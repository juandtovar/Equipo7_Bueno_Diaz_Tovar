package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class Electiva extends Materia implements Serializable {

    public Electiva(String name, int codigo, int creditos, String prerrequisitos, int semestre) {
        super(codigo, name, creditos, "EL", prerrequisitos, semestre);
    }

}
