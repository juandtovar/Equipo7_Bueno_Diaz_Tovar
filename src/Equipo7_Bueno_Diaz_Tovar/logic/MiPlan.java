package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class MiPlan {

    public static void insertarMateria(Plan plan, Materia materia) {
        if (materia.getSemestre() <= plan.getN_semestres() && !estaDentroEnPlan(plan, materia)) {
            plan.getSemestres()[materia.getSemestre() - 1].add(materia, plan.getSemestres()[materia.getSemestre() - 1].getSize());
        }
    }

    public static void eliminarMateria(Plan plan, int codigo) {
        ChainNode<Materia> temp;
        for (int i = 0; i < plan.getN_semestres(); i++) {
            if (plan.getSemestres()[i].getSize() != 0) {
                temp = plan.getSemestres()[i].getHead();
                while (temp != null) {
                    if (temp.getElement().getCodigo() == codigo) {
                        plan.getSemestres()[i].remove(plan.getSemestres()[i].indexOf(temp.getElement()));
                        return;
                    } else {
                        temp = temp.getNext();
                    }
                }
            }
        }
    }

    public static void consultarMateria(Plan plan, int codigo) {
        ChainNode<Materia> temp;
        for (int i = 0; i < plan.getN_semestres(); i++) {
            if (plan.getSemestres()[i].getSize() != 0) {
                temp = plan.getSemestres()[i].getHead();
                while (temp != null) {
                    if (temp.getElement().getCodigo() == codigo) {
                        Materia mat = temp.getElement();
                        Alert dialogo = new Alert(AlertType.INFORMATION);
                        dialogo.setTitle("Busqueda de materia");
                        dialogo.setHeaderText(null);
                        String texto = "Código: " + mat.getCodigo() + '\n'
                                + "Materia: " + mat.getName() + '\n'
                                + "Créditos: " + mat.getCreditos() + '\n'
                                + "Tipología: " + mat.getTipologia() + '\n';
                        if (mat.getNota() != null) {
                            texto += "Nota: " + mat.getNota() + '\n';
                        }
                        dialogo.setContentText(texto);
                        dialogo.initStyle(StageStyle.UTILITY);
                        dialogo.showAndWait();
                        return;
                    } else {
                        temp = temp.getNext();
                    }
                }
            }
        }
    }

    public static boolean estaDentroEnPlan(Plan plan, Materia materia) {
        ChainNode<Materia> temp;
        for (int i = 0; i < plan.getN_semestres(); i++) {
            if (plan.getSemestres()[i].getSize() != 0) {
                temp = plan.getSemestres()[i].getHead();
                while (temp != null) {
                    if (temp.getElement().getCodigo() == materia.getCodigo()) {
                        return true;
                    } else {
                        temp = temp.getNext();
                    }
                }
            }
        }
        return false;
    }
}
