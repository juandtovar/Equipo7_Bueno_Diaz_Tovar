package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class Optativa extends Materia implements Serializable {

    public Optativa(String name, int codigo, int creditos, String tipologia, String prerrequisitos, int semestre) {
        super(codigo, name, creditos, tipologia, prerrequisitos, semestre);
    }

}
