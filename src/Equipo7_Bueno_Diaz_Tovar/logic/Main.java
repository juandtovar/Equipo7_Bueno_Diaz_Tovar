package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import Equipo7_Bueno_Diaz_Tovar.ui.*;
import java.io.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static LinkedStack<Integer> pestañas = new LinkedStack<>();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.printf("%s%d\n", "Inicio cargar materias = \t", System.currentTimeMillis());
        SingleLinkedList<Plan> planes = new SingleLinkedList<>();
        //CARGAR INFO BÁSICA PLAN

        try {
            FileInputStream planesFile = new FileInputStream("informacion_planes.txt");
            FileInputStream file, fileInput;
            File fichero;
            FileOutputStream fileOut;
            ObjectInputStream input;
            ObjectOutputStream out;
            Plan plan;
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
                        fileOut = new FileOutputStream("Info_" + plan.getNombre() + ".txt");
                        out = new ObjectOutputStream(fileOut);
                        out.writeObject(plan);
                    }

                    try {
                        fileInput = new FileInputStream("Info_" + plan.getNombre() + ".txt");
                        input = new ObjectInputStream(fileInput);
                        file = new FileInputStream(plan.getNombre() + ".txt");
                        plan.cargarMaterias(file);
                        readPlanes.nextLine();
                        Plan aux = (Plan) input.readObject();
                        if (MiAvance.calcularAvance(aux).get(3) != 0) {
                            plan = aux;
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.println(ex.toString());
                    }
                    System.out.printf("%s%d\n", "Plan cargado = \t\t\t", System.currentTimeMillis());
                    planes.add(plan, planes.size());
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
        stage.centerOnScreen();
        stage.setTitle("UNet tu malla personalizada");
        stage.show();

    }

    public static LinkedStack<Integer> getPestañas() {
        return pestañas;
    }

    public static void deletePestañas() {
        Main.pestañas.pop();
        System.out.println(pestañas);
    }

    public static void setPestañas(int i) {
        Main.pestañas.push(i);
        System.out.println(pestañas);
    }

}
