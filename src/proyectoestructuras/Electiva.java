/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

/**
 *
 * @author usuario
 */
public class Electiva extends Materia {
    public Electiva(String name, int codigo, int creditos,String prerrequisitos) {
        super(codigo, name, creditos,"EL",prerrequisitos);
    }
    
}
