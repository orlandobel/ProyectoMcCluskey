/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author orlando
 */
public class Minimizar {
 
    private ArrayList<Bits> tabla; //almacena los terminos dew la tabla que se van a minimizar
    private int[] minterminos; //almacena los numeros en decimal cullo valor de salida sea 1
    private int min; //número más pequeño
    private int max; //número más grande
    private String res; //resultado final
    
    public Minimizar(int minterminos[]) {
        this.minterminos = minterminos;
        this.tabla = new ArrayList<>();
        this.res = " ";
        
        this.min = 20;
        this.max = 0;
    }
    
    public void Agregar(Bits e) {
        this.tabla.add(e); //añade un numero en binario
        
        /*establece el numero más grande y el más pequeño*/
        if(e.getUnos()<min) {
            min = e.getUnos();
        }
        if(e.getUnos()>max) {
            max = e.getUnos();
        }
        /*-----------------------------------------------*/
    }
    
    public void Agrupacion() {
        boolean inicio = true; //variable auxiliar para saber si es la primer agrupación de las tablas
        ArrayList<ArrayList<Bits>> grupo = new ArrayList<ArrayList<Bits>>(max); //simula la tabla de agrupación
        ArrayList<Bits> minusterminos = new ArrayList<>(); //almacena en binario los terminos que no pueden reducirse más
        
        /*organiza los terminos según la cantidad de unos que tienen*/
        for(int i=min;i<=max;i++) { 
            ArrayList<Bits> aux = new ArrayList<>(); //arreglo auxiliar en el que se almacenan los terminos con i cantidad de unos
            for(int j=0;j<this.tabla.size();j++) {
                if(this.tabla.get(j).getUnos()==i) { 
                    aux.add(this.tabla.get(j)); //cuando el elemento j en el arreglo tabla tiene la misma cantidad de unos se añade al arreglo auxiliar
                }
            }
            grupo.add(aux); //se añade aux a la tabla de agrupacion
        }
        /*----------------------------------------------------------*/
        
        /*si existe algun arreglo dentro de la tabla de agrupacion que no contenga ningun elemento es eliminado*/
        for(int i=0;i<grupo.size();i++) {
            if(grupo.get(i).isEmpty()) {
                grupo.remove(i);
                i=0;
            }
        }
        /*-----------------------------------------------------------------------------------------------------*/
       
        /*--------------------------------------------minimización de la tabla y obtencion de minterminos--------------------------------------------*/
        do {
            int ii=0; //contador de la agrupacion con n unos. Ejemplo agrupacion con 0 unos
            int jj=1; //contador de la agrupacion con n+1 unos. Ejemplo con 1 uno
            ArrayList<ArrayList<Bits>> grupo2 = new ArrayList<ArrayList<Bits>>(); //tabla de agrupación auxiliar para los terminos agrupados. Ejemplo: agrupación de ii=0 con jj=1
            
            for(int i=0;i<grupo.size();i++) { //movimiento en la tabla de agrupacion
                ArrayList<Bits> b = new ArrayList<>(); //arreglo auxiliar de los terminos agrupados. Ejemplo 000 - 001 = 00x; Esto funciona para cualquier nivel de agrupación
                for(int j=0;j<=grupo.get(ii).size();j++) { //movimiento en la agrupacion con ii elementos
                    try {
                        for(int k=0;k<grupo.get(jj).size();k++) { //movimiento en la agrupacion con jj elementos
                            int unI = grupo.get(ii).get(0).getPosUX().length; //numero de unos en la posicion ii de la agrupación
                            int unJ = grupo.get(jj).get(0).getPosUX().length; //numero de unos en la posicion jj de la agrupación
                            
                            int xI = grupo.get(ii).get(0).getPosX().length; //numero de 'x' en la agrupacion ii de la agrupación
                            int xJ = grupo.get(jj).get(0).getPosX().length; //numeros de x'' en la posicion jj de la agrupación
                            
                            int u = 0; //contados de unos coincidentes
                            int x = 0; //contador de 'x' coincidentes
                            int d = 0; //contador de unos de diferencia
                            if(unI==0&&unJ>1) {
                                d=2; // cuando los terminos en ii no tienen unos pero los de jj tienen dos unos o mas
                            } else {
                                /*----------------------------comparacion de la posicion de los unos----------------------------*/
                                for(int l=0;l<unI;l++) { //movimiento en las posiciones de los inos en ii
                                    try {
                                        for(int m=0;m<unJ;m++) { //movimiento en las posiciones de los unos en jj
                                            if(inicio==true){ //este bloque solo se reproduce si es la primer minimización
                                                if(grupo.get(ii).get(j).getPosUX()[l]==grupo.get(jj).get(k).getPosUX()[m]) {
                                                    u++; //si la posición l del primer termino es igual a la posicion m del segundo termino el contador u aumenta en uno
                                                }
                                            } else { //si es la segunda minimización o superior entra en este bloque
                                                if(grupo.get(ii).get(j).getPosX()[0]==grupo.get(jj).get(k).getPosX()[0]) { //compara si las primeras posiciones de los terminos son iguales
                                                    if(grupo.get(ii).get(j).getPosUX()[l]==grupo.get(jj).get(k).getPosUX()[m]) {
                                                        u++; //realiza la misma accion que el bloque anterior
                                                    }
                                                }
                                            }
                                        }
                                    } catch(Exception e) {

                                    }
                                }
                                /*----------------------------------------------------------------------------------------------*/
                            }
                            
                            /*----------cuando es la  segunda minimizacion o superior entra al siguiente bloque----------*/
                            if(inicio==false){
                                //funciona de la misma manera que el anterior pero con las 'x' en vez de unos
                                for(int l=0;l<xI;l++) {
                                 try {
                                    for(int m=0;m<xJ;m++) {
                                            if(grupo.get(ii).get(j).getPosX()[0]==grupo.get(jj).get(k).getPosX()[0]) {
                                                if(grupo.get(ii).get(j).getPosX()[l]==grupo.get(jj).get(k).getPosX()[m]) {
                                                    x++;
                                                }
                                            }
                                        }
                                    } catch(Exception e) {
                                    
                                    }
                                }
                            }
                            /*-------------------------------------------------------------------------------------------*/
                            
                            /*------------------------------------------agrupación de terminos coincidentes------------------------------------------*/
                            try {
                                if(u>=grupo.get(ii).get(j).getPosUX().length&&d<2) { //si 'u' es mayor o igual a la cantidad de unos del termino en ii y además 'd'<2
                                    if(x>=grupo.get(ii).get(j).getPosX().length) { //si 'x' es mayor o igual que la cantidad de x del termino en ii
                                        String[] aux = new String[grupo.get(ii).get(j).getBit().length]; //String auxiliar para el nuevo bit
                                        /*----------------------------genera el nuevo binario----------------------------*/
                                        for(int n=0;n<grupo.get(ii).get(0).getBit().length;n++) {
                                            if(grupo.get(ii).get(j).getBit()[n].equals(grupo.get(jj).get(k).getBit()[n])) {
                                                aux[n]=grupo.get(ii).get(j).getBit()[n]; //si los valores del bit en la posicion 'n' de si String son iguales, pasa el mismo numero al auxiliar en la misma posicion
                                            } else {
                                                aux[n]="x"; //si los valores del bit en la posicion 'n' de su String son diferentes, pasa una x al auxiliar en la misma posicion
                                            }
                                        }
                                        /*-------------------------------------------------------------------------------*/
                                        
                                        /*Establece la marca de los terminos usados en uno*/
                                        grupo.get(ii).get(j).setMarca(1);
                                        grupo.get(jj).get(k).setMarca(1);
                                        /*------------------------------------------------*/
                                    
                                        Bits e = new Bits(aux,""+grupo.get(ii).get(j).getPosicionTabla()+"-"+grupo.get(jj).get(k).getPosicionTabla()); //crea un nuevo objeto de tipo Bits con el String auxiliar
                                        boolean exist = false; //variable para verificar si el objeto ya existe
                                        
                                        /*----busca el bit creado en el arreglo auxiliar----*/
                                        for(int o=0;o<b.size();o++) {
                                            if(Arrays.equals(e.getBit(), b.get(o).getBit())) {
                                                exist = true; // si encuentra
                                            }
                                        }
                                        /*--------------------------------------------------*/
                                        
                                        /*añade el bit creado en el arreglo auxiliar*/
                                        if(exist==false) { 
                                            b.add(e); //si el nuevo bit no se encuentra en el arreglo auxiliar lo añade
                                        }
                                        /*------------------------------------------*/
                                    }
                                }
                            } catch(Exception e) {
                                
                            }
                            /*-----------------------------------------------------------------------------------------------------------------------*/
                        }
                    }catch(Exception e) {
                    
                    }
                }
                if(b.size()>0) {
                    grupo2.add(b); //cuando el arreglo auxiliar es diferente del vacio lo añade a la nueva agrupación
                }
                ii++; //aumenta ii en uno
                jj++; //aumenta jj en uno
            }
            
            /*Busca si hay minustyerminos en el grupo principal y los añade a su arreglo correspondiente*/
            int tam = grupo.size(); //obtiene el tamaño de la agrupación principal para usarlo en el  siguiente ciclo
            for(int i=0;i<tam;i++) {
                for(int j=0;j<grupo.get(0).size();j++) {
                    if(grupo.get(0).get(j).getMarca()==0) { //berifica el valor de la marca del termino en la posicion actual
                        minusterminos.add(grupo.get(0).get(j)); //si la marca es cero se añade a los minusterminos para no usar el termino en la siguiente agrupación
                    }
                }
                grupo.remove(0);//remueve el primer elemento del grupo principal para vaciarlo
                
                if(minusterminos.size()==1&&minusterminos.get(0).getPosicionTabla().equals("0")) {
                    break; //cuando el arreglo de minusterminos solo contiene al cero termina con el ciclo
                }
            }
            /*------------------------------------------------------------------------------------------*/
        
            /*añade los elementos agrupados a la agrupación principal*/
            for(int i=0;i<grupo2.size();i++) {
                grupo.add(grupo2.get(i));
            }
            /*-------------------------------------------------------*/
            
            inicio = false; //camia inicio a falso para evitar errores en la siguiente agrupación
        } while(grupo.size()>0);//se detiene cuando la tabla de agrupación no contiene ningun elemento
        /*-------------------------------------------------------------------------------------------------------------------------------------------*/
        
        //Parte de memo D:
        ArrayList<Bits> simpli = new ArrayList<>();
        int[] cuantos = new int[this.minterminos.length];
        
        for(int x=0; x<this.minterminos.length;x++){
            int z= this.minterminos[x];
            int xy=x;
            cuantos[x]=0;
           
            minusterminos.forEach((Bits Simplificar) -> {
                String datos[]=Simplificar.getPosicionTabla().split("-");
                
                for (String dato : datos) {
                    int y = Integer.parseInt(dato);
                    
                    if(z==y){
                      
                      cuantos[xy]++;
                      break;
                    }
                }
            });
        }
        for (int x =0; x<cuantos.length;x++){
            
            int z=x;
            if (cuantos[x]==1){
                minusterminos.forEach((Bits Simplificar) -> {
                String datos[]=Simplificar.getPosicionTabla().split("-");
                for (String dato : datos) {
                    int y = Integer.parseInt(dato);
                    if (y==this.minterminos[z] && Simplificar.getMarca()!=-1){
                        simpli.add(Simplificar);
                        Simplificar.setMarca(-1);
                        System.out.println("Entro nuevo mintermino");
                        break;
                    }
                }
            });
            }
        }
        minusterminos.forEach((Bits Simplificar) -> {
            
            System.out.println("Marca de : "+ Simplificar.getPosicionTabla()+ " existe: "+Simplificar.getMarca());
            
        });
        simpli.forEach((Bits simplif)->{
            minusterminos.remove((Bits) simplif);
        });
        
        //fin de 1 sola solución

        minusterminos.forEach((Bits Simplificar) -> {
            System.out.println("Entro dato con: "+ Simplificar.getPosicionTabla());
            String datos[]=Simplificar.getPosicionTabla().split("-");
            boolean aux=false;
            
            for (String dato : datos) {   
                System.out.println("Entro dato a: "+ dato);
                int z= Integer.parseInt(dato);
               
                simpli.forEach((Bits simpli1) -> {
                    System.out.println("Entro simpli con: "+ simpli1.getPosicionTabla());
                    String sd[]=simpli1.getPosicionTabla().split("-");
                    
                    for (String sim : sd) {
                        System.out.println("Entro simpli sa: "+ sim);
                        int y = Integer.parseInt(sim);
                        
                            
                        if(y == z){
                            System.out.println("Entro: "+ z+ " = "+y);
                            Simplificar.setMarca(-1);
                            System.out.println("Marca de : "+ Simplificar.getPosicionTabla()+ " existe: "+Simplificar.getMarca());
                            break;
                        }else{
                            if(Simplificar.getMarca()==-1){
                                Simplificar.setMarca(0);
                            }
                        }
                    }
                    
                });   
            }
            if(Simplificar.getMarca()!=-1){
                
                for(int i=0; i<this.minterminos.length; i++){
                    
                    for(int y=0; y<datos.length;y++){
                        if(this.minterminos[i] == Integer.parseInt(datos[y])){
                            aux=true;
                            break;
                        }
                    }
                }
                if(aux){
                    simpli.add(Simplificar);
                }
            }
        });
        
        minusterminos.forEach((Bits Simplificar) -> {
            System.out.println("Marca de : "+ Simplificar.getPosicionTabla()+ " existe: "+Simplificar.getMarca());
            
        });
        
        
        /*---------------------Genera el resultado minimizado---------------------*/
        for(int i=0;i<simpli.size();i++) {
            if(i==0){
                this.res = this.res+Convertir.convertir(simpli.get(i).getBit());
            }else{
                this.res = this.res+"+"+Convertir.convertir(simpli.get(i).getBit());
            }
        }
        /*------------------------------------------------------------------------*/
    }
    public String getRes() {
        return this.res;
    }
}
