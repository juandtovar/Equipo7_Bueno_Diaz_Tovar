package Equipo7_Bueno_Diaz_Tovar.data;

public class Materia {

    private String name;
    private int codigo;
    private int creditos;
    private String tipologia;
    private int semestre;
    private Chain<Integer> nota;
    private String prerrequisitos;

    public Materia(int codigo, String name, int creditos, String tipologia, String prerrequisitos) {
        this.name = name;
        this.codigo = codigo;
        this.creditos = creditos;
        this.tipologia = tipologia;
        this.prerrequisitos = prerrequisitos;
        this.nota = null;
    }

    public void insertarNota(int nota) {
        this.nota.add(nota, this.nota.getSize());
    }

    public String getName() {
        return name;
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

    public Chain<Integer> getNota() {
        return nota;
    }

    public void setNota(Chain<Integer> nota) {
        this.nota = nota;
    }

    public String getPrerrequisitos() {
        return prerrequisitos;
    }

    public void setPrerrequisitos(String prerrequisitos) {
        this.prerrequisitos = prerrequisitos;
    }

}
