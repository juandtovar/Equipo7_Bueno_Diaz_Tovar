package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;

public class MiPlan {

    public void insertarMateria(Plan plan, Materia materia) {
        if (materia.getSemestre() <= plan.getN_semestres()) {
            plan.getSemestres()[materia.getSemestre() - 1].PushBack(materia);
        }

    }

    public boolean eliminarMateria(Plan plan, int codigo) {
        ChainNode<Materia> temp = new ChainNode();
        for (int i = 0; i < plan.getN_semestres() - 1; i++) {
            temp = plan.getSemestres()[i].getHead();
            if (temp.getElement().getCodigo()== codigo) {
                temp.setNext(temp.getNext().getNext());
                return true;
            }
            boolean condicion = true;
            while (condicion) {
                if (temp.getNext().getElement().getCodigo() == codigo && temp.getNext() != null) {
                    temp.setNext(temp.getNext().getNext());
                    return true;
                }
                temp = temp.getNext();
                if (temp == null) {
                    condicion = false;
                }
            }
        }
        return false;
    }
}
