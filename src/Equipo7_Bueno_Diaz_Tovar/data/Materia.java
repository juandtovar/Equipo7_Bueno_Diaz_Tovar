package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class Materia implements Serializable {

    private String name;
    private long codigo;
    private int creditos;
    private String tipologia;
    private int semestre;
    private final Chain<Double> nota;
    private Chain<Long> prerrequisitos;
    private boolean vista;

    public Materia(long codigo, String name, int creditos, String tipologia, int semestre) {
        this.name = name;
        this.codigo = codigo;
        this.creditos = creditos;
        this.tipologia = tipologia;
        this.prerrequisitos = new Chain<>();
        this.nota = new Chain<>();
        this.semestre = semestre;
        this.vista = false;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Chain<Double> getNota() {
        return this.nota;
    }

    public Double getLastNota() {
        return this.nota.get(this.nota.size() - 1);
    }

    public void setNota(double nota) {
        this.nota.add(nota, this.nota.size());
    }

    public Chain<Long> getPrerrequisitos() {
        return prerrequisitos;
    }

    public void setPrerrequisitos(Chain<Long> prerrequisitos) {
        this.prerrequisitos = prerrequisitos;
    }

    public boolean isVista() {
        return vista;
    }

    public void setVista() {
        this.vista = true;
    }

    @Override
    public String toString() {
        return String.valueOf(codigo);
    }

}
