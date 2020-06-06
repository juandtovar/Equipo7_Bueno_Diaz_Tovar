package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import java.util.ArrayList;

public class MiAvance {

    public static void actualizarNota(Plan plan, int codigo, int nota) {
        BinaryTreeNode<Identificador> materia = plan.getIdentificadores().find(new Identificador(codigo, nota, 0));
        int semestre = materia.getElement().getSemestre();
        int pos = materia.getElement().getPosición();
        if (plan.getSemestres()[semestre - 1].get(pos).getNota().size() == 0) {
            plan.getMateriasVistas()[semestre - 1].add(plan.getSemestres()[semestre - 1].get(pos));
        }
        plan.getSemestres()[semestre - 1].get(pos).setNota(nota);
    }

    public static double calcularPAPA(Plan plan) {
        int acumulado = 0;
        int creditosVistos = 0;
        for (int i = 0; i < plan.getN_semestres(); i++) {
            ArrayList<Materia> materiasSemestre = plan.getMateriasVistas()[i];
            if (!materiasSemestre.isEmpty()) {
                for (int j = 0; j < materiasSemestre.size(); j++) {
                    acumulado += plan.getMateriasVistas()[i].get(j).getLastNota() * plan.getMateriasVistas()[i].get(j).getCreditos();
                    creditosVistos += plan.getMateriasVistas()[i].get(j).getCreditos();
                }
            }
        }
        return acumulado / creditosVistos;
    }

    public static ArrayList<Double> calcularAvance(Plan plan) {
        double acumuladoFundamentacion = 0;
        double acumuladoDisciplinar = 0;
        double acumuladoElectivas = 0;
        for (int i = 0; i < plan.getN_semestres(); i++) {
            ArrayList<Materia> materiasSemestre = plan.getMateriasVistas()[i];
            try {
                if (materiasSemestre.get(0) != null) {
                    for (int j = 0; j < materiasSemestre.size(); j++) {
                        if ("FO".equals(plan.getMateriasVistas()[i].get(j).getTipologia())
                                || "FOpt".equals(plan.getMateriasVistas()[i].get(j).getTipologia())) {
                            acumuladoFundamentacion += plan.getMateriasVistas()[i].get(j).getCreditos();
                        } else if ("DO".equals(plan.getMateriasVistas()[i].get(j).getTipologia())
                                || "DOpt".equals(plan.getMateriasVistas()[i].get(j).getTipologia())) {
                            acumuladoDisciplinar += plan.getMateriasVistas()[i].get(j).getCreditos();
                        } else {
                            acumuladoElectivas += plan.getMateriasVistas()[i].get(j).getCreditos();
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
        ArrayList<Double> avance = new ArrayList<>();
        avance.add(acumuladoFundamentacion);
        avance.add(acumuladoDisciplinar);
        avance.add(acumuladoElectivas);
        avance.add(acumuladoFundamentacion + acumuladoDisciplinar + acumuladoElectivas);
        return avance;
    }

}
