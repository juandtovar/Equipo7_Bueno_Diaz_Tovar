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
public class Vista3 implements Vista{
    private Scene escena;
    Chain <Plan> planes;
    String planImprimir;
    

    public Vista3(Chain <Plan> planes, String plan) {
        this.planes=planes;
        ChainNode <Plan> temp= new ChainNode <>();
        temp=planes.head;
        while(temp.next!=null){
            
        }
        
        }

    @Override
    public Scene getScena() {
        return this.escena;  
    }   
}
