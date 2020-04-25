/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

public class Usuario {
 private String nombre;
 private Plan [] planes;

    public Usuario(String nombre, Plan[] planes) {
        this.nombre = nombre;
        this.planes = planes;
    }
 
    public void cambiarNombre (String nuevoNombre){
        this.nombre=nuevoNombre;
    }
    
    public boolean añadirPlan(Plan plan){
    for(int i=0; i<2; i++){
        if(planes[i]==null){
           planes[i]=plan;
           return true;
        }
    }
    return false;
    }
    public boolean eliminarPlan(Plan plan){
    for(int i=0; i<2; i++){
        if(planes[i]!=null){
          if(plan.getNombre().equals(planes[i].getNombre())){
              planes[i]=null;
              return true;
          }
    }
    }
        return false;
}
}
