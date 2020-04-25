/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Profesor
 */
public class Vista3 implements Vista{
    private Scene escena;
    private Chain <Plan> planes;
    private String planAImprimir;
    private Plan plan_Imprimir;
    

    public Vista3(Chain <Plan> planes, String plan) {
        this.planes=planes;
        HBox layoutHor= new HBox ();
        ChainNode <Plan> temp= new ChainNode <>();
        temp=planes.head;
        boolean condicion=true;
        while(condicion){
            if(temp.element.getNombre().equals(plan)){
                plan_Imprimir=temp.element;
            }
            temp=temp.next;
            if(temp==null){
                condicion=false;
            }
        }
        VBox [] columnas= new VBox [N_semestres];
        for(int i=0;i<plan_Imprimir.getN_semestres();i++){
            
            layoutHor.getChildren().add(columna);
            
        }
        
        }

    @Override
    public Scene getScena() {
        return this.escena;  
    }   
}
