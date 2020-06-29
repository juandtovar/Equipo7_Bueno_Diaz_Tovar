package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.*;

public class Plan implements Serializable {

    private String nombre;
    private int n_semestres;
    private SingleLinkedList<Materia>[] semestres;
    private SingleLinkedList<Materia> optativas;
    private SingleLinkedList<Materia>[] materiasVistas;
    private Set<Materia> materias;
    private long[] numeroDesbloqueos;
    private int creditosDiscp;
    private int creditosFund;
    private int creditosElect;
    private int creditosTotales;
    private double PAPA;
    private int maxMaterias;
    private int nMaterias;
    private MaxHeap<Materia> materiasUrgentes;

    public Plan(String nombre, int creditosDiscp, int creditosFund, int creditosElect, int n_semestres, int maxMaterias, int nMaterias) {
        this.nombre = nombre;
        this.creditosDiscp = creditosDiscp;
        this.creditosFund = creditosFund;
        this.creditosElect = creditosElect;
        this.creditosTotales = this.creditosDiscp + this.creditosFund + this.creditosElect;
        this.n_semestres = n_semestres;
        this.semestres = new SingleLinkedList[n_semestres];
        this.optativas = new SingleLinkedList<>();
        this.materiasVistas = new SingleLinkedList[n_semestres];
        for (int i = 0; i < n_semestres; i++) {
            this.materiasVistas[i] = new SingleLinkedList<>();
        }
        this.maxMaterias = maxMaterias;
        this.nMaterias = nMaterias;
        this.materias = new Set(2 * nMaterias + 1);
        this.numeroDesbloqueos = new long[nMaterias];
        this.materiasUrgentes = new MaxHeap<>(nMaterias);
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
                        int posTabla = this.materias.find(new Materia(cod, "", 0, "", 0));
                        this.materias.getTable()[posTabla].setDesbloqueos();
                    } catch (Exception ex) {
                        break;
                    }
                }
                try {
                    readFile.nextLine();
                } catch (Exception ex) {

                }
                int semestre = materia.getSemestre();
                if (semestre == 0) {
                    this.optativas.add(materia);
                } else {
                    if (semestres[semestre - 1] == null) {
                        SingleLinkedList<Materia> semestreLista = new SingleLinkedList<>();
                        this.semestres[semestre - 1] = semestreLista;
                        semestreLista.add(materia);
                    } else {
                        semestres[semestre - 1].add(materia);
                    }
                    int pos = this.semestres[semestre - 1].size() - 1;
                    materia.setPos(pos);
                    this.materias.put(materia);
                }
            } while (readFile.hasNext());
        }
        for (int i = 0; i < this.getN_semestres(); i++) {
            for (int j = 0; j < this.getSemestres()[i].size(); j++) {
                this.materiasUrgentes.add(this.getSemestres()[i].get(j));
            }
        }
    }

    public String getNombre() {
        return this.nombre;
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

    public SingleLinkedList<Materia>[] getSemestres() {
        return semestres;
    }

    public void setSemestres(SingleLinkedList<Materia>[] semestres) {
        this.semestres = semestres;
    }

    public SingleLinkedList<Materia> getOptativas() {
        return optativas;
    }

    public void setOptativas(SingleLinkedList<Materia> optativas) {
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

    public SingleLinkedList<Materia>[] getMateriasVistas() {
        return materiasVistas;
    }

    public void setMateriasVistas(SingleLinkedList<Materia>[] vistas) {
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

    public long[] getNumeroDesbloqueos() {
        return numeroDesbloqueos;
    }

    public void setNumeroDesbloqueos(long[] numeroDesbloqueos) {
        this.numeroDesbloqueos = numeroDesbloqueos;
    }

    public MaxHeap<Materia> getMateriasUrgentes() {
        return materiasUrgentes;
    }

    public void setMateriasUrgentes(MaxHeap<Materia> materiasUrgentes) {
        this.materiasUrgentes = materiasUrgentes;
    }

    public Set<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(Equipo7_Bueno_Diaz_Tovar.data.Set<Materia> materias) {
        this.materias = materias;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < n_semestres; i++) {
            s += "[";
            SingleLinkedList<Materia> sem = this.getSemestres()[i];
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
