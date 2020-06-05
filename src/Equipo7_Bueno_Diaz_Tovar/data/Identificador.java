package Equipo7_Bueno_Diaz_Tovar.data;

public class Identificador implements Comparable<Identificador> {

    private int codigo;
    private int semestre;
    private int posición;

    public Identificador(int codigo, int semestre, int posición) {
        this.codigo = codigo;
        this.semestre = semestre;
        this.posición = posición;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getPosición() {
        return posición;
    }

    public void setPosición(int posición) {
        this.posición = posición;
    }

    @Override
    public int compareTo(Identificador o) {
        if (this.codigo < o.codigo) {
            return -1;
        } else if (this.codigo > o.codigo) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.codigo) + ' ' + String.valueOf(this.getPosición()) + ' ' + String.valueOf(this.getSemestre());
    }
}
