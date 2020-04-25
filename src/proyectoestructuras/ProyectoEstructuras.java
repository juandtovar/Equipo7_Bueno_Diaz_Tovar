/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.io.*;
import java.util.*;


/**
 *
 * @author usuario
 */
public class ProyectoEstructuras {
    
    public static void main(String[] args) {
        Chain <Plan> planes =new Chain <>(); 
        try{
            FileInputStream planesFile=new FileInputStream("informacion_planes.txt");
            Scanner readPlanes=new Scanner (planesFile);
            readPlanes.useDelimiter("/ ");
            System.out.println(readPlanes.nextLine());
            while(readPlanes.hasNext()){
                Plan plan=new Plan (readPlanes.next(),readPlanes.nextInt(),readPlanes.nextInt(),readPlanes.nextInt(),readPlanes.nextInt());
                System.out.println(plan.getNombre());
                planes.PushBack(plan);
                System.out.println(plan.getNombre()+".txt");
                FileInputStream file=new FileInputStream(plan.getNombre()+".txt");
                plan.cargarMaterias(file);
                readPlanes.nextLine();
                }
            readPlanes.close();
        }catch (FileNotFoundException ex) {
        }  
    }   
}
