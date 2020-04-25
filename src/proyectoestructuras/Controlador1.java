/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 *
 * @author Profesor
 */
public class Controlador1 {
    private Vista1 vista;
    Chain <Plan> planes;

    public Controlador1(Chain <Plan> planes) {
        this.vista = new Vista1();
        this.planes=planes;
        this.vista.getBtCrearPlan().setOnAction(new Evento1());
    }

    public Vista1 getVista() {
        return vista;
    }
     
    
    //Registro eventos
    private class Evento1 
            implements  EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
          
          Singleton singleton = Singleton.getSingleton();
          Stage stage = singleton.getStage();          
          Controlador2 controlador = new Controlador2(planes);
          Vista vista = controlador.getVista();
          stage.setScene(vista.getScena());
          stage.show();
        }
    
    }
}
