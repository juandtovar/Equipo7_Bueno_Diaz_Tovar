package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class MiPlan {

    public static void insertarMateria(Plan plan, Materia materia) {
        System.out.printf("%s%d\n", "Inicio insertar materia = \t", System.currentTimeMillis());
        BinaryTreeNode<Identificador> temp = plan.getIdentificadores().find(new Identificador(materia.getCodigo(), 0, 0));
        if(temp != null) {
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Insertar materia");
            dialogo.setHeaderText(null);
            dialogo.setContentText("La materia ya se encontraba en el plan");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        } else if (materia.getSemestre() <= plan.getN_semestres()) {
            plan.getSemestres()[materia.getSemestre() - 1].add(materia);
            Identificador identificador =
                    new Identificador(materia.getCodigo(), materia.getSemestre(), plan.getSemestres()[materia.getSemestre() - 1].size() - 1);
            plan.getIdentificadores().add(identificador);
        } else {
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Insertar materia");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Semestre fuera de rango");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        }
        System.out.printf("%s%d\n", "Fin insertar materia = \t\t", System.currentTimeMillis());
    }

    public static void eliminarMateria(Plan plan, int codigo) {
        System.out.printf("%s%d\n", "Inicio eliminar materia = \t", System.currentTimeMillis());
        BinaryTreeNode<Identificador> temp = plan.getIdentificadores().find(new Identificador(codigo, 0, 0));
        if (temp != null) {
            plan.getIdentificadores().remove(new Identificador(codigo, 0, 0));
            plan.getSemestres()[temp.getElement().getSemestre() - 1].remove(temp.getElement().getPosición());
            System.out.printf("%s%d\n", "Fin eliminar materia = \t\t", System.currentTimeMillis());

        } else {
            alertaMateriaNoEncontrada();
        }
    }

    public static void consultarMateria(Plan plan, int codigo) {
        System.out.printf("%s%d\n", "Inicio consultar materia = \t", System.currentTimeMillis());
        BinaryTreeNode<Identificador> temp = plan.getIdentificadores().find(new Identificador(codigo, 0, 0));
        if (temp != null) {
            int semestre = temp.getElement().getSemestre();
            int pos = temp.getElement().getPosición();
            System.out.printf("%s%d\n", "Fin consultar materia = \t", System.currentTimeMillis());
            Materia mat = plan.getSemestres()[semestre - 1].get(pos);
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
        } else {
            alertaMateriaNoEncontrada();
        }
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
