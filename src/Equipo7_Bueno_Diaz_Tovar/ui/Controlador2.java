package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import Equipo7_Bueno_Diaz_Tovar.logic.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controlador2 {

    private Vista2 vista;
    private SingleLinkedList<Plan> planes;
    private String plan;

    public Controlador2(SingleLinkedList<Plan> planes) {
        this.planes = planes;
        this.vista = new Vista2(this.planes);
        SingleLinkedListNode<Button> temp1 = this.vista.getBotones().getHead();
        SingleLinkedListNode<Plan> temp2 = this.planes.getHead();
        do {
            this.plan = temp2.getElement().getNombre();
            temp1.getElement().setOnAction(new Evento2(this.planes, this.plan));
            temp1 = temp1.getNext();
            temp2 = temp2.getNext();
        } while (temp1 != null);
    }

    private class Evento2 implements EventHandler<ActionEvent> {

        SingleLinkedList<Plan> planes;
        String plan;

        public Evento2(SingleLinkedList<Plan> planes, String plan) {
            this.planes = planes;
            this.plan = plan;
        }

        @Override
        public void handle(ActionEvent event) {
            Main.setPestañas(2);
            Controlador3 controlador = new Controlador3(this.planes, this.plan);
            Vista vista = controlador.getVista();
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(vista.getScena());
            stage.centerOnScreen();
            stage.show();
        }

    }

    public Vista2 getVista() {
        return vista;
    }

    public void setVista(Vista2 vista) {
        this.vista = vista;
    }

    public SingleLinkedList<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(SingleLinkedList<Plan> planes) {
        this.planes = planes;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

}
