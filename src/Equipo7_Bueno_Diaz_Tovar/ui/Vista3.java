package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import Equipo7_Bueno_Diaz_Tovar.logic.Main;
import Equipo7_Bueno_Diaz_Tovar.logic.MiAvance;
import Equipo7_Bueno_Diaz_Tovar.logic.MiPlan;
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
    private static Chain<Plan> planes;
    private String planAImprimir;
    private Plan planActual;
    private ScrollPane sp;
    private VBox layout = new VBox();
    private HBox layoutHor = new HBox();
    private HBox layout_Botones = new HBox();
    private VBox[] columnas;
    private TextField[] MateriaTF;
    private Label[] MateriaLB;
    private Button insertarMateria;
    private Button insertar;
    private Button eliminarMateria;
    private Button eliminar;
    private Button consultarMateria;
    private Button consultar;
    private Button editar;
    private Button mi_avance;
    private Button atras;

    public Vista3(Chain<Plan> planes, String plan) {
        Vista3.planes = planes;
        ChainNode<Plan> temp1 = this.planes.getHead();
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
        for (int i = 0; i < 6; i++) {
            this.MateriaTF[i].setVisible(false);
            this.MateriaLB[i].setVisible(false);
            this.MateriaTF[i].setText("");
        }
    }

    public final void dibujarMalla() {
        System.out.printf("%s%d\n", "\t\t\t\tInicio mostrar materias = \t", System.currentTimeMillis());
        this.sp = new ScrollPane();
        this.layout = new VBox();
        this.escena = new Scene(this.sp, 1280, 700);
        escena.getStylesheets().add(getClass().getResource("Presed_Boton.css").toExternalForm());

        //Botones
        this.layout_Botones = new HBox();
        this.layout_Botones.setSpacing(10);
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

        //Malla
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
                int codigo = planActual.getSemestres()[i].get(j).getCodigo();
                boton.setOnAction((ActionEvent event) -> {
                    try {
                        presentarVistaInicial();
                        this.editar.setVisible(true);
                        this.MateriaLB[0] = new Label("Nota");
                        this.MateriaLB[0].setVisible(true);
                        this.MateriaTF[0].setText("");
                        this.MateriaTF[0].setVisible(true);
                        this.MateriaTF[1].setText(String.valueOf(codigo));
                    } catch (NumberFormatException e) {
                        alertaEntrada("Consultar materia");
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

        this.layout_Botones.getChildren().add(mi_avance);
        this.layout_Botones.getChildren().add(insertarMateria);
        this.layout_Botones.getChildren().add(consultarMateria);
        this.layout_Botones.getChildren().add(eliminarMateria);

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
            for (int i = 0; i < 6; i++) {
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
        });

        this.insertar.setOnAction((ActionEvent event) -> {
            try {
                Materia materia = new Materia(Integer.parseInt(MateriaLB[0].getText()),
                MateriaLB[1].getText(),
                Integer.parseInt(MateriaLB[2].getText()),
               "LE",
                Integer.parseInt(MateriaLB[3].getText()));//Co.No.Cr.Se.No
                materia.setNota(Integer.parseInt(MateriaLB[4].getText()));
                MiPlan.insertarMateria(planActual, materia);
                dibujarMalla();
            } catch (NumberFormatException e) {
                alertaEntrada("Insertar materia");
            }
            MiAvance.salvarAvance(planActual);
        });

        this.eliminarMateria.setOnAction((ActionEvent event) -> {
            MateriaTF[0].setVisible(true);
            MateriaLB[0].setVisible(true);
            for (int i = 1; i < 6; i++) {
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
            for (int i = 1; i < 6; i++) {
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

        this.layout.getChildren().add(layout_Botones);
        this.layout.getChildren().add(this.atras);
        this.layout.getChildren().add(this.insertar);
        this.layout.getChildren().add(this.eliminar);
        this.layout.getChildren().add(this.consultar);
        this.layout.getChildren().add(this.editar);

        //Entradas de texto
        this.MateriaTF = new TextField[6];
        this.MateriaLB = new Label[6];
        this.MateriaLB[0] = new Label("Codigo");
        this.MateriaLB[1] = new Label("Nombre");
        this.MateriaLB[2] = new Label("Creditos");
        this.MateriaLB[3] = new Label("Semestre");
        this.MateriaLB[4] = new Label("Nota");
        for (int i = 0; i < 6; i++) {
            this.MateriaTF[i] = new TextField();
            this.layout.getChildren().add(this.MateriaLB[i]);
            this.layout.getChildren().add(this.MateriaTF[i]);
        }

        presentarVistaInicial();

        //Ventana
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

    public void alertaEntrada(String advertencia) {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle(advertencia);
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingresa una entrada válida");
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }

    @Override
    public void goBack() {
        Main.deletePestañas();
        MiAvance.salvarAvance(planActual);
        Singleton singleton = Singleton.getSingleton();
        Stage stage = singleton.getStage();
        Controlador2 controlador = new Controlador2(planes);
        Vista vista = controlador.getVista();
        stage.setScene(vista.getScena());
        stage.show();
    }

    public Plan getPlanActual() {
        return planActual;
    }

    public void setPlanActual(Plan planActual) {
        this.planActual = planActual;
    }

    @Override
    public Scene getScena() {
        return this.escena;
    }

    public static Chain<Plan> getPlanes() {
        return planes;
    }

    public static void setPlanes(Chain<Plan> planes) {
        Vista3.planes = planes;
    }

}
