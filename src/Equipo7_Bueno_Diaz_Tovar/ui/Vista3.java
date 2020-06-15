package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import Equipo7_Bueno_Diaz_Tovar.logic.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Vista3 implements Vista {

    private Scene escena;
    private static SingleLinkedList<Plan> planes;
    private String planAImprimir;
    private Plan planActual;
    private ScrollPane sp;
    private VBox layout;
    private HBox layoutHor, layoutBotonesPrincipales, layoutBotonesSecundarios;
    private VBox[] columnas;
    private TextField[] MateriaTF;
    private Label[] MateriaLB;
    private Button insertarMateria, insertar;
    private Button eliminarMateria, eliminar;
    private Button consultarMateria, consultar;
    private Button editar, mi_avance, atras, cancelarAccion, materiaUrgente, modoVista;
    boolean vistaTipologia;

    public Vista3(SingleLinkedList<Plan> planes, String plan) {
        Vista3.planes = planes;
        SingleLinkedListNode<Plan> temp1 = Vista3.planes.getHead();
        do {
            if (temp1.getElement().getNombre().equals(plan)) {
                this.planActual = temp1.getElement();
            }
            temp1 = temp1.getNext();
        } while (temp1 != null);
        this.layout = new VBox();
        this.layoutHor = new HBox();
        this.layoutBotonesPrincipales = new HBox();
        this.layoutBotonesSecundarios = new HBox();
        this.sp = new ScrollPane();
        this.columnas = new VBox[this.planActual.getN_semestres()];
        this.layoutHor = new HBox();
        this.escena = new Scene(this.layout, 1000, 600);
        this.vistaTipologia = true;

        this.escena.getStylesheets().add(getClass().getResource("Presed_Boton.css").toExternalForm());
        this.layoutHor.setSpacing(25);
        this.layoutHor.getChildren().add(new Label(""));
        this.sp.setPrefSize(Screen.getPrimary().getVisualBounds().getWidth(), 320);
        this.sp.setHmax(Screen.getPrimary().getVisualBounds().getHeight() - this.layout.getHeight());
        this.sp.setContent(layoutHor);

        inicializarBotonesLabelsTextField();
        inicializarSetOnAction();

        dibujarMalla();

    }

    public final void presentarVistaInicial() {
        this.insertarMateria.setVisible(true);
        this.eliminarMateria.setVisible(true);
        this.consultarMateria.setVisible(true);
        this.mi_avance.setVisible(true);
        this.materiaUrgente.setVisible(true);
        this.insertar.setVisible(false);
        this.eliminar.setVisible(false);
        this.consultar.setVisible(false);
        this.editar.setVisible(false);
        this.cancelarAccion.setVisible(false);
        for (int i = 0; i < 5; i++) {
            this.MateriaTF[i].setVisible(false);
            this.MateriaLB[i].setVisible(false);
            this.MateriaTF[i].setText("");
        }
        this.MateriaLB[0].setText("Codigo");
        this.MateriaLB[1].setText("Nombre");
        this.MateriaLB[2].setText("Creditos");
        this.MateriaLB[3].setText("Semestre");
        this.MateriaLB[4].setText("Nota");
    }

    private void inicializarBotonesLabelsTextField() {

        //BOTONES
        this.layoutBotonesPrincipales = new HBox();
        this.layoutBotonesPrincipales.setSpacing(10);
        this.insertarMateria = new Button("Insertar materia de libre elección");
        this.insertar = new Button("Insertar");
        this.eliminarMateria = new Button("Eliminar materia");
        this.eliminar = new Button("Eliminar");
        this.consultarMateria = new Button("Buscar materia");
        this.consultar = new Button("Buscar");
        this.editar = new Button("Actualizar");
        this.mi_avance = new Button("Mostrar Avance");
        this.atras = new Button("Atrás");
        this.materiaUrgente = new Button("Materia de mayor prioridad");
        this.modoVista = new Button("Aprobados/Tipología");
        this.atras.setOnAction((ActionEvent event) -> {
            goBack();
        });
        this.cancelarAccion = new Button("Cancelar");
        cancelarAccion.setOnAction((ActionEvent event) -> {
            presentarVistaInicial();
        });

        //ENTRADAS DE TEXTO
        this.MateriaTF = new TextField[5];
        this.MateriaLB = new Label[5];
        this.MateriaLB[0] = new Label("Codigo");
        this.MateriaLB[1] = new Label("Nombre");
        this.MateriaLB[2] = new Label("Creditos");
        this.MateriaLB[3] = new Label("Semestre");
        this.MateriaLB[4] = new Label("Nota");

    }

    public void agregarBoton(Materia materia) {
        Button boton = new Button(materia.getName());
        long codigo = materia.getCodigo();
        boton.setOnAction((ActionEvent event) -> {
            try {
                MiPlan.consultarMateria(planActual, codigo);
                presentarVistaInicial();
                if (materia.getNota().isEmpty() || materia.getLastNota() < 3) {
                    Identificador idTemp = new Identificador(codigo, 0, 0), id;
                    id = (Identificador) planActual.getIdentificadores().find(idTemp).getElement();
                    if (MiAvance.prerrequisitosVistos(planActual, id)) {
                        this.editar.setVisible(true);
                        this.cancelarAccion.setVisible(true);
                        this.MateriaLB[0] = new Label("Nota");
                        this.MateriaLB[0].setVisible(true);
                        this.MateriaTF[0].setVisible(true);
                        this.MateriaTF[1].setText(String.valueOf(codigo));
                    }
                }

            } catch (NumberFormatException e) {
                alertaEntrada("Actualizar nota materia");
            }
        });
        boton.setPrefHeight(25);
        boton.setPrefWidth(225);
        boton.setAlignment(Pos.CENTER);
        boton.setWrapText(true);
        this.columnas[materia.getSemestre() - 1].getChildren().add(boton);
        int i = materia.getSemestre() - 1;
        int j = this.columnas[materia.getSemestre() - 1].getChildren().size() - 1;
        if (this.vistaTipologia) {
            switch (materia.getTipologia().substring(0, 1)) {
                case "F":
                    this.columnas[i].getChildren().get(j).getStyleClass().add("button_3");
                    break;
                case "D":
                    this.columnas[i].getChildren().get(j).getStyleClass().add("button_1");
                    break;
                case "L":
                    this.columnas[i].getChildren().get(j).getStyleClass().add("button_2");
                    break;
            }
        } else {
            this.escena.getStylesheets().clear();
            this.escena.getStylesheets().add(getClass().getResource("Degrade_Buton.css").toExternalForm());
            if (materia.getNota().size() != 0) {
                if (materia.getLastNota() >= 3) {
                    this.columnas[i].getChildren().get(j).getStyleClass().add("button_approved");
                } else {
                    this.columnas[i].getChildren().get(j).getStyleClass().add("button_failed");
                }
            } else {
                this.columnas[i].getChildren().get(j).getStyleClass().add("button_unseen");
            }
            this.escena.getStylesheets().add(getClass().getResource("Presed_boton.css").toExternalForm());
        }
        Stage stage = Singleton.getSingleton().getStage();
        stage.show();
        presentarVistaInicial();
    }

    public void actualizarBoton(int codigo, double nota) {
        LinkedBinaryTreeNode<Identificador> matNode = this.planActual.getIdentificadores().find(new Identificador(codigo, 0, 0));
        int sem = matNode.getElement().getSemestre();
        int pos = matNode.getElement().getPosición();
        Materia mat = this.planActual.getSemestres()[sem - 1].get(pos);
        Button boton = (Button) this.columnas[sem - 1].getChildren().get(pos + 1);
        boton.setPrefHeight(25);
        boton.setPrefWidth(225);
        int i = sem - 1;
        int j = pos + 1;
        if (!this.vistaTipologia) {
            this.escena.getStylesheets().clear();
            this.escena.getStylesheets().add(getClass().getResource("Degrade_Buton.css").toExternalForm());
            if (mat.getNota().size() != 0) {
                if (mat.getLastNota() >= 3) {
                    this.columnas[i].getChildren().get(j).getStyleClass().clear();
                    this.columnas[i].getChildren().get(j).getStyleClass().add("button_approved");
                } else {
                    this.columnas[i].getChildren().get(j).getStyleClass().clear();
                    this.columnas[i].getChildren().get(j).getStyleClass().add("button_failed");
                }
            } else {
                this.columnas[i].getChildren().get(j).getStyleClass().add("button_unseen");
            }
            this.escena.getStylesheets().add(getClass().getResource("Presed_boton.css").toExternalForm());
        }
    }

    private void inicializarSetOnAction() {

        this.insertarMateria.setOnAction((ActionEvent event) -> {
            for (int i = 0; i < 5; i++) {
                MateriaTF[i].setVisible(true);
                MateriaLB[i].setVisible(true);
                MateriaTF[i].setText("");
            }
            this.insertarMateria.setVisible(false);
            this.insertar.setVisible(true);
            this.eliminarMateria.setVisible(false);
            this.eliminar.setVisible(false);
            this.consultarMateria.setVisible(false);
            this.consultar.setVisible(false);
            this.editar.setVisible(false);
            this.cancelarAccion.setVisible(true);
            this.materiaUrgente.setVisible(false);
        }
        );

        this.insertar.setOnAction((ActionEvent event) -> {
            try {
                if (Double.parseDouble(MateriaTF[4].getText()) >= 0 && Double.parseDouble(MateriaTF[4].getText()) <= 5) {
                    Materia materia = new Materia(Long.parseLong(MateriaTF[0].getText()),
                            MateriaTF[1].getText(),
                            Integer.parseInt(MateriaTF[2].getText()),
                            "LE",
                            Integer.parseInt(MateriaTF[3].getText()));
                    MiPlan.insertarMateria(planActual, materia);
                    if (Integer.parseInt(MateriaTF[3].getText()) < this.planActual.getN_semestres()) {
                        MiAvance.actualizarNota(planActual, materia.getCodigo(), Double.parseDouble(MateriaTF[4].getText()));
                        agregarBoton(materia);
                    }
                    presentarVistaInicial();
                } else {
                    alertaNota();
                }
            } catch (NumberFormatException e) {
                alertaEntrada("Insertar materia");
            }
        });

        this.eliminarMateria.setOnAction((ActionEvent event) -> {
            MateriaTF[0].setVisible(true);
            MateriaLB[0].setVisible(true);
            MateriaTF[0].setText("");
            for (int i = 1; i < 5; i++) {
                MateriaTF[i].setVisible(false);
                MateriaLB[i].setVisible(false);
                MateriaTF[i].setText("");
            }
            insertarMateria.setVisible(false);
            insertar.setVisible(false);
            eliminarMateria.setVisible(false);
            eliminar.setVisible(true);
            consultarMateria.setVisible(false);
            consultar.setVisible(false);
            editar.setVisible(false);
            cancelarAccion.setVisible(true);
            this.materiaUrgente.setVisible(false);
        });

        this.eliminar.setOnAction((ActionEvent event) -> {
            try {
                int codigo = Integer.parseInt(MateriaTF[0].getText());
                try {
                    Identificador id = (Identificador) planActual.getIdentificadores().find(new Identificador(codigo, 0, 0)).getElement();
                    int sem = id.getSemestre();
                    int i = id.getPosición();
                    try {
                        MiPlan.eliminarMateria(planActual, codigo);
                        this.columnas[sem - 1].getChildren().remove(i + 1);
                    } catch (Exception ex) {
                        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                        dialogo.setTitle("Eliminar materia");
                        dialogo.setHeaderText(null);
                        dialogo.setContentText("Esta materia es obligatoria dentro del plan de estudios");
                        dialogo.initStyle(StageStyle.UTILITY);
                        dialogo.showAndWait();
                    }
                } catch (NullPointerException ex) {
                    MiPlan.alertaMateriaNoEncontrada();
                }
                Stage stage = Singleton.getSingleton().getStage();
                stage.show();
                presentarVistaInicial();
            } catch (NumberFormatException e) {
                alertaEntrada("Eliminar materia");
            }
        });

        this.consultarMateria.setOnAction((ActionEvent event) -> {
            MateriaTF[0].setVisible(true);
            MateriaLB[0].setVisible(true);
            for (int i = 1; i < 5; i++) {
                MateriaTF[i].setVisible(false);
                MateriaLB[i].setVisible(false);
                MateriaTF[i].setText("");
            }
            insertarMateria.setVisible(false);
            insertar.setVisible(false);
            eliminarMateria.setVisible(false);
            eliminar.setVisible(false);
            consultarMateria.setVisible(false);
            consultar.setVisible(true);
            editar.setVisible(false);
            cancelarAccion.setVisible(true);
            this.materiaUrgente.setVisible(false);
        });

        this.consultar.setOnAction((ActionEvent event) -> {
            try {
                int codigo = Integer.parseInt(MateriaTF[0].getText());
                MiPlan.consultarMateria(planActual, codigo);
                presentarVistaInicial();
            } catch (NumberFormatException e) {
                alertaEntrada("Consultar materia");
            }
        });

        this.editar.setOnAction((ActionEvent event) -> {
            try {
                double nota = Double.parseDouble(this.MateriaTF[0].getText());
                if (nota >= 0 && nota <= 5) {
                    int codigo = Integer.parseInt(this.MateriaTF[1].getText());
                    MiAvance.actualizarNota(this.planActual, codigo, nota);
                    actualizarBoton(codigo, nota);
                    presentarVistaInicial();
                } else {
                    this.MateriaTF[0].setText("");
                    alertaNota();
                }
            } catch (NumberFormatException e) {
                alertaEntrada("Actualizar nota");
            }
        });

        this.mi_avance.setOnAction((ActionEvent event) -> {
            Main.setPestañas(3);
            Controlador4 controlador = new Controlador4(planActual);
            Vista4 vista = controlador.getVista();
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(vista.getScena());
            stage.centerOnScreen();
            stage.show();
            MiAvance.salvarAvance(planActual);
        });

        this.materiaUrgente.setOnAction((ActionEvent event) -> {
            MiPlan.consultarMateria(this.planActual, this.planActual.getMateriasUrgentes().getMax().getCodigo());
        });

        this.modoVista.setOnAction((ActionEvent event) -> {
            if (!this.vistaTipologia) {
                this.escena.getStylesheets().clear();
                this.escena.getStylesheets().add(getClass().getResource("Presed_boton.css").toExternalForm());
                for (int i = 0; i < this.planActual.getN_semestres(); i++) {
                    for (int j = 1; j < this.columnas[i].getChildren().size(); j++) {
                        this.columnas[i].getChildren().get(j).getStyleClass().clear();
                        Materia mat = this.planActual.getSemestres()[i].get(j - 1);
                        switch (mat.getTipologia().substring(0, 1)) {
                            case "F":
                                this.columnas[i].getChildren().get(j).getStyleClass().add("button_3");
                                break;
                            case "D":
                                this.columnas[i].getChildren().get(j).getStyleClass().add("button_1");
                                break;
                            case "L":
                                this.columnas[i].getChildren().get(j).getStyleClass().add("button_2");
                                break;
                        }
                    }
                }
            } else {
                this.escena.getStylesheets().clear();
                this.escena.getStylesheets().add(getClass().getResource("Degrade_Buton.css").toExternalForm());
                for (int i = 0; i < this.planActual.getN_semestres(); i++) {
                    for (int j = 1; j < this.columnas[i].getChildren().size(); j++) {
                        this.columnas[i].getChildren().get(j).getStyleClass().clear();
                        Materia mat = this.planActual.getSemestres()[i].get(j - 1);
                        if (mat.getNota().size() != 0) {
                            if (mat.getLastNota() >= 3) {
                                this.columnas[i].getChildren().get(j).getStyleClass().add("button_approved");
                            } else {
                                this.columnas[i].getChildren().get(j).getStyleClass().add("button_failed");
                            }
                        } else {
                            this.columnas[i].getChildren().get(j).getStyleClass().add("button_unseen");
                        }
                    }
                }
            }
            this.vistaTipologia = !this.vistaTipologia;
        });
    }

    public void agregarAVentana() {

        this.layoutBotonesPrincipales.getChildren().add(this.mi_avance);
        this.layoutBotonesPrincipales.getChildren().add(this.modoVista);
        this.layoutBotonesPrincipales.getChildren().add(this.insertarMateria);
        this.layoutBotonesPrincipales.getChildren().add(this.consultarMateria);
        this.layoutBotonesPrincipales.getChildren().add(this.eliminarMateria);
        this.layoutBotonesPrincipales.getChildren().add(this.materiaUrgente);
        this.layoutBotonesSecundarios.getChildren().add(this.cancelarAccion);
        this.layoutBotonesSecundarios.getChildren().add(this.insertar);
        this.layoutBotonesSecundarios.getChildren().add(this.consultar);
        this.layoutBotonesSecundarios.getChildren().add(this.eliminar);
        this.layoutBotonesSecundarios.getChildren().add(this.editar);
        this.layout.getChildren().add(this.sp);
        this.layout.getChildren().add(this.atras);
        this.layout.getChildren().add(layoutBotonesPrincipales);
        this.layout.getChildren().add(layoutBotonesSecundarios);
        for (int i = 0; i < 5; i++) {
            this.MateriaTF[i] = new TextField();
            this.layout.getChildren().add(this.MateriaLB[i]);
            this.layout.getChildren().add(this.MateriaTF[i]);
        }

        presentarVistaInicial();

    }

    public final void dibujarMalla() {

        System.out.printf("%s%d\n", "Inicio mostrar materias = \t", System.currentTimeMillis());
        for (int i = 0; i < columnas.length; i++) {
            this.columnas[i] = new VBox();
            this.columnas[i].setSpacing(10);
            Label lb = new Label("                 " + "Semestre" + " " + (i + 1));
            lb.setPrefWidth(225);
            lb.setFont(Font.font(java.awt.Font.SERIF, FontPosture.ITALIC, 18));
            this.columnas[i].getChildren().add(lb);
            for (int j = 0; j < this.planActual.getSemestres()[i].size(); j++) {
                Button boton = new Button(this.planActual.getSemestres()[i].get(j).getName());
                long codigo = planActual.getSemestres()[i].get(j).getCodigo();
                Materia materia = planActual.getSemestres()[i].get(j);
                boton.setOnAction((ActionEvent event) -> {
                    try {
                        MiPlan.consultarMateria(planActual, codigo);
                        presentarVistaInicial();
                        if (materia.getNota().isEmpty() || materia.getLastNota() < 3) {
                            Identificador idTemp = new Identificador(codigo, 0, 0), id;
                            id = (Identificador) planActual.getIdentificadores().find(idTemp).getElement();
                            if (MiAvance.prerrequisitosVistos(planActual, id)) {
                                this.editar.setVisible(true);
                                this.cancelarAccion.setVisible(true);
                                this.MateriaLB[0].setText("Nota");
                                this.MateriaLB[0].setVisible(true);
                                this.MateriaTF[0].setVisible(true);
                                this.MateriaTF[1].setText(String.valueOf(codigo));

                            }
                        }
                    } catch (NumberFormatException e) {
                        alertaEntrada("Actualizar nota materia");
                    }
                });
                boton.setPrefHeight(25);
                boton.setPrefWidth(225);
                boton.setAlignment(Pos.CENTER);
                boton.getStyleClass().clear();
                switch (materia.getTipologia().substring(0, 1)) {
                    case "F":
                        boton.getStyleClass().add("button_3");
                        break;
                    case "D":
                        boton.getStyleClass().add("button_1");
                        break;
                    case "L":
                        boton.getStyleClass().add("button_2");
                        break;
                }
                this.escena.getStylesheets().add(getClass().getResource("Degrade_Buton.css").toExternalForm());
                if (materia.getNota().size() != 0) {
                    if (materia.getLastNota() >= 3) {
                        boton.getStyleClass().add("button_approved");
                    } else {
                        boton.getStyleClass().add("button_failed");
                    }
                } else {
                    boton.getStyleClass().add("button_unseen");
                }
                this.escena.getStylesheets().clear();
                this.escena.getStylesheets().add(getClass().getResource("Presed_Boton.css").toExternalForm());
                this.columnas[i].getChildren().add(boton);
            }
            this.layoutHor.getChildren().add(this.columnas[i]);
        }
        System.out.printf("%s%d\n", "Fin mostrar materias = \t\t", System.currentTimeMillis());
        agregarAVentana();

    }

    public void alertaEntrada(String advertencia) {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle(advertencia);
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingresa una entrada válida");
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }

    public void alertaNota() {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle("Insertar materia");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese una nota válida");
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }

    public Plan getPlanActual() {
        return planActual;
    }

    public void setPlanActual(Plan planActual) {
        this.planActual = planActual;
    }

    public static SingleLinkedList<Plan> getPlanes() {
        return Vista3.planes;
    }

    public static void setPlanes(SingleLinkedList<Plan> planes) {
        Vista3.planes = planes;
    }

    @Override
    public void goBack() {
        Main.deletePestañas();
        MiAvance.salvarAvance(this.planActual);
        Singleton singleton = Singleton.getSingleton();
        Stage stage = singleton.getStage();
        singleton.setStage(stage);
        Controlador2 controlador = new Controlador2(Vista3.planes);
        Vista vista = controlador.getVista();
        stage.setScene(vista.getScena());
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public Scene getScena() {
        return this.escena;
    }

}
