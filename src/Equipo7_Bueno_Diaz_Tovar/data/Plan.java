package Equipo7_Bueno_Diaz_Tovar.data;

import Equipo7_Bueno_Diaz_Tovar.data.Chain;
import java.io.*;
import java.util.*;

public class Plan {

    private String nombre;
    private int n_semestres;
    private Chain<Materia>[] semestres;
    private Chain<Materia> optativas;
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
        this.semestres = new Chain[n_semestres];
        this.optativas = new Chain<>();
    }

    public void cargarMaterias(FileInputStream file) {
        Scanner readFile = new Scanner(file);
        readFile.useDelimiter("/ ");
        readFile.nextLine();
        int i = 1;
        do {
            Materia materia = new Materia(readFile.nextInt(), readFile.next(),
                    readFile.nextInt(), readFile.next(), readFile.next());
            int semestre = readFile.nextInt();
            readFile.nextLine();
            materia.setSemestre(semestre);
            if (semestre == 0) {
                this.optativas.PushBack(materia);
            } else {
                if (semestres[semestre - 1] == null) {
                    Chain<Materia> semestreLista = new Chain<>();
                    this.semestres[semestre - 1] = semestreLista;
                    semestreLista.PushBack(materia);
                } else {
                    semestres[semestre - 1].PushBack(materia);
                }
            }
            i++;
        } while (readFile.hasNext());
        readFile.close();
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

    public Chain<Materia>[] getSemestres() {
        return semestres;
    }

    public void setSemestres(Chain<Materia>[] semestres) {
        this.semestres = semestres;
    }

    public Chain<Materia> getOptativas() {
        return optativas;
    }

    public void setOptativas(Chain<Materia> optativas) {
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

}
