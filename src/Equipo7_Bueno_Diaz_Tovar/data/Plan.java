package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.*;

public class Plan implements Serializable {

    private String nombre;
    private int n_semestres;
    private Chain<Materia>[] semestres;
    private Chain<Materia> optativas;
    private Chain<Materia>[] materiasVistas;
    private LinkedAVLTree<Identificador> identificadores;
    private int creditosDiscp;
    private int creditosFund;
    private int creditosElect;
    private int creditosTotales;
    private double PAPA;
    private int maxMaterias;
    private int nMaterias;

    public Plan(String nombre, int creditosDiscp, int creditosFund, int creditosElect, int n_semestres, int maxMaterias, int nMaterias) {
        this.nombre = nombre;
        this.creditosDiscp = creditosDiscp;
        this.creditosFund = creditosFund;
        this.creditosElect = creditosElect;
        this.creditosTotales = this.creditosDiscp + this.creditosFund + this.creditosElect;
        this.n_semestres = n_semestres;
        this.semestres = new Chain[n_semestres];
        this.optativas = new Chain<>();
        this.identificadores = new LinkedAVLTree();
        this.materiasVistas = new Chain[n_semestres];
        for (int i = 0; i < n_semestres; i++) {
            this.materiasVistas[i] = new Chain<>();
        }
        this.maxMaterias = maxMaterias;
        this.nMaterias = nMaterias;
    }

    public void cargarMaterias(FileInputStream file) {
        try (Scanner readFile = new Scanner(file)) {
            readFile.useDelimiter("/ ");
            readFile.nextLine();
            do {
                Materia materia = new Materia(readFile.nextLong(),
                        readFile.next(),
                        readFile.nextInt(),
                        readFile.next(),
                        readFile.nextInt());
                long cod;
                while (true) {
                    try {
                        cod = readFile.nextLong();
                        materia.getPrerrequisitos().add(cod);
                    } catch (InputMismatchException ex) {
                        break;
                    }
                }
                readFile.nextLine();
                int semestre = materia.getSemestre();
                if (semestre == 0) {
                    this.optativas.add(materia);
                } else {
                    if (semestres[semestre - 1] == null) {
                        Chain<Materia> semestreLista = new Chain<>();
                        this.semestres[semestre - 1] = semestreLista;
                        semestreLista.add(materia);
                    } else {
                        semestres[semestre - 1].add(materia);
                    }
                    Identificador identificador = new Identificador(materia.getCodigo(),
                            materia.getSemestre(),
                            this.semestres[materia.getSemestre() - 1].size() - 1);
                    this.identificadores.add(identificador);
                }

            } while (readFile.hasNext());
        }
    }

    public LinkedAVLTree getIdentificadores() {
        return this.identificadores;
    }

    public void setIdentificadores(LinkedAVLTree identificadores) {
        this.identificadores = identificadores;
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

    public Chain<Materia>[] getMateriasVistas() {
        return materiasVistas;
    }

    public void setMateriasVistas(Chain<Materia>[] vistas) {
        this.materiasVistas = vistas;
    }

    public int getMaxMaterias() {
        return this.maxMaterias;
    }

    public void setMaxMaterias(int maxMaterias) {
        this.maxMaterias = maxMaterias;
    }

    public int getnMaterias() {
        return nMaterias;
    }

    public void setnMaterias(int nMaterias) {
        this.nMaterias = nMaterias;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < n_semestres; i++) {
            s += "[";
            Chain<Materia> sem = this.getSemestres()[i];
            for (int j = 0; j < sem.size(); j++) {
                if (j == sem.size() - 1) {
                    s += sem.get(j).toString();
                } else {
                    s += sem.get(j).toString() + ", ";
                }
            }
            s += "]";
            if (i != n_semestres - 1) {
                s += '\n';
            }
        }
        return s;
    }

}
