/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

public class Materia {
    private String name;
    private int codigo;
    private int creditos;
    private String tipologia;
    private int semestre;
    private Chain<Integer> nota;
    private String prerrequisitos;
    //private Materia [] prerrequisitos;
    //private Materia [] desbloqueos;

    public Materia(int codigo,String name, int creditos, String tipologia, String prerrequisitos) {
        this.name = name;
        this.codigo = codigo;
        this.creditos = creditos;
        this.tipologia = tipologia;
        this.prerrequisitos=prerrequisitos;
        this.nota= new Chain <>();
    }
    
    public String getName() {
        return name;
    }
    public int getCodigo() {
        return codigo;
    }
    public int getCreditos() {
        return creditos;
    }
    public String getTipologia() {
        return tipologia;
    }
    public int getSemestre() {
        return semestre;
    }
    public Chain<Integer> getNota() {
        return nota;
    }
    public String getPrerrequisitos() {
        return prerrequisitos;
    }
    
    public void setSemestre(int semestre){
        this.semestre=semestre;
    }

    public void insertarNota(int nota) {
        this.nota.PushBack(nota);
    }  
    
}
