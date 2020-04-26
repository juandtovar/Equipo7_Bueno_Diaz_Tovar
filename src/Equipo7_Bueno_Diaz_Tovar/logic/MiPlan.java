package Equipo7_Bueno_Diaz_Tovar.logic;

import Equipo7_Bueno_Diaz_Tovar.data.*;

public class MiPlan {
    
    public void insertarMateria(Plan plan, Materia materia) {
        
        plan.getSemestres()[materia.getSemestre() - 1].PushBack(materia);
        
    }
    
    
    
}
