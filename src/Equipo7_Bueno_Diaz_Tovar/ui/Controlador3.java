package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.Chain;
import Equipo7_Bueno_Diaz_Tovar.data.Plan;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Controlador3 {

    private Vista3 vista;
    private Chain<Plan> planes;

    public Controlador3(Chain<Plan> planes, String plan) {
        this.planes = planes;
        this.vista = new Vista3(planes, plan);
        this.vista.getMi_avance().setOnAction(new Evento3());
    }
    
    private class Evento3 implements EventHandler<ActionEvent> {

        Plan plan;

        public Evento3() {
            this.plan = vista.getPlanActual();
        }

        @Override
        public void handle(ActionEvent event) {
            Controlador4 controlador = new Controlador4(this.plan);
            Vista4 vista = controlador.getVista();
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(vista.getScena());
            stage.show();
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
