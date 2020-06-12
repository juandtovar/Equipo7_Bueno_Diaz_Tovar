package Equipo7_Bueno_Diaz_Tovar.ui;

import Equipo7_Bueno_Diaz_Tovar.data.SingleLinkedList;
import Equipo7_Bueno_Diaz_Tovar.data.Plan;

public class Controlador3 {

    private Vista3 vista;
    private SingleLinkedList<Plan> planes;

    public Controlador3(SingleLinkedList<Plan> planes, String plan) {
        this.planes = planes;
        this.vista = new Vista3(planes, plan);
    }

    public Vista3 getVista() {
        return vista;
    }

    public void setVista(Vista3 vista) {
        this.vista = vista;
    }

    public SingleLinkedList<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(SingleLinkedList<Plan> planes) {
        this.planes = planes;
    }

}
