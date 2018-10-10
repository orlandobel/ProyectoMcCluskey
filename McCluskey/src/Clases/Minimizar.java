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
 
    private int[] minterminos;
    private int min;
    private int max;
    private String res;
    private ArrayList<Bits> tabla;
    
    public Minimizar(int minterminos[]) {
        this.minterminos = minterminos;
        this.tabla = new ArrayList<>();
        this.res = " ";
        
        this.min = 20;
        this.max = 0;
    }
    
    public void Agregar(Bits e) {
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
        ArrayList<ArrayList<Bits>> grupo = new ArrayList<ArrayList<Bits>>(max); //arreglo n elementos
        ArrayList<Bits> minusterminos = new ArrayList<>();
        
        for(int i=min;i<=max;i++) { //agrupacion n elementos
            ArrayList<Bits> aux = new ArrayList<>();
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
            ArrayList<ArrayList<Bits>> grupo2 = new ArrayList<ArrayList<Bits>>();
            for(int i=0;i<grupo.size();i++) {
                ArrayList<Bits> b = new ArrayList<>();
                for(int j=0;j<=grupo.get(ii).size();j++) {
                    try {
                        
                        for(int k=0;k<grupo.get(jj).size();k++) {
                            int unI = grupo.get(ii).get(0).getPosUX().length;
                            int unJ = grupo.get(jj).get(0).getPosUX().length;
                            
                            int xI = grupo.get(ii).get(0).getPosX().length;
                            int xJ = grupo.get(jj).get(0).getPosX().length;
                            
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
                                } catch(Exception e) {
//                                    System.out.println("Error");
                                }
                            }
                            if(inicio==false){
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
                                    
                                        Bits e = new Bits(aux,""+grupo.get(ii).get(j).getPosicionTabla()+"-"+grupo.get(jj).get(k).getPosicionTabla());
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
                    if (y==this.minterminos[z]){
                        simpli.add(Simplificar);
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
        
        
        
        for(int i=0;i<simpli.size();i++) {
            if(i==0){
                this.res = this.res+Convertir.convertir(simpli.get(i).getBit());
            }else{
                this.res = this.res+"+"+Convertir.convertir(simpli.get(i).getBit());
            }
        }
    
    
        
        
    }
        // Arreglo del codigo de memo
    public String getRes() {
        return this.res;
    }
}