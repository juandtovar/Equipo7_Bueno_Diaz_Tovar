package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import Equipo7_Bueno_Diaz_Tovar.logic.MiPlan;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JTextField;

public class Vista3 implements Vista {

    private Scene escena;
    private Chain<Plan> planes;
    private String planAImprimir;
    private Plan planActual;
    private MiPlan miplan;
    private VBox[] columnas;
    private VBox layout = new VBox();
    private TextField[] insertarMateriaTF;
    private Label[] insertarMateriaLB;
    private Button insertarMateria;
    private Button eliminarMateria;
    private Button guardar;
    private Button eliminar;

    public Vista3(Chain<Plan> planes, String plan) {

        miplan = new MiPlan();
        this.planes = planes;
        layout = new VBox();
        HBox layoutHor = new HBox();
        ChainNode<Plan> temp1 = new ChainNode<>();

        //Hallar Plan
        temp1 = this.planes.getHead();
        do {
            if (temp1.getElement().getNombre().equals(plan)) {
                planActual = temp1.getElement();
            }
            temp1 = temp1.getNext();
        } while (temp1 != null);
        ChainNode<Materia> temp2 = new ChainNode<>();
        columnas = new VBox[planActual.getN_semestres() - 1];

        //Dibujar malla
        for (int i = 0; i < columnas.length; i++) {
            temp2 = planActual.getSemestres()[i].getHead();
            System.out.println(i);
            columnas[i] = new VBox();
            columnas[i].getChildren().add(new Label("Semestre" + " " + (i + 1)));
            do {
                Button boton = new Button(temp2.getElement().getName());
                columnas[i].getChildren().add(boton);
                temp2 = temp2.getNext();
            } while (temp2 != null);
            layoutHor.getChildren().add(columnas[i]);
        }

        insertarMateria = new Button("Insertar materia de libre elección");
        insertarMateria.setOnAction(new Evento1());
        layout.getChildren().add(layoutHor);
        layout.getChildren().add(insertarMateria);
        this.escena = new Scene(layout, 1000, 700);
        //InsertarMateria
        this.guardar = new Button("Insertar");
        this.guardar.setVisible(false);
        this.insertarMateriaTF = new TextField[6];
        this.insertarMateriaLB = new Label[6];
        this.insertarMateriaLB[0] = new Label("Codigo");
        this.insertarMateriaLB[1] = new Label("Nombre");
        this.insertarMateriaLB[2] = new Label("Creditos");
        this.insertarMateriaLB[3] = new Label("Tipologia");
        this.insertarMateriaLB[4] = new Label("Prerrequisitos");
        this.insertarMateriaLB[5] = new Label("Semestre");

        for (int i = 0; i < 6; i++) {
            this.insertarMateriaTF[i] = new TextField();
            this.insertarMateriaTF[i].setVisible(false);
            this.insertarMateriaLB[i].setVisible(false);
            layout.getChildren().add(this.insertarMateriaLB[i]);
            layout.getChildren().add(this.insertarMateriaTF[i]);
        }
        guardar.setOnAction(new Evento2(this.planActual));
        layout.getChildren().add(this.guardar);

        //EliminarMateria
        this.eliminarMateria = new Button("Eliminar Materia");
        this.eliminar = new Button("Eliminar");
        this.eliminar.setVisible(false);
        this.eliminarMateria.setOnAction(new Evento3());
        this.eliminar.setOnAction(new Evento4());
        layout.getChildren().add(this.eliminarMateria);
        layout.getChildren().add(this.eliminar);

    }

    private class Evento1 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            insertarMateria.setVisible(false);
            for (int i = 0; i < 6; i++) {
                insertarMateriaTF[i].setVisible(true);
                insertarMateriaLB[i].setVisible(true);
            }
            guardar.setVisible(true);
            Singleton singleton = new Singleton();
            Stage stage = singleton.getStage();
            stage.show();
        }
    }

    private class Evento2 implements EventHandler<ActionEvent> {

        private Plan planActual;

        public Evento2(Plan plan) {
            this.planActual = plan;
        }

        @Override
        public void handle(ActionEvent event) {
            for (int i = 0; i < 6; i++) {
                insertarMateriaTF[i].setVisible(false);
                insertarMateriaLB[i].setVisible(false);
            }
            guardar.setVisible(false);
            insertarMateria.setVisible(false);

            Materia materia = new Materia(Integer.parseInt(insertarMateriaTF[0].getText()), insertarMateriaTF[1].getText(), Integer.parseInt(insertarMateriaTF[2].getText()), insertarMateriaTF[3].getText(), insertarMateriaTF[4].getText());
            int semestre = Integer.parseInt(insertarMateriaTF[5].getText());
            materia.setSemestre(semestre);
            miplan.insertarMateria(this.planActual, materia);
            Button boton = new Button(materia.getName());
            columnas[semestre - 1].getChildren().add(boton);
            Singleton singleton = new Singleton();
            Stage stage = singleton.getStage();
            stage.show();
        }
    }

    private class Evento3 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            insertarMateriaTF[2].setVisible(true);
            insertarMateriaLB[2].setVisible(true);
            insertarMateriaTF[5].setVisible(true);
            insertarMateriaLB[5].setVisible(true);
            eliminarMateria.setVisible(true);
            eliminar.setVisible(true);
            Singleton singleton = new Singleton();
            Stage stage = singleton.getStage();
            stage.show();
            
        }
    }
    private class Evento4 implements EventHandler<ActionEvent> {

        private Plan planActual;

        public Evento4(Plan plan) {
            this.planActual = plan;
        }

        @Override
        public void handle(ActionEvent event) {
            eliminar.setVisible(false);
            eliminarMateria.setVisible(false);
            insertarMateriaTF[2].setVisible(false);
            insertarMateriaLB[2].setVisible(false);
            insertarMateriaTF[5].setVisible(false);
            insertarMateriaLB[5].setVisible(false);
            int semestre=Integer.parseInt(insertarMateriaTF[5].getText());
            miplan.eliminarMateria(this.planActual,Integer.parseInt(insertarMateriaTF[2].getText()));
            int i=0;
            while (){
                Button boton =(Button)columnas[semestre-1].getChildren().get(i);
            if(boton.getText()==){
                
            }
                    }
            Singleton singleton = new Singleton();
            Stage stage = singleton.getStage();
            stage.show();
        }
    }


    @Override
    public Scene getScena() {
        return this.escena;
    }

}
