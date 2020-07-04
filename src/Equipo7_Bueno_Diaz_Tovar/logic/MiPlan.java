package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class MiPlan {

    public static void insertarMateria(Plan plan, Materia materia) {
        System.out.printf("%s%d\n", "Inicio insertar materia = \t", System.currentTimeMillis());
        if (plan.getMaterias().find(materia) != -1) {
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Insertar materia");
            dialogo.setHeaderText(null);
            dialogo.setContentText("La materia ya se encontraba en el plan");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        } else if (materia.getSemestre() <= plan.getN_semestres()) {
            plan.getSemestres()[materia.getSemestre() - 1].add(materia);
            plan.getMaterias().put(materia);
        } else {
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Insertar materia");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Semestre fuera de rango");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        }
        System.out.printf("%s%d\n", "Fin insertar materia = \t\t", System.currentTimeMillis());
        MiAvance.salvarAvance(plan);
    }

    public static void eliminarMateria(Plan plan, long codigo) throws Exception {
        System.out.printf("%s%d\n", "Inicio eliminar materia = \t", System.currentTimeMillis());
        int posTabla = plan.getMaterias().find(new Materia(codigo, "", 0, "", 0));
        if (posTabla != -1) {
            Materia mat = plan.getMaterias().get(posTabla);
            int pos = mat.getPos();
            int sem = mat.getSemestre();
            if (plan.getSemestres()[sem - 1].get(pos).getTipologia().equals("LE")) {
                try {
                    int i = plan.getMateriasVistas()[sem - 1].indexOf(plan.getSemestres()[sem - 1].get(pos));
                    plan.getMateriasVistas()[sem - 1].remove(i);
                } catch (Exception ex) {

                }
                plan.getSemestres()[sem - 1].remove(pos);
                plan.getMaterias().remove(mat);
                for (int i = pos; i < plan.getSemestres()[sem - 1].size(); i++) {
                    plan.getSemestres()[sem - 1].get(i).setPos(plan.getSemestres()[sem - 1].get(i).getPos() - 1);
                }
            } else {
                throw new Exception("MateriaObligatoria");
            }
        }
        System.out.printf("%s%d\n", "Fin eliminar materia = \t\t", System.currentTimeMillis());
        MiAvance.salvarAvance(plan);
    }

    public static void consultarMateria(Plan plan, long codigo) {
        System.out.printf("%s%d\n", "Inicio consultar materia = \t", System.currentTimeMillis());
        int posTabla = plan.getMaterias().find(new Materia(codigo, "", 0, "", 0));
        System.out.printf("%s%d\n", "Fin consultar materia = \t", System.currentTimeMillis());
        if (posTabla != -1) {
            Materia mat = (Materia) plan.getMaterias().get(posTabla);
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("Busqueda de materia");
            dialogo.setHeaderText(null);
            String texto = "Código: " + mat.getCodigo() + '\n'
                    + "Materia: " + mat.getName() + '\n'
                    + "Créditos: " + mat.getCreditos() + '\n'
                    + "Tipología: " + mat.getTipologia() + '\n';
            if (mat.getNota() != null) {
                texto += "Historial de notas: " + mat.getNota().toString() + '\n';
            }
            if(mat.getPrerrequisitos().size() != 0) {
                texto += "Prerrequisitos: [";
                for(int i = 0; i < mat.getPrerrequisitos().size(); i++) {
                    long codigoPrerrequisito = mat.getPrerrequisitos().get(i);
                    int posTablaPre = plan.getMaterias().find(new Materia(codigoPrerrequisito, "", 0, "", 0));
                    String nombrePre = plan.getMaterias().get(posTablaPre).getName();
                    texto += nombrePre;
                    if(i != mat.getPrerrequisitos().size() - 1) {
                        texto += ", ";
                    }
                }
                texto += "]\n";
            }
            if(mat.getDesbloqueos().size() != 0) {
                texto += "Desbloqueos: [";
                for(int i = 0; i < mat.getDesbloqueos().size(); i++) {
                    long codigoDesbloqueo = mat.getDesbloqueos().get(i);
                    int posTablaD = plan.getMaterias().find(new Materia(codigoDesbloqueo, "", 0, "", 0));
                    String nombreD = plan.getMaterias().get(posTablaD).getName();
                    texto += nombreD;
                    if(i != mat.getDesbloqueos().size() - 1) {
                        texto += ", ";
                    }
                }
                texto += "]";
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
