package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.Chain;
import Equipo7_Bueno_Diaz_Tovar.data.Plan;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controlador3 {

    private Vista3 vista;
    private Chain<Plan> planes;

    public Controlador3(Chain<Plan> planes, String plan) {
        this.planes = planes;
        this.vista = new Vista3(planes, plan);
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
