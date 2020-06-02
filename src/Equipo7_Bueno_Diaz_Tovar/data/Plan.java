package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.*;
import java.text.Normalizer;
import java.util.*;

public class Plan {

    private String nombre;
    private int n_semestres;
    private ArrayList<Materia>[] semestres;
    private ArrayList<Materia> optativas;
    private ArrayList<Materia>[] materiasVistas;
    private AVLTree codigos;
    private int creditosDiscp;
    private int creditosFund;
    private int creditosElect;
    private int creditosTotales;
    private double PAPA;

    public Plan(String nombre, int creditosDiscp, int creditosFund, int creditosElect, int n_semestres) {
        this.nombre = nombre;
        this.creditosDiscp = creditosDiscp;
        this.creditosFund = creditosFund;
        this.creditosElect = creditosElect;
        this.n_semestres = n_semestres;
        this.semestres = new ArrayList[n_semestres];
        this.optativas = new ArrayList<>();
        this.codigos = new AVLTree();
        this.materiasVistas = new ArrayList[n_semestres];
        for(int i = 0; i < n_semestres; i++) {
            this.materiasVistas[i] = new ArrayList<>();
        }
    }

    public void cargarMaterias(FileInputStream file) {
        try (Scanner readFile = new Scanner(file)) {
            readFile.useDelimiter("/ ");
            readFile.nextLine();
            do {
                Materia materia = new Materia(readFile.nextInt(),Normalizer.normalize(readFile.next(), Normalizer.Form.NFD),
                        readFile.nextInt(), readFile.next(), readFile.next());
                int semestre = readFile.nextInt();
                readFile.nextLine();
                materia.setSemestre(semestre);
                if (semestre == 0) {
                    this.optativas.add(materia);
                } else {
                    if (semestres[semestre - 1] == null) {
                        ArrayList<Materia> semestreLista = new ArrayList<>();
                        this.semestres[semestre - 1] = semestreLista;
                        semestreLista.add(materia);
                    } else {
                        semestres[semestre - 1].add(materia);
                    }
                    AVLTreeNode temp = new AVLTreeNode(materia.getCodigo(), materia.getSemestre(),
                            this.semestres[materia.getSemestre() - 1].size() - 1);
                    this.codigos.add(temp.getCodigo(), temp.getSemestre(), temp.getPosición());
                }

            } while (readFile.hasNext());
        }
    }

    public AVLTree getCodigos() {
        return codigos;
    }

    public void setCodigos(AVLTree codigos) {
        this.codigos = codigos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getN_semestres() {
        return n_semestres;
    }

    public void setN_semestres(int n_semestres) {
        this.n_semestres = n_semestres;
    }

    public ArrayList<Materia>[] getSemestres() {
        return semestres;
    }

    public void setSemestres(ArrayList<Materia>[] semestres) {
        this.semestres = semestres;
    }

    public ArrayList<Materia> getOptativas() {
        return optativas;
    }

    public void setOptativas(ArrayList<Materia> optativas) {
        this.optativas = optativas;
    }

    public int getCreditosDiscp() {
        return creditosDiscp;
    }

    public void setCreditosDiscp(int creditosDiscp) {
        this.creditosDiscp = creditosDiscp;
    }

    public int getCreditosFund() {
        return creditosFund;
    }

    public void setCreditosFund(int creditosFund) {
        this.creditosFund = creditosFund;
    }

    public int getCreditosElect() {
        return creditosElect;
    }

    public void setCreditosElect(int creditosElect) {
        this.creditosElect = creditosElect;
    }

    public int getCreditosTotales() {
        return creditosTotales;
    }

    public void setCreditosTotales(int creditosTotales) {
        this.creditosTotales = creditosTotales;
    }

    public double getPAPA() {
        return PAPA;
    }

    public void setPAPA(double PAPA) {
        this.PAPA = PAPA;
    }

    public ArrayList<Materia>[] getMateriasVistas() {
        return materiasVistas;
    }

    public void setMateriasVistas(ArrayList<Materia>[] vistas) {
        this.materiasVistas = vistas;
    }

}
