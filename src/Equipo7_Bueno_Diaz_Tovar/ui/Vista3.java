package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import Equipo7_Bueno_Diaz_Tovar.logic.MiPlan;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Vista3 implements Vista {

    private Scene escena;
    private Chain<Plan> planes;
    private String planAImprimir;
    private Plan planActual;
    private VBox layout = new VBox();
    private HBox layoutHor = new HBox();
    private VBox[] columnas;
    private TextField[] MateriaTF;
    private Label[] MateriaLB;
    private Button insertarMateria;
    private Button eliminarMateria;
    private Button guardar;
    private Button eliminar;

    public Vista3(Chain<Plan> planes, String plan) {

        this.planes = planes;
        ChainNode<Plan> temp1 = this.planes.getHead();
        do {
            if (temp1.getElement().getNombre().equals(plan)) {
                this.planActual = temp1.getElement();
            }
            temp1 = temp1.getNext();
        } while (temp1 != null);

        dibujarMalla();

    }

    public void dibujarMalla() {
        this.layout = new VBox();
        columnas = new VBox[this.planActual.getN_semestres()];
        this.layoutHor = new HBox();
        for (int i = 0; i < columnas.length; i++) {
            ChainNode<Materia> temp2 = this.planActual.getSemestres()[i].getHead();
            columnas[i] = new VBox();
            columnas[i].getChildren().add(new Label("Semestre" + " " + (i + 1)));
            if (temp2 != null) {
                do {
                    Button boton = new Button(temp2.getElement().getName());
                    this.columnas[i].getChildren().add(boton);
                    temp2 = temp2.getNext();
                } while (temp2 != null);
            }
            this.layoutHor.getChildren().add(this.columnas[i]);
        }
        this.layout.getChildren().add(this.layoutHor);

        this.insertarMateria = new Button("Insertar materia de libre elección");
        this.guardar = new Button("Insertar");
        this.eliminarMateria = new Button("Eliminar Materia");
        this.eliminar = new Button("Eliminar");

        this.insertarMateria.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                insertarMateria.setVisible(false);
                for (int i = 0; i < 6; i++) {
                    MateriaTF[i].setVisible(true);
                    MateriaLB[i].setVisible(true);
                    MateriaTF[i].setText("");
                }
                guardar.setVisible(true);
                eliminar.setVisible(false);
                eliminarMateria.setVisible(false);
            }
        });

        this.guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Materia materia = new Materia(Integer.parseInt(MateriaTF[0].getText()),
                        MateriaTF[1].getText(),
                        Integer.parseInt(MateriaTF[2].getText()),
                        MateriaTF[3].getText(),
                        MateriaTF[4].getText());
                int semestre = Integer.parseInt(MateriaTF[5].getText());
                materia.setSemestre(semestre);
                MiPlan.insertarMateria(planActual, materia);
                dibujarMalla();
            }
        });

        this.eliminarMateria.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MateriaTF[0].setVisible(true);
                MateriaLB[0].setVisible(true);
                MateriaTF[1].setVisible(false);
                MateriaLB[1].setVisible(false);
                MateriaTF[2].setVisible(false);
                MateriaLB[2].setVisible(false);
                MateriaTF[3].setVisible(false);
                MateriaLB[3].setVisible(false);
                MateriaTF[4].setVisible(false);
                MateriaLB[4].setVisible(false);
                MateriaTF[5].setVisible(false);
                MateriaLB[5].setVisible(false);
                insertarMateria.setVisible(false);
                guardar.setVisible(false);
                eliminarMateria.setVisible(false);
                eliminar.setVisible(true);
            }
        });

        this.eliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int codigo = Integer.parseInt(MateriaTF[0].getText());
                MiPlan.eliminarMateria(planActual, codigo);
                dibujarMalla();
            }
        });

        this.MateriaTF = new TextField[6];
        this.MateriaLB = new Label[6];
        this.MateriaLB[0] = new Label("Codigo");
        this.MateriaLB[1] = new Label("Nombre");
        this.MateriaLB[2] = new Label("Creditos");
        this.MateriaLB[3] = new Label("Tipologia");
        this.MateriaLB[4] = new Label("Prerrequisitos");
        this.MateriaLB[5] = new Label("Semestre");
        for (int i = 0; i < 6; i++) {
            this.MateriaTF[i] = new TextField();
        }

        this.insertarMateria.setVisible(true);
        this.eliminarMateria.setVisible(true);
        this.guardar.setVisible(false);
        this.eliminar.setVisible(false);

        this.layout.getChildren().add(this.insertarMateria);
        this.layout.getChildren().add(this.eliminarMateria);
        this.layout.getChildren().add(this.guardar);
        this.layout.getChildren().add(this.eliminar);
        for (int i = 0; i < 6; i++) {
            this.MateriaTF[i].setVisible(false);
            this.MateriaLB[i].setVisible(false);
            this.layout.getChildren().add(this.MateriaLB[i]);
            this.layout.getChildren().add(this.MateriaTF[i]);
        }

        this.escena = new Scene(this.layout, 1280, 700);

        Singleton singleton = Singleton.getSingleton();
        Stage stage = singleton.getStage();
        stage.setScene(this.getScena());
        stage.show();
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
    }

    @Override
    public Scene getScena() {
        return this.escena;
    }

}
