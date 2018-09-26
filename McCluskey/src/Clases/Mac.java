/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author orlando
 */
public class Mac {
 
    private int[] minterminos;
    private int min;
    private int max;
    private int aize;
    private String res;
    private ArrayList<Elemento> tabla;
    
    public Mac(int minterminos[]) {
        this.minterminos = minterminos;
        this.tabla = new ArrayList<>();
        this.res = " ";
        
        this.min = 20;
        this.max = 0;
    }
    
    public void Agregar(Elemento e) {
        this.tabla.add(e);
        
        if(e.getUnos()<min) {
            min = e.getUnos();
        }
        if(e.getUnos()>max) {
            max = e.getUnos();
        }
    }
    
    public void Agrupacion() {
        ArrayList<ArrayList<Elemento>> grupo = new ArrayList<ArrayList<Elemento>>(max); //arreglo n elementos
//        ArrayList<Elemento> cero = new ArrayList<>();
//        ArrayList<Elemento> uno = new ArrayList<>();
//        ArrayList<Elemento> dos = new ArrayList<>();
//        ArrayList<Elemento> tres = new ArrayList<>();
//        ArrayList<Elemento> cuatro = new ArrayList<>();
        
        for(int i=min;i<=max;i++) { //agrupacion n elementos
            ArrayList<Elemento> aux = new ArrayList<>();
            for(int j=0;j<this.tabla.size();j++) {
                if(this.tabla.get(j).getUnos()==i) {
                    aux.add(this.tabla.get(j));
                }
            }
            grupo.add(aux);
        }
        
        for(int i=0;i<grupo.size();i++) {
            if(grupo.get(i).isEmpty()) {
                grupo.remove(i);
                i=0;
            }
        }
        
        int ii=0;
        int jj=1;
        for(int i=0;i<grupo.size();i++) {
            for(int j=0;j<=grupo.get(ii).size();j++) {
                try {
                    for(int k=0;k<grupo.get(jj).size();k++) {
                        int unI = grupo.get(ii).get(0).getPosUX().length;
                        int unJ = grupo.get(jj).get(0).getPosUX().length;
                        int c = 0;

                        for(int l=0;l<unI;l++) {
                            try {
                                for(int m=0;m<unJ;m++) {
                                    if(grupo.get(ii).get(j).getPosUX()[l]==grupo.get(jj).get(k).getPosUX()[m]) {
                                        c++;
                                    }
                                }
                            } catch(Exception e) {
//                                System.out.println("Error");
                            }
                        }

                        try {
                            if(c>grupo.get(ii).get(j).getPosUX().length) {
                                String[] aux = new String[grupo.get(ii).get(j).getBit().length];
                                for(int n=0;n<grupo.get(ii).get(0).getBit().length;n++) {
                                    if(grupo.get(ii).get(j).getBit()[n].equals(grupo.get(jj).get(k).getBit()[n])) {
                                        aux[n]=grupo.get(ii).get(j).getBit()[n];
                                    } else {
                                        aux[n]="x";
                                    }
                                }
                            }
                        } catch(Exception e) {
//                            System.out.println("Error2");
                        }
                        c=0;
                    }
                }catch(Exception e) {
//                    System.out.println("Error3");
                }
            }
            ii++;
            jj++;
        }
        
    }
}
