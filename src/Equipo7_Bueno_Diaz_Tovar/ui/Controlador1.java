package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.SingleLinkedList;
import Equipo7_Bueno_Diaz_Tovar.data.Plan;
import Equipo7_Bueno_Diaz_Tovar.logic.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Controlador1 {

    private Vista1 vista;
    private SingleLinkedList<Plan> planes;

    public Controlador1(SingleLinkedList<Plan> planes) {
        this.vista = new Vista1();
        this.planes = planes;
        this.vista.getBtCrearPlan().setOnAction(new Evento1());
    }

    private class Evento1 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Main.setPestañas(1);
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            Controlador2 controlador = new Controlador2(planes);
            Vista vista = controlador.getVista();
            stage.setScene(vista.getScena());
            stage.show();
        }
    }

    public Vista1 getVista() {
        return vista;
    }

    public void setVista(Vista1 vista) {
        this.vista = vista;
    }

    public SingleLinkedList<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(SingleLinkedList<Plan> planes) {
        this.planes = planes;
    }

}
