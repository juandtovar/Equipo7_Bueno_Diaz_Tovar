/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Profesor
 */
public class Vista2 implements Vista{
    private Scene escena;
    private Label elegir;
    private Chain <Button>botones ;
    Chain <Plan> planes;
    

    public Vista2(Chain <Plan> planes) {
        botones= new Chain <>();
        this.planes=planes;
        this.elegir = new Label("Eliga su plan");
        VBox layout= new VBox ();
        layout.getChildren().add(elegir);
        ChainNode <Plan> temp=planes.head;
        boolean condicion=true;
        while(condicion){
            Button boton =new Button (temp.element.getNombre());
            System.out.println(boton.getText());
            layout.getChildren().add(boton);
            botones.PushBack(boton);
            temp=temp.next;
            if(temp==null){
                condicion=false;
            }
        }
        this.escena = new Scene(layout, 500, 500);
    }
    @Override
    public Scene getScena() {
      return this.escena;  
    }
    
    public Label getElegir() {
        return elegir;
    }
    public Chain<Button> getBotones() {
        return botones;
    }
    
    
    
}
