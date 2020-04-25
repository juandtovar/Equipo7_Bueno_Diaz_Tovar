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
public class Controlador3 {
    private Vista3 vista;
    Chain <Plan> planes;

    public Controlador3(Chain <Plan> planes, String plan) {
      this.planes=planes;
      this.vista = new Vista3(planes,plan);
    }
    public Vista3 getVista() {
        return vista;
    }   
}
