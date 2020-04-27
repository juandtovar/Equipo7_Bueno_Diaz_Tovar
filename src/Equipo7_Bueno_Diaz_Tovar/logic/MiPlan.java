package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class MiPlan {

    public static void insertarMateria(Plan plan, Materia materia) {
        System.out.printf("%s%d\n", "Inicio insertar materia = \t", System.currentTimeMillis());
        if (materia.getSemestre() <= plan.getN_semestres() && !estaDentroEnPlan(plan, materia)) {
            plan.getSemestres()[materia.getSemestre() - 1].add(materia, plan.getSemestres()[materia.getSemestre() - 1].getSize());
        } else if (materia.getSemestre() <= plan.getN_semestres()) {
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Insertar materia");
            dialogo.setHeaderText(null);
            dialogo.setContentText("La materia ya se encontraba en el plan");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        }
        System.out.printf("%s%d\n", "Fin insertar materia = \t\t", System.currentTimeMillis());
    }

    public static void eliminarMateria(Plan plan, int codigo) {
        System.out.printf("%s%d\n", "Inicio eliminar materia = \t", System.currentTimeMillis());
        ChainNode<Materia> temp;
        for (int i = 0; i < plan.getN_semestres(); i++) {
            if (plan.getSemestres()[i].getSize() != 0) {
                temp = plan.getSemestres()[i].getHead();
                while (temp != null) {
                    if (temp.getElement().getCodigo() == codigo) {
                        plan.getSemestres()[i].remove(plan.getSemestres()[i].indexOf(temp.getElement()));
                        System.out.printf("%s%d\n", "Fin eliminar materia = \t\t", System.currentTimeMillis());
                        return;
                    } else {
                        temp = temp.getNext();
                    }
                }
            }
        }
        alertaMateriaNoEncontrada();
    }

    public static void consultarMateria(Plan plan, int codigo) {
        System.out.printf("%s%d\n", "Inicio consultar materia = \t", System.currentTimeMillis());
        ChainNode<Materia> temp;
        for (int i = 0; i < plan.getN_semestres(); i++) {
            if (plan.getSemestres()[i].getSize() != 0) {
                temp = plan.getSemestres()[i].getHead();
                while (temp != null) {
                    if (temp.getElement().getCodigo() == codigo) {
                        System.out.printf("%s%d\n", "Fin consultar materia = \t", System.currentTimeMillis());
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
        alertaMateriaNoEncontrada();
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

    public static void alertaMateriaNoEncontrada() {
        System.out.printf("%s%d\n", "No se encontró materia = \t", System.currentTimeMillis());
        Alert dialogo = new Alert(AlertType.INFORMATION);
        dialogo.setTitle("Busqueda de materia");
        dialogo.setHeaderText(null);
        dialogo.setContentText("No se encontró la materia");
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }
}
