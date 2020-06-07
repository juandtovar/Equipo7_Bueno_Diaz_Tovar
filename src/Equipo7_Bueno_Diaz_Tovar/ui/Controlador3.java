package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.Chain;
import Equipo7_Bueno_Diaz_Tovar.data.Plan;
import Equipo7_Bueno_Diaz_Tovar.logic.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controlador3 {

    private Vista3 vista;
    private Chain<Plan> planes;

    public Controlador3(Chain<Plan> planes, String plan) {
        Main.getPestañas().add(3);
        this.planes = planes;
        this.vista = new Vista3(planes, plan);
    }

    private class Evento3 implements EventHandler<ActionEvent> {

        Plan plan;

        public Evento3() {
            this.plan = vista.getPlanActual();
        }

        @Override
        public void handle(ActionEvent event) {
            
        }
    }

    public Vista3 getVista() {
        return vista;
    }

    public void setVista(Vista3 vista) {
        this.vista = vista;
    }

    public Chain<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(Chain<Plan> planes) {
        this.planes = planes;
    }

}
