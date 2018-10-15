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
 
    private ArrayList<Bits> tabla; //almacena los terminos de la tabla que se van a minimizar
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
                            int unI = grupo.get(ii).get(0).getPosU().length; //numero de unos en la posicion ii de la agrupación
                            int unJ = grupo.get(jj).get(0).getPosU().length; //numero de unos en la posicion jj de la agrupación
                            
                            int xI = grupo.get(ii).get(0).getPosX().length; //numero de 'x' en la agrupacion ii de la agrupación
                            int xJ = grupo.get(jj).get(0).getPosX().length; //numeros de x'' en la posicion jj de la agrupación
                            
                            int u = 0; //contados de unos coincidentes
                            int x = 0; //contador de 'x' coincidentes
                            int d = 0; //contador de unos de diferencia
                            if(unI==0&&unJ>1) {
                                d=2; // cuando los terminos en ii no tienen unos pero los de jj tienen dos unos o mas
                            } else {
                                /*----------------------------comparacion de la posicion de los unos----------------------------*/
                                for(int l=0;l<unI;l++) { //movimiento en las posiciones de los unos en ii
                                    try {
                                        for(int m=0;m<unJ;m++) { //movimiento en las posiciones de los unos en jj
                                            if(inicio==true){ //este bloque solo se reproduce si es la primer minimización
                                                if(grupo.get(ii).get(j).getPosU()[l]==grupo.get(jj).get(k).getPosU()[m]) {
                                                    u++; //si la posición l del primer termino es igual a la posicion m del segundo termino el contador u aumenta en uno
                                                }
                                            } else { //si es la segunda minimización o superior entra en este bloque
                                                if(grupo.get(ii).get(j).getPosX()[0]==grupo.get(jj).get(k).getPosX()[0]) { //compara si las primeras posiciones de los terminos son iguales
                                                    if(grupo.get(ii).get(j).getPosU()[l]==grupo.get(jj).get(k).getPosU()[m]) {
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
                                if(u>=grupo.get(ii).get(j).getPosU().length&&d<2) { //si 'u' es mayor o igual a la cantidad de unos del termino en ii y además 'd'<2
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
        ArrayList<Bits> simpli = new ArrayList<>(); // genero un arreglo de bits para simplificar el arreglo de bits de componentes primos("minusterminos")
        int[] cuantos = new int[this.minterminos.length]; // este arreglo de enteros es para saber cuantas veces encuentro un elemento de los minterminos definidos
        
        for(int x=0; x<this.minterminos.length;x++){//recorro mis minterminos
            int z= this.minterminos[x];//guardo el mintermino 
            int xy=x;// y su posicion en el arreglo
            cuantos[x]=0;// mi arreglo en esa posicion la inicializo en 0
           
            minusterminos.forEach((Bits Simplificar) -> { // recorro todos los elementos del arreglo dado (minusterminos)
                String datos[]=Simplificar.getPosicionTabla().split("-"); // separo los decimales del bit, para obtener los numeros decimales del mismo
                
                for (String dato : datos) {// para cada elemento del bit
                    int y = Integer.parseInt(dato); // se transforma de un String a un entero
                    
                    if(z==y){ // si mi numero guardado "z" (mintemino guardado) y "y" (numero del bit) son iguales entra
                      
                      cuantos[xy]++;// agrego 1 a cuantos econtre en la posicion del arreglo 
                      break;
                    }
                }
            });
        }
        for (int x =0; x<cuantos.length;x++){// recorro todos mis contadores
            
            int z=x;// guardo la posicion de ese numero
            if (cuantos[x]==1){// si encontre un dato que solo existe una vez en todos mis componentes primos y pertenece a los minterminos 
                minusterminos.forEach((Bits Simplificar) -> {//busco ese elemento
                String datos[]=Simplificar.getPosicionTabla().split("-"); // para que tenga sentido mi busqueda a cada bit le separo los decimales que forma
                for (String dato : datos) {// y por cada decimal
                    int y = Integer.parseInt(dato);// transformo y 
                    if (y==this.minterminos[z] && Simplificar.getMarca()!=-1){//comparo donde esta, la marca que estoy comparando siempre deberia estar en 0
                        //pero si 2 o más decimales se encuentran en el mismo bit solo una vez, genero una marca para no agarrar varias veces el mismo elemento. 
                        simpli.add(Simplificar);//agrego elemento
                        Simplificar.setMarca(-1);// marco con -1 el elemento
                        System.out.println("Entro nuevo mintermino");// impresion de debugeo
                        break;
                    }
                }
            });
            }
        }
        minusterminos.forEach((Bits Simplificar) -> {
            
            System.out.println("Marca de : "+ Simplificar.getPosicionTabla()+ " existe: "+Simplificar.getMarca());
            
        }); // imprecion de todos los datos con las marcas dadas
        simpli.forEach((Bits simplif)->{
            minusterminos.remove((Bits) simplif);
        });//cada elemento agregado a mi solucion simplificada se elimina de los componentes primos
        
        //fin de 1 sola solución

        minusterminos.forEach((Bits Simplificar) -> { //Busco mis componentes primos, los restantes 
            System.out.println("Entro dato con: "+ Simplificar.getPosicionTabla());// impresion de debugeo
            String datos[]=Simplificar.getPosicionTabla().split("-");// separo mis decimales del bit para comparar
            boolean aux=false;// booleano que me dice si el elemento no marcado tiene un mintermino
            
            for (String dato : datos) {   //por cada decimal del bit del componente primo
                System.out.println("Entro dato a: "+ dato);//impresion de debugeo
                int z= Integer.parseInt(dato);// igualo el decimal a un entero 
               
                simpli.forEach((Bits simpli1) -> { //por cada elemento de simpli
                    System.out.println("Entro simpli con: "+ simpli1.getPosicionTabla());// impresion de debugeo
                    String sd[]=simpli1.getPosicionTabla().split("-"); // separo los decimales del elemento 
                    
                    for (String sim : sd) {// y por cada decimal de mis simplificados 
                        System.out.println("Entro simpli sa: "+ sim);//impresion de debugeo
                        int y = Integer.parseInt(sim);// convierto ese decimal a un entero
                        
                            
                        if(y == z){ // si existe el decimal de mi componente primo en alguno de los datos simplificados
                            System.out.println("Entro: "+ z+ " = "+y);// impresion de debugeo
                            Simplificar.setMarca(-1);// marco el dato 
                            System.out.println("Marca de : "+ Simplificar.getPosicionTabla()+ " existe: "+Simplificar.getMarca());// imprimo que el dato se marco
                            break;// paso al siguiente elemento de mi simplificacion
                        }
                    }
                    
                });   
            }
            
            if(Simplificar.getMarca()!=-1){ // si la marca del elemento es distinta a -1
                
                for(int i=0; i<this.minterminos.length; i++){// busco con mis minterminos si 
                    
                    for(int y=0; y<datos.length;y++){// alguno de los datos de del elemento es igual al mintermino
                        if(this.minterminos[i] == Integer.parseInt(datos[y])){// en caso de ser asi
                            aux=true;// encontre un bit con minterminos 
                            break;// me salgo de aqui 
                        }
                    }
                }
                if(aux){ // si tuve exito encontrando un mintermino nuevo que no se habia agregado
                    simpli.add(Simplificar);// lo agrego a los simplificados 
                }
            }
        });
        
        minusterminos.forEach((Bits Simplificar) -> {
            System.out.println("Marca de : "+ Simplificar.getPosicionTabla()+ " existe: "+Simplificar.getMarca());
            
        });// impresiones de comprobacion de marcas final, debugeos
        
        //En caso de tener más de una solucion, este codigo no te da la simplificacion más optima,
        //te da la primera solucion que encuentra, de la manera empirica que usted nos enseño.
        //por eso algunas soluciones tendran  un o más minterminos en más de una agrupacion de bits,
        // y tampoco se ocupa de agarrar el numero menor de x, por esos detalles, la solucion es suficiente, pero no optima.
        
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
        return this.res; // retorna el resultado para su impreción en pantalla
    }
}
