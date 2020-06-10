package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class Materia implements Serializable {

    private String name;
    private int codigo;
    private int creditos;
    private String tipologia;
    private int semestre;
    private Chain<Double> nota;
    private String prerrequisitos;

    public Materia(int codigo, String name, int creditos, String tipologia, String prerrequisitos, int semestre) {
        this.name = name;
        this.codigo = codigo;
        this.creditos = creditos;
        this.tipologia = tipologia;
        this.prerrequisitos = prerrequisitos;
        this.nota = new Chain<>();
        this.semestre = semestre;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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
        return this.nota.getTail().getElement();
    }

    public void setNota(double nota) {
        this.nota.add(nota, this.nota.getSize());
    }

    public String getPrerrequisitos() {
        return prerrequisitos;
    }

    public void setPrerrequisitos(String prerrequisitos) {
        this.prerrequisitos = prerrequisitos;
    }

    @Override
    public String toString() {
        return codigo + "-" + nota.toString() + '\n';
    }

}
