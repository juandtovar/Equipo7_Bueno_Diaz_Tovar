package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.AVLTreeNode;
import Equipo7_Bueno_Diaz_Tovar.data.Materia;
import Equipo7_Bueno_Diaz_Tovar.data.Plan;
import java.util.ArrayList;

public class MiAvance {

    public static void actualizarNota(Plan plan, int codigo, int nota) {
        AVLTreeNode materia = plan.getCodigos().find(codigo);
        int semestre = materia.getSemestre();
        int pos = materia.getPosición();
        plan.getSemestres()[semestre - 1].get(pos).setNota(nota);
        if (plan.getSemestres()[semestre - 1].get(pos).getNota().size() == 0) {
            plan.getMateriasVistas()[semestre - 1].add(plan.getSemestres()[semestre - 1].get(pos));
        }
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

    public static double[] calcularAvance(Plan plan) {
        double acumuladoFundamentacion = 0;
        double acumuladoDisciplinar = 0;
        double acumuladoElectivas = 0;
        int creditosVistos = 0;
        for (int i = 0; i < plan.getN_semestres(); i++) {
            ArrayList<Materia> materiasSemestre = plan.getMateriasVistas()[i];
            if (materiasSemestre.get(0) != null) {
                for (int j = 0; j < materiasSemestre.size(); j++) {
                    if (plan.getMateriasVistas()[i].get(j).getTipologia() == "FO"
                            || plan.getMateriasVistas()[i].get(j).getTipologia() == "FOpt") {
                        acumuladoFundamentacion += plan.getMateriasVistas()[i].get(j).getCreditos();
                    } else if (plan.getMateriasVistas()[i].get(j).getTipologia() == "DO"
                            || plan.getMateriasVistas()[i].get(j).getTipologia() == "DOpt") {
                        acumuladoDisciplinar += plan.getMateriasVistas()[i].get(j).getCreditos();
                    } else {
                        acumuladoElectivas += plan.getMateriasVistas()[i].get(j).getCreditos();
                    }
                }
            }
        }
        double acumulado = acumuladoFundamentacion + acumuladoDisciplinar + acumuladoElectivas;
        double[] avance = {acumuladoFundamentacion, acumuladoDisciplinar, acumuladoElectivas, acumulado};
        return avance;
    }

}
