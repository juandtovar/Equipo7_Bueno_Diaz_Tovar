package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.Plan;
import Equipo7_Bueno_Diaz_Tovar.logic.Main;

public class Controlador4 {

    private Vista4 vista;
    private Plan plan;

    public Controlador4(Plan plan) {
        this.plan = plan;
        this.vista = new Vista4(plan);
    }

    public Vista4 getVista() {
        return vista;
    }

    public void setVista(Vista4 vista) {
        this.vista = vista;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

}
