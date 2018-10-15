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
public class Convertir {
    
    public static String convertir(String[] bit) {
        String aux=" ";
        for(int x = 0 ; x<bit.length;x++){
            aux=aux+letras(x,bit[x]);
        }
        return aux;
    }
    
    private static String letras(int posicion,String valor){
        String aux="";
        int auxi= (65+posicion);
        if(valor.equals("1"))
            aux += (char)auxi;
        else if(valor.equals("0"))
            aux += (char)auxi+"!";
        return (aux);
    }
}
