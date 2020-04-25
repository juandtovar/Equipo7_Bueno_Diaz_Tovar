/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Profesor
 */
public class Vista1 implements Vista{
    private Scene escena;
    private Label titulo;
    private Button btCrearPlan;
    private VBox layoutPrincipal;

    public Vista1() {
      layoutPrincipal= new VBox ();
      titulo=new Label ("Bienvenido a UNet");
      btCrearPlan = new Button("Siguiente");
      layoutPrincipal.getChildren().add(titulo);
      btCrearPlan=new Button("Crear plan");
      layoutPrincipal.getChildren().add(btCrearPlan);
      this.escena = new Scene(layoutPrincipal, 500, 500);
    }
    @Override
    public Scene getScena() {
        return this.escena;
    }
    public Label getTitulo() {
        return titulo;
    }
    public Button getBtCrearPlan() {
        return btCrearPlan;
    }

    
  
    
}
