/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author usuario
 */
public class Interfaz extends Application {
    public static void main(String[] args) {
    Application.launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Chain <Plan> planes =new Chain <>(); 
        try{
            FileInputStream planesFile=new FileInputStream("informacion_planes.txt");
            Scanner readPlanes=new Scanner (planesFile);
            readPlanes.useDelimiter("/ ");
            System.out.println(readPlanes.nextLine());
            while(readPlanes.hasNext()){
                Plan plan=new Plan (readPlanes.next(),readPlanes.nextInt(),readPlanes.nextInt(),readPlanes.nextInt(),readPlanes.nextInt());
                System.out.println(plan.getNombre());
                planes.PushBack(plan);
                System.out.println(plan.getNombre()+".txt");
                FileInputStream file=new FileInputStream(plan.getNombre()+".txt");
                plan.cargarMaterias(file);
                readPlanes.nextLine();
                }
            readPlanes.close();
        }catch (FileNotFoundException ex) {
        }
      Singleton singleton = Singleton.getSingleton();
      singleton.setStage(stage);
      Controlador1 controlador = new Controlador1(planes);
      Vista vista = controlador.getVista();
      stage.setScene(vista.getScena());
      stage.show();
    }
}

