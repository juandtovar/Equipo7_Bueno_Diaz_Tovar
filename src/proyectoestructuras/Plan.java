/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoestructuras;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plan {
    private String nombre;
    private int n_semestres;
    private Chain<Materia> [] semestres ;
    private Chain<Materia> optativas;
    private int creditosDiscp;
    private int creditosFund;
    private int creditosElect;
    private int creditosTotales;
    private double PAPA;
    

    public Plan(String nombre, int creditosDiscp, int creditosFund, int creditosElect,int n_semestres) {
        this.nombre = nombre;
        this.creditosDiscp = creditosDiscp;
        this.creditosFund = creditosFund;
        this.creditosElect = creditosElect;
        this.n_semestres=n_semestres;
        this.semestres= new Chain [n_semestres]; 
        this.optativas=new Chain <>();
    }

    public String getNombre() {
        return nombre;
    }
    public int getN_semestres() {
        return n_semestres;
    }
    public Chain<Materia>[] getSemestres() {
        return semestres;
    }
    public int getCreditosDiscp() {
        return creditosDiscp;
    }
    public int getCreditosFund() {
        return creditosFund;
    }
    public int getCreditosElect() {
        return creditosElect;
    }
    public int getCreditosTotales() {
        return creditosTotales;
    }
    public double getPAPA() {
        return PAPA;
    }
    
    

    public void setCreditosTotales(int creditosTotales) {
        this.creditosTotales = this.creditosDiscp+this.creditosFund+this.creditosElect;
    }
    
    public void setAprobadas (){
        
    }

    public void setPAPA(double PAPA) {

    }
    public void cargarMaterias(FileInputStream file){
        Scanner readFile=new Scanner (file);
        readFile.useDelimiter("/ ");
        System.out.println(readFile.nextLine());
        int i=1;
        do{
            Materia materia=new Materia (readFile.nextInt(),readFile.next(),readFile.nextInt(),readFile.next(),readFile.next());
            int semestre=readFile.nextInt();
            readFile.nextLine();
            materia.setSemestre(semestre);
            if(semestre==0){
                this.optativas.PushBack(materia);
                //System.out.print("Es optativa ");
            }else{
                if(semestres[semestre-1]==null){
                    Chain <Materia> semestreLista=new Chain <>();
                    this.semestres[semestre-1]=semestreLista;
                    semestreLista.PushBack(materia);
                }else{
                    semestres[semestre-1].PushBack(materia);
                }
            }
            //System.out.printf("%d %s \n",i,materia.getName());
            //System.out.printf(" %-70s %-15d %-5d %-70s %-5s %-5d",materia.getName(),materia.getCodigo(),materia.getCreditos(),materia.getPrerrequisitos(),materia.getTipologia(),materia.getSemestre());
            //System.out.print("\n");
            /*
            System.out.print(materia.getName()+" ");
            System.out.print(materia.getCodigo()+" ");
            System.out.print(materia.getCreditos()+" ");
            System.out.print(materia.getPrerrequisitos()+" ");
            System.out.print(materia.getTipologia()+" ");
            System.out.print(materia.getSemestre()+" ");
            System.out.print(semestre);
            System.out.print("\n");*/
            i++;
        }while (readFile.hasNext());
        readFile.close();
    }
    
    
    
    }
    
    
    
    


