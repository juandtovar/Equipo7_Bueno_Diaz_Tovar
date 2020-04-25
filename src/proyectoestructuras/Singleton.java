/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import javafx.stage.Stage;

/**
 *
 * @author Profesor
 */
public class Singleton {
    private static Singleton singleton;
    private Stage stage;
    public static Singleton getSingleton(){
      if(singleton == null)
          singleton = new Singleton();
      return singleton;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
