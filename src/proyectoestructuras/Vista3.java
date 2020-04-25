/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Profesor
 */
public class Vista3 implements Vista{
    private Scene escena;
    private Chain <Plan> planes;
    private String planAImprimir;
    private Plan plan_Imprimir;
    //private ChainNode<Button> botones;
    

    public Vista3(Chain <Plan> planes, String plan) {
        this.planes=planes;
        HBox layoutHor= new HBox ();
        ChainNode <Plan> temp1= new ChainNode <>();
        temp1=this.planes.head;
        boolean condicion=true;
        while(condicion){
            if(temp1.element.getNombre().equals(plan)){
                plan_Imprimir=temp1.element;
                System.out.println(temp1.element.getNombre());
            }
            temp1=temp1.next;
            if(temp1==null){
                condicion=false;
            }
        }
        ChainNode <Materia> temp2=new ChainNode <>();
        for(int i=0;i<plan_Imprimir.getN_semestres()-1;i++){
            condicion=true;
            temp2=plan_Imprimir.getSemestres()[i].head;
            System.out.println(temp2.element.getName());
            VBox columna= new VBox();
            columna.getChildren().add(new Label("Semestre"+" "+(i+1)));
            while(condicion){
                Button boton=new Button (temp2.element.getName());
                columna.getChildren().add(boton);
                //boton.setOnAction(new Evento());
                temp2=temp2.next;
                if(temp2==null){
                    condicion=false;
                }
            }
            layoutHor.getChildren().add(columna);
        }
        this.escena = new Scene(layoutHor, 500, 500);
        }

    @Override
    public Scene getScena() {
        return this.escena;  
    }
    /*private class Evento implements
            EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            
        }*/   
    
    }

