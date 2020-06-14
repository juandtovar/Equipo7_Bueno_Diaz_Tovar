package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class MiAvance {

    public static void actualizarNota(Plan plan, long codigo, double nota) {
        LinkedBinaryTreeNode<Identificador> materia = plan.getIdentificadores().find(new Identificador(codigo, 0, 0));
        int semestre = materia.getElement().getSemestre();
        int pos = materia.getElement().getPosición();
        if (prerrequisitosVistos(plan, materia.getElement())) {
            if (plan.getSemestres()[semestre - 1].get(pos).getNota().size() == 0) {
                plan.getMateriasVistas()[semestre - 1].add(plan.getSemestres()[semestre - 1].get(pos));
            }
            if (!plan.getSemestres()[semestre - 1].get(pos).isVista()) {
                plan.getSemestres()[semestre - 1].get(pos).setNota(nota);
                if (nota >= 3.0) {
                    plan.getSemestres()[semestre - 1].get(pos).setVista();
                    plan.getMateriasUrgentes().remove(plan.getSemestres()[semestre - 1].get(pos));
                }
            } else {
                Alert dialogo = new Alert(AlertType.INFORMATION);
                dialogo.setTitle("Ingresar nota");
                dialogo.setHeaderText(null);
                dialogo.setContentText("La materia ya fue aprobada");
                dialogo.initStyle(StageStyle.UTILITY);
                dialogo.showAndWait();
            }
        } else {
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Ingresar nota");
            dialogo.setHeaderText(null);
            dialogo.setContentText("No cuenta con los prerrequisitos necesarios");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        }
        salvarAvance(plan);
    }

    public static boolean prerrequisitosVistos(Plan plan, Identificador materia) {
        Materia temp = plan.getSemestres()[materia.getSemestre() - 1].get(materia.getPosición());
        for (int i = 0; i < temp.getPrerrequisitos().size(); i++) {
            long prerrequisito = temp.getPrerrequisitos().get(i);
            Identificador iden_prerrequisito = (Identificador) plan.getIdentificadores().find(new Identificador(prerrequisito, 0, 0)).getElement();
            Materia mat = plan.getSemestres()[iden_prerrequisito.getSemestre() - 1].get(iden_prerrequisito.getPosición());
            if (!mat.isVista()) {
                return false;
            }
        }
        return true;
    }

    public static double calcularPAPA(Plan plan) {
        int acumulado = 0;
        int creditosVistos = 0;
        for (int i = 0; i < plan.getN_semestres(); i++) {
            SingleLinkedList<Materia> materiasSemestre = plan.getMateriasVistas()[i];
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
            SingleLinkedList<Materia> materiasSemestre = plan.getMateriasVistas()[i];
            try {
                if (materiasSemestre.get(0) != null) {
                    for (int j = 0; j < materiasSemestre.size(); j++) {
                        if (plan.getMateriasVistas()[i].get(j).getLastNota() >= 3) {
                            switch (plan.getMateriasVistas()[i].get(j).getTipologia()) {
                                case "FOpt":
                                    acumuladoFundamentacion += plan.getMateriasVistas()[i].get(j).getCreditos();
                                    break;
                                case "FO":
                                    acumuladoFundamentacion += plan.getMateriasVistas()[i].get(j).getCreditos();
                                    break;
                                case "DOpt":
                                    acumuladoDisciplinar += plan.getMateriasVistas()[i].get(j).getCreditos();
                                    break;
                                case "DO":
                                    acumuladoDisciplinar += plan.getMateriasVistas()[i].get(j).getCreditos();
                                    break;
                                default:
                                    acumuladoElectivas += plan.getMateriasVistas()[i].get(j).getCreditos();
                                    break;
                            }
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

    public static void salvarAvance(Plan plan) {
        ObjectOutputStream canal;
        try (FileOutputStream fileOutput = new FileOutputStream("Info_" + plan.getNombre() + ".txt")) {
            canal = new ObjectOutputStream(fileOutput);
            canal.flush();
            canal.writeObject(plan);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }

}
