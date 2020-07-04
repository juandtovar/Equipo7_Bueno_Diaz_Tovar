package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class Materia implements Serializable, Comparable<Materia> {

    private String name;
    private long codigo;
    private int creditos;
    private String tipologia;
    private int semestre;
    private int pos;
    private final SingleLinkedList<Double> nota;
    private SingleLinkedList<Long> prerrequisitos;
    private SingleLinkedList<Long> desbloqueos;
    private boolean vista;

    public Materia(long codigo, String name, int creditos, String tipologia, int semestre) {
        this.name = name;
        this.codigo = codigo;
        this.creditos = creditos;
        this.tipologia = tipologia;
        this.prerrequisitos = new SingleLinkedList<>();
        this.nota = new SingleLinkedList<>();
        this.semestre = semestre;
        this.vista = false;
        this.desbloqueos = new SingleLinkedList<>();
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

    public SingleLinkedList<Long> getDesbloqueos() {
        return desbloqueos;
    }

    public void setDesbloqueos(SingleLinkedList<Long> desbloqueos) {
        this.desbloqueos = desbloqueos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public int hashCode() {
        return (23 * (int) this.codigo + 3) % 12990007;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Materia other = (Materia) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.name) + " "
                + String.valueOf(this.codigo) + " "
                + this.getPrerrequisitos().toString() + " "
                + this.getDesbloqueos().toString() + '\n';
    }

    @Override
    public int compareTo(Materia o) {
        if (this.getLastNota() != null) {
            if (this.getLastNota() >= 3) {
                return 1;
            }
        } else if (this.getSemestre() < o.getSemestre()) {
            return 1;
        } else if (this.getSemestre() > o.getSemestre()) {
            return -1;
        } else if (this.getDesbloqueos().size() > o.getDesbloqueos().size()) {
            return 1;
        } else if (this.getDesbloqueos().size() < o.getDesbloqueos().size()) {
            return -1;
        } else if (this.getCodigo() < o.getCodigo()) {
            return 1;
        } else if (this.getCodigo() > o.getCodigo()) {
            return -1;
        }
        return 0;
    }

}
