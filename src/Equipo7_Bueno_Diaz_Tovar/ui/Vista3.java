package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Vista3 implements Vista{

    private Scene escena;
    private Chain<Plan> planes;
    private String planAImprimir;
    private Plan plan_Imprimir;

    public Vista3(Chain<Plan> planes, String plan) {
        this.planes = planes;
        VBox layout = new VBox();
        HBox layoutHor = new HBox();
        ChainNode<Plan> temp1 = new ChainNode<>();
        temp1 = this.planes.getHead();
        do {
            if (temp1.getElement().getNombre().equals(plan)) {
                plan_Imprimir = temp1.getElement();
            }
            temp1 = temp1.getNext();
        } while(temp1 != null);
        ChainNode<Materia> temp2 = new ChainNode<>();
        for (int i = 0; i < plan_Imprimir.getN_semestres() - 1; i++) {
            temp2 = plan_Imprimir.getSemestres()[i].getHead();
            VBox columna = new VBox();
            columna.getChildren().add(new Label("Semestre" + " " + (i + 1)));
            do {
                Button boton = new Button(temp2.getElement().getName());
                columna.getChildren().add(boton);
                temp2 = temp2.getNext();
            }while(temp2 != null);
            layoutHor.getChildren().add(columna);
        }
        
        Button insertarMateria = new Button("Insertar materia de libre elección");
        insertarMateria.setOnAction(new Evento());
        layout.getChildren().add(layoutHor);
        layout.getChildren().add(insertarMateria);
        this.escena = new Scene(layout, 500, 500);
    }
    
    private class Evento implements EventHandler<ActionEvent> {

        Chain<Plan> planes;
        String plan;

        public Evento() {
            
        }

        @Override
        public void handle(ActionEvent event) {
            
        }

    }
    
    @Override
    public Scene getScena() {
        return this.escena;
    }

}
