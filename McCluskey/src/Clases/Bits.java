/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author orlando
 */
public class Bits {
    private int unos; //total de unos que tiene el término
    private int posU[]; //posición del arreglo en que se encuentran los unos
    private int posX[]; //posicion del arreglo en que s encuentrn las 'x'
    private int marca; //se usa para saber si se pudo agrupar con otro término o si paara como mintermino
    private String bit[]; //término en binario
    private String sal; //1,0,x
    private String posicionTabla; //número decimal (1,2,3,...)
    
    public Bits(String b[],String posicion) {
        this.bit = b;
        this.posicionTabla = posicion;
        this.unos = 0;
        this.marca = 0;
        
        Contar();
        PosicionU();
        PosicionX();
    }
    
    public Bits(String b[],String posicion,String s) {
        this.bit = b;
        this.posicionTabla = posicion;
        this.unos = 0;
        this.marca = 0;
        this.sal = s;
        
        Contar();
        PosicionU();
        PosicionX();
    }
    
    /*cuenta la cantidad de unos que hay en el término*/
    public void Contar() {
        for(int i=0;i<this.bit.length;i++) { //recorre el arreglo
            if("1".equals(this.bit[i])) { //compara si el dato en la posición 'i' es un uno
                this.unos++; //cuando se cumple se incrementa unos en uno
            }
        }
    }
    /*------------------------------------------------*/
    
    /*guarda las posiciones de los unos en el arreglo de bits*/
    public void PosicionU() {
        this.posU = new int[this.unos]; //inicializa el arreglo de posiciones de los unos
        
        int j=0; //crea una variable j
        for(int i=0;i<bit.length;i++) { //recorre el arreglo del binario
            if(bit[i].equals("1")) { //compaa si el dato en la posición 'i' es un uno
                this.posU[j] = i; //añade el vlor del contador 'i' en la posicion 'j' del arreglo de posiciones
                j++; //aumenta 'j' en uno
            }
        }
    }
    /*------------------------------------------------------*/
    
    /*guarda las posiciones de las 'x' en el arreglo de bits*/
    public void PosicionX() {
        /*ceunta las 'x' del arreglo de bits*/
        int aux = 0; //contador auxiliar de la cantidad de 'x' en el arreglo de bits
        for(int i=0;i<bit.length;i++) { //recorre el arreglo
            if(bit[i].equals("x")) { //si el dato almacenado en la posición 'i' del arreglo es una 'x'
                aux++; //aumenta aux en uno
            }
        }
        /*----------------------------------*/
        
        this.posX = new int[aux]; //crea el arreglo de posiciones de 'x'
        aux = 0; //reinicia el contador para poder usarlo más adelante
        
        /*guarda las posiciones de las 'x' dentro del arreglo de bits*/
        for(int i=0;i<bit.length;i++) { //recorre el arreglo de bits
            if(this.bit[i].equals("x")) { //si la posicion 'i' en el arreglo de bits es una 'x'
                this.posX[aux] = i; //alacena el valor de 'i' en el arreglo de posiciones
                aux++; //aumenta aux en uno
            }
        }
        /*-----------------------------------------------------------*/
    }
    /*------------------------------------------------------*/
    
    public int getUnos() {
        return unos; //retorna la cantidad de unos
    }

    public int[] getPosU() {
        return posU;//retorna el arreglo de posiciones de unos
    }

    public int[] getPosX() {
        return posX; //retorna el arredlo de posiciones de las 'x'
    }

    public int getMarca() {
        return marca; //retorna el valor de la marca
    }

    public void setMarca(int marca) {
        this.marca = marca; //establece el valor de la marca
    }

    public String[] getBit() {
        return bit; //retorna el arreglo de bits
    }

    /*public void setBit(String[] bit) {
        this.bit = bit;
    }*/

    public String getPosicionTabla() {
        return posicionTabla; //retorna la posicion de la tabla que ocupa el término (el número en decimal) 
   }
    
    public String getSal() {
        return this.sal; //retorna el valor de la salida del término (0,1 o  x)
    }
}
