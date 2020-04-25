/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Profesor
 */
public class Controlador2 {
    private Vista2 vista;
    Chain <Plan> planes;
    String plan;

    public Controlador2(Chain <Plan> planes) {
      this.planes=planes;
      this.vista = new Vista2(planes);
     ChainNode <Button> temp1=this.vista.getBotones().head;
     ChainNode <Plan> temp2= planes.head;
     boolean condicion=true;
      while(condicion){
          this.plan=temp2.element.getNombre();
          temp1.element.setOnAction(new Evento());
          temp1=temp1.next;
          temp2=temp2.next;
          if(temp1==null){
              condicion=false;
          }
      } 
    }

    public Vista2 getVista() {
        return vista;
    }
    
    private class Evento implements
            EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Controlador3 controlador = 
                    new Controlador3(planes,plan);
            Vista vista = controlador.getVista();
            Singleton singleton = 
                    Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(vista.getScena());
            stage.show();
        }
    
    
    }
    
}
