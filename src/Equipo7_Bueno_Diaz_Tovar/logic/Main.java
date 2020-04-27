package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import Equipo7_Bueno_Diaz_Tovar.ui.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.printf("%s%d\n", "Inicio cargar materias = \t", System.currentTimeMillis());
        Chain<Plan> planes = new Chain<>();
        try {
            FileInputStream planesFile = new FileInputStream("informacion_planes.txt");
            try (Scanner readPlanes = new Scanner(planesFile)) {
                readPlanes.useDelimiter("/ ");
                readPlanes.nextLine();
                while (readPlanes.hasNext()) {
                    Plan plan = new Plan(readPlanes.next(), readPlanes.nextInt(),
                            readPlanes.nextInt(), readPlanes.nextInt(), readPlanes.nextInt());
                    planes.add(plan, planes.getSize());
                    FileInputStream file = new FileInputStream(plan.getNombre() + ".txt");
                    plan.cargarMaterias(file);
                    System.out.printf("%s%d\n", "Plan cargado = \t\t\t", System.currentTimeMillis());
                    readPlanes.nextLine();
                }
            }
        } catch (FileNotFoundException ex) {

        }
        Singleton singleton = Singleton.getSingleton();
        singleton.setStage(stage);
        Controlador1 controlador = new Controlador1(planes);
        Vista vista = controlador.getVista();
        stage.setScene(vista.getScena());
        stage.show();
    }

}
