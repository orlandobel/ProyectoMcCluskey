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
        boolean inicio = true;
        ArrayList<ArrayList<Elemento>> grupo = new ArrayList<ArrayList<Elemento>>(max); //arreglo n elementos
        ArrayList<Elemento> minusterminos = new ArrayList<>();
        
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
        
        do {
            int ii=0;
            int jj=1;
            ArrayList<ArrayList<Elemento>> grupo2 = new ArrayList<ArrayList<Elemento>>();
            for(int i=0;i<grupo.size();i++) {
                ArrayList<Elemento> b = new ArrayList<>();
                for(int j=0;j<=grupo.get(ii).size();j++) {
                    try {
                        
                        for(int k=0;k<grupo.get(jj).size();k++) {
                            int unI = grupo.get(ii).get(0).getPosUX().length;
                            int unJ = grupo.get(jj).get(0).getPosUX().length;
                            int u = 0;
                            int x = 0;

                            for(int l=0;l<unI;l++) {
                                try {
                                    for(int m=0;m<unJ;m++) {
                                        if(inicio==true){
                                            if(grupo.get(ii).get(j).getPosUX()[l]==grupo.get(jj).get(k).getPosUX()[m]) {
                                                u++;
                                            }
                                        } else {
                                            if(grupo.get(ii).get(j).getPosX()[0]==grupo.get(jj).get(k).getPosX()[0]) {
                                                if(grupo.get(ii).get(j).getPosUX()[l]==grupo.get(jj).get(k).getPosUX()[m]) {
                                                    u++;
                                                }
                                            }
                                        }
                                    }
                                    
                                    for(int m=0;m<unJ;m++) {
                                        if(inicio==false){
                                            if(grupo.get(ii).get(j).getPosX()[0]==grupo.get(jj).get(k).getPosX()[0]) {
                                                if(grupo.get(ii).get(j).getPosX()[l]==grupo.get(jj).get(k).getPosX()[m]) {
                                                    x++;
                                                }
                                            }
                                        }
                                    }
                                } catch(Exception e) {
//                                    System.out.println("Error");
                                }
                            }

                            try {
                                if(u>=grupo.get(ii).get(j).getPosUX().length) {
                                    if(x>=grupo.get(ii).get(j).getPosX().length) {
                                        String[] aux = new String[grupo.get(ii).get(j).getBit().length];
                                        for(int n=0;n<grupo.get(ii).get(0).getBit().length;n++) {
                                            if(grupo.get(ii).get(j).getBit()[n].equals(grupo.get(jj).get(k).getBit()[n])) {
                                                aux[n]=grupo.get(ii).get(j).getBit()[n];
                                            } else {
                                                aux[n]="x";
                                            }
                                        }
                                        
                                        grupo.get(ii).get(j).setMarca(1);
                                        grupo.get(jj).get(k).setMarca(1);
                                    
                                        Elemento e = new Elemento(aux,""+grupo.get(ii).get(j).getPosicionTabla()+"-"+grupo.get(jj).get(k).getPosicionTabla());
                                        boolean exist = false;
                                        for(int o=0;o<b.size();o++) {
                                            if(Arrays.equals(e.getBit(), b.get(o).getBit())) {
                                                exist = true;
                                            }
                                        }
                                        if(exist==false) {
                                            b.add(e);
                                        }
                                    }
                                }
                            } catch(Exception e) {
                                
                            }
                            u=0;
                        }
                        
                    }catch(Exception e) {
                    
                    }
                }
                if(b.size()>0) {
                    grupo2.add(b);
                }
                ii++;
                jj++;
            }
            int tam = grupo.size();
            for(int i=0;i<tam;i++) {
                for(int j=0;j<grupo.get(0).size();j++) {
                    if(grupo.get(0).get(j).getMarca()==0) {
                        minusterminos.add(grupo.get(0).get(j));
                    }
                }
                grupo.remove(0);
            }
        
            for(int i=0;i<grupo2.size();i++) {
                grupo.add(grupo2.get(i));
            }
            inicio = false;
        } while(grupo.size()>0);
        
        for(int i=0;i<minusterminos.size();i++) {
            if(i==0){
                this.res = this.res+Interprete.convertir(minusterminos.get(i).getBit());
            }else{
                this.res = this.res+"+"+Interprete.convertir(minusterminos.get(i).getBit());
            }
        }
    }
    
    public String getRes() {
        return this.res;
    }
}
