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
    private int unos;
    private int posUX[];
    private int posX[];
    private int marca;
    private String bit[];
    private String posicionTabla;
    
    public Bits(String b[],String posicion) {
        this.bit = b;
        this.posicionTabla = posicion;
        this.unos=0;
        this.marca = 0;
        
        Contar();
        PosicionUX();
        PosicionX();
    }
    
    public void Contar() {
        for(int i=0;i<this.bit.length;i++) {
            if("1".equals(this.bit[i])) {
                this.unos++;
            }
        }
    }
    
    public void PosicionUX() {
        int aux = 0;
        for(int i=0;i<bit.length;i++) {
            if(bit[i].equals("1")) {
                aux++;
            }
        }
        
        this.posUX = new int[aux];
        aux = 0;
        
        for(int i=0;i<bit.length;i++) {
            if(bit[i].equals("1")) {
                this.posUX[aux] = i;
                aux++;
            }
        }
    }
    
    public void PosicionX() {
        int aux = 0;
        for(int i=0;i<bit.length;i++) {
            if(bit[i].equals("x")) {
                aux++;
            }
        }
        
        this.posX = new int[aux];
        aux = 0;
        
        for(int i=0;i<bit.length;i++) {
            if(this.bit[i].equals("x")) {
                this.posX[aux] = i;
                aux++;
            }
        }
    }

    public int getUnos() {
        return unos;
    }

    public void setUnos(int unos) {
        this.unos = unos;
    }

    public int[] getPosUX() {
        return posUX;
    }

    public void setPosUX(int[] posUX) {
        this.posUX = posUX;
    }

    public int[] getPosX() {
        PosicionX();
        return posX;
    }

    public void setPosX(int[] posX) {
        this.posX = posX;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public String[] getBit() {
        return bit;
    }

    public void setBit(String[] bit) {
        this.bit = bit;
    }

    public String getPosicionTabla() {
        return posicionTabla;
    }

    public void setPosicionTabla(String posicionTabla) {
        this.posicionTabla = posicionTabla;
    }
}
