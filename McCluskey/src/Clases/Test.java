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
public class Test {
    public static void main(String[] args) {
        // TODO code application logic here
        int min[] = new int[]{3,4,7,9,10};
        
        String v1[] = new String[]{"0","0","0","0"};//
        String v2[] = new String[]{"0","0","0","1"};//
        String v3[] = new String[]{"0","0","1","0"};//
        String v4[] = new String[]{"0","0","1","1"};
        String v5[] = new String[]{"0","1","0","0"};//
        String v6[] = new String[]{"0","1","1","1"};
        String v7[] = new String[]{"1","0","0","1"};
        String v8[] = new String[]{"1","0","1","0"};
        String v9[] = new String[]{"1","1","0","1"};
        String v10[] = new String[]{"1","1","1","0"};
        String v11[] = new String[]{"1","1","1","1"};
        
        Bits e1 = new Bits(v1,"0");
        Bits e2 = new Bits(v2,"1");
        Bits e3 = new Bits(v3,"2");
        Bits e4 = new Bits(v4,"3");
        Bits e5 = new Bits(v5,"4");
        Bits e6 = new Bits(v6,"7");
        Bits e7 = new Bits(v7,"9");
        Bits e8 = new Bits(v8,"10");
        Bits e9 = new Bits(v9,"13");
        Bits e10 = new Bits(v10,"14");
        Bits e11 = new Bits(v11,"15");
        
        Minimizar m = new Minimizar(min);
        m.Agregar(e1);
        m.Agregar(e2);
        m.Agregar(e3);
        m.Agregar(e4);
        m.Agregar(e5);
        m.Agregar(e6);
        m.Agregar(e7);
        m.Agregar(e8);
        m.Agregar(e9);
        m.Agregar(e10);
        m.Agregar(e11);
        
        m.Agrupacion();
        
        System.out.println("! -> significa negacion\nEjemplo : A! = A negada\n");
        System.out.println("El resultado final es : "+m.getRes());
        System.out.println("\n\nFalta la parte de memo para minimizar todo lo posible, ahorita arroja todo el resultado tal cual");
        System.out.println();
        
    }
}
