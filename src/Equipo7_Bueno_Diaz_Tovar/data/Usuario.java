package Equipo7_Bueno_Diaz_Tovar.data;

public class Usuario {

    private String nombre;
    private Plan[] planes;

    public Usuario(String nombre, Plan[] planes) {
        this.nombre = nombre;
        this.planes = planes;
    }

    public boolean añadirPlan(Plan plan) {
        for (int i = 0; i < 2; i++) {
            if (planes[i] == null) {
                planes[i] = plan;
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPlan(Plan plan) {
        for (int i = 0; i < 2; i++) {
            if (planes[i] != null) {
                if (plan.getNombre().equals(planes[i].getNombre())) {
                    planes[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Plan[] getPlanes() {
        return planes;
    }

    public void setPlanes(Plan[] planes) {
        this.planes = planes;
    }

}
