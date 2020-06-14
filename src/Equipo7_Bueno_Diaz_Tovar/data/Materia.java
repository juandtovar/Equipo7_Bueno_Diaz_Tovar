package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class Materia implements Serializable, Comparable<Materia> {

    private String name;
    private long codigo;
    private int creditos;
    private String tipologia;
    private int semestre;
    private final SingleLinkedList<Double> nota;
    private SingleLinkedList<Long> prerrequisitos;
    private boolean vista;
    private int desbloqueos;

    public Materia(long codigo, String name, int creditos, String tipologia, int semestre) {
        this.name = name;
        this.codigo = codigo;
        this.creditos = creditos;
        this.tipologia = tipologia;
        this.prerrequisitos = new SingleLinkedList<>();
        this.nota = new SingleLinkedList<>();
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

    public SingleLinkedList<Double> getNota() {
        return this.nota;
    }

    public Double getLastNota() {
        return this.nota.get(this.nota.size() - 1);
    }

    public void setNota(double nota) {
        this.nota.add(nota, this.nota.size());
    }

    public SingleLinkedList<Long> getPrerrequisitos() {
        return prerrequisitos;
    }

    public void setPrerrequisitos(SingleLinkedList<Long> prerrequisitos) {
        this.prerrequisitos = prerrequisitos;
    }

    public boolean isVista() {
        return vista;
    }

    public void setVista() {
        this.vista = true;
    }

    public long getDesbloqueos() {
        return desbloqueos;
    }

    public void setDesbloqueos(int desbloqueos) {
        this.desbloqueos = desbloqueos;
    }

    public void setDesbloqueos() {
        this.desbloqueos++;
    }

    @Override
    public String toString() {
        return String.valueOf(codigo);
    }

    @Override
    public int compareTo(Materia o) {
        if (this.isVista()) {
            return 1;
        } else if (this.getSemestre() < o.getSemestre()) {
            return 1;
        } else if (this.getSemestre() > o.getSemestre()) {
            return -1;
        } else if (this.getDesbloqueos() > o.getDesbloqueos()) {
            return 1;
        } else if (this.getDesbloqueos() < o.getDesbloqueos()) {
            return -1;
        } else if (this.getCodigo() > o.getCodigo()) {
            return 1;
        } else {
            return -1;
        }
    }

}
