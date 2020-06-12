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
    private Button editar, mi_avance, atras, cancelarAccion;

    public Vista3(SingleLinkedList<Plan> planes, String plan) {
        Vista3.planes = planes;
        SingleLinkedListNode<Plan> temp1 = Vista3.planes.getHead();
        do {
            if (temp1.getElement().getNombre().equals(plan)) {
                this.planActual = temp1.getElement();
            }
            temp1 = temp1.getNext();
        } while (temp1 != null);
        dibujarMalla();
    }

    public final void presentarVistaInicial() {
        this.insertarMateria.setVisible(true);
        this.eliminarMateria.setVisible(true);
        this.consultarMateria.setVisible(true);
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
    }

    public void alertaEntrada(String advertencia) {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle(advertencia);
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingresa una entrada válida");
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }

    public void inicializarBotonesLabelsTextField() {

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
        atras.setOnAction((ActionEvent event) -> {
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

    public void inicializarSetOnAction() {

        this.mi_avance.setOnAction((ActionEvent event) -> {
            Main.setPestañas(3);
            Controlador4 controlador = new Controlador4(planActual);
            Vista4 vista = controlador.getVista();
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(vista.getScena());
            stage.show();
            MiAvance.salvarAvance(planActual);
        });

        this.insertarMateria.setOnAction((ActionEvent event) -> {
            for (int i = 0; i < 5; i++) {
                MateriaTF[i].setVisible(true);
                MateriaLB[i].setVisible(true);
                MateriaTF[i].setText("");
            }
            insertarMateria.setVisible(false);
            insertar.setVisible(true);
            eliminarMateria.setVisible(false);
            eliminar.setVisible(false);
            consultarMateria.setVisible(false);
            consultar.setVisible(false);
            editar.setVisible(false);
            cancelarAccion.setVisible(true);
        });

        this.insertar.setOnAction((ActionEvent event) -> {

            try {
                Materia materia = new Materia(Long.parseLong(MateriaTF[0].getText()),
                        MateriaTF[1].getText(),
                        Integer.parseInt(MateriaTF[2].getText()),
                        "LE",
                        Integer.parseInt(MateriaTF[3].getText()));
                MiPlan.insertarMateria(planActual, materia);
                MiAvance.actualizarNota(planActual, materia.getCodigo(), Double.parseDouble(MateriaTF[4].getText()));
                dibujarMalla();
            } catch (NumberFormatException e) {
                alertaEntrada("Insertar materia");
            }
            MiAvance.salvarAvance(planActual);
        });

        this.eliminarMateria.setOnAction((ActionEvent event) -> {
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
            eliminar.setVisible(true);
            consultarMateria.setVisible(false);
            consultar.setVisible(false);
            editar.setVisible(false);
            cancelarAccion.setVisible(true);
        });

        this.eliminar.setOnAction((ActionEvent event) -> {
            try {
                int codigo = Integer.parseInt(MateriaTF[0].getText());
                MiPlan.eliminarMateria(planActual, codigo);
                dibujarMalla();
            } catch (NumberFormatException e) {
                alertaEntrada("Eliminar materia");
            }
            MiAvance.salvarAvance(planActual);
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
            this.MateriaLB[0] = new Label("Codigo");
            double nota = 0;
            try {
                nota = Double.parseDouble(this.MateriaTF[0].getText());
                int codigo = Integer.parseInt(this.MateriaTF[1].getText());
                MiAvance.actualizarNota(planActual, codigo, nota);
                presentarVistaInicial();
            } catch (NumberFormatException e) {
                alertaEntrada("Actualizar nota");
            }
            MiAvance.salvarAvance(planActual);
        });
    }

    public void agregarAVentana() {
        this.layout.getChildren().add(this.atras);
        this.layoutBotonesPrincipales.getChildren().add(this.mi_avance);
        this.layoutBotonesPrincipales.getChildren().add(this.insertarMateria);
        this.layoutBotonesPrincipales.getChildren().add(this.consultarMateria);
        this.layoutBotonesPrincipales.getChildren().add(this.eliminarMateria);
        this.layoutBotonesSecundarios.getChildren().add(this.cancelarAccion);
        this.layoutBotonesSecundarios.getChildren().add(this.insertar);
        this.layoutBotonesSecundarios.getChildren().add(this.consultar);
        this.layoutBotonesSecundarios.getChildren().add(this.eliminar);
        this.layoutBotonesSecundarios.getChildren().add(this.editar);
        this.layout.getChildren().add(layoutBotonesPrincipales);
        this.layout.getChildren().add(layoutBotonesSecundarios);
        for (int i = 0; i < 5; i++) {
            this.MateriaTF[i] = new TextField();
            this.layout.getChildren().add(this.MateriaLB[i]);
            this.layout.getChildren().add(this.MateriaTF[i]);
        }

        presentarVistaInicial();

        this.sp.setContent(layout);
        Singleton singleton = Singleton.getSingleton();
        Stage stage = singleton.getStage();
        stage.setScene(this.getScena());
        stage.show();
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
    }

    public final void dibujarMalla() {

        this.layout = new VBox();
        this.layoutHor = new HBox();
        this.layoutBotonesPrincipales = new HBox();
        this.layoutBotonesSecundarios = new HBox();

        System.out.printf("%s%d\n", "\t\t\t\tInicio mostrar materias = \t", System.currentTimeMillis());
        this.sp = new ScrollPane();
        this.layout = new VBox();
        this.escena = new Scene(this.sp, 1280, 700);
        escena.getStylesheets().add(getClass().getResource("Presed_Boton.css").toExternalForm());

        inicializarBotonesLabelsTextField();
        inicializarSetOnAction();

        this.columnas = new VBox[this.planActual.getN_semestres()];
        this.layoutHor = new HBox();
        this.layoutHor.setSpacing(25);
        this.layoutHor.getChildren().add(new Label(""));
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
                boton.setPrefWidth(225);
                boton.setAlignment(Pos.CENTER);
                boton.setWrapText(true);
                boton.getStyleClass().add("button");
                this.columnas[i].getChildren().add(boton);
            }
            this.layoutHor.getChildren().add(this.columnas[i]);
        }
        this.layout.getChildren().add(this.layoutHor);
        System.out.printf("%s%d\n", "\t\t\t\tFin mostrar materias = \t\t", System.currentTimeMillis());

        agregarAVentana();

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
        Controlador2 controlador = new Controlador2(Vista3.planes);
        Vista vista = controlador.getVista();
        stage.setScene(vista.getScena());
        stage.show();
    }

    @Override
    public Scene getScena() {
        return this.escena;
    }

}
