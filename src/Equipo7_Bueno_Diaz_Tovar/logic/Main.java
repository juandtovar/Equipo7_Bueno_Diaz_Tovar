package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import Equipo7_Bueno_Diaz_Tovar.ui.*;
import java.io.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static Pila<Integer> pestañas = new Pila<>();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.printf("%s%d\n", "Inicio cargar materias = \t", System.currentTimeMillis());
        Chain<Plan> planes = new Chain<>();
        //CARGAR INFO BÁSICA PLAN
        try {
            FileInputStream planesFile = new FileInputStream("informacion_planes.txt");
            FileInputStream file, fileInput;
            File fichero;
            FileOutputStream fileOut;
            ObjectInputStream input;
            ObjectOutputStream out;
            Plan plan = null;
            try (Scanner readPlanes = new Scanner(planesFile)) {
                readPlanes.useDelimiter("/ ");
                readPlanes.nextLine();
                while (readPlanes.hasNext()) {
                    plan = new Plan(readPlanes.next(), readPlanes.nextInt(),
                            readPlanes.nextInt(), readPlanes.nextInt(), readPlanes.nextInt(),
                            readPlanes.nextInt(), readPlanes.nextInt());

                    fichero = new File("Info_" + plan.getNombre() + ".txt");

                    if (fichero.length() == 0) {
                        System.out.println("Vacio");
                        fileOut = new FileOutputStream(fichero);
                        out = new ObjectOutputStream(fileOut);
                        out.writeObject(plan);
                    }
                    
                    try {
                        fileInput = new FileInputStream("Info_" + plan.getNombre() + ".txt");
                        input = new ObjectInputStream(fileInput);
                        file = new FileInputStream(plan.getNombre() + ".txt");
                        plan.cargarMaterias(file);
                        readPlanes.nextLine();
                        plan = (Plan) input.readObject();
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }

                    System.out.printf("%s%d\n", "Plan cargado = \t\t\t", System.currentTimeMillis());
                    planes.add(plan, planes.getSize());
                }
            }
        } catch (FileNotFoundException ex) {

        }

        //CARGAR INFO PERSONAL
        Singleton singleton = Singleton.getSingleton();
        singleton.setStage(stage);
        Controlador1 controlador = new Controlador1(planes);
        Vista vista = controlador.getVista();
        stage.setScene(vista.getScena());
        stage.setTitle("Unet tu malla personalizada");
        stage.show();

    }

    public static Pila<Integer> getPestañas() {
        return pestañas;
    }

}
