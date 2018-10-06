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
        int min[] = new int[]{4,7,9,10};
        
        String v1[] = new String[]{"0","0","0","0"};
        String v2[] = new String[]{"0","0","0","1"};
        String v3[] = new String[]{"0","0","1","0"};
        String v4[] = new String[]{"0","0","1","1"};
        String v5[] = new String[]{"0","1","0","0"};
        String v6[] = new String[]{"0","1","1","1"};
        String v7[] = new String[]{"1","0","0","1"};
        String v8[] = new String[]{"1","0","1","0"};
        String v9[] = new String[]{"1","1","0","1"};
        String v10[] = new String[]{"1","1","1","0"};
        String v11[] = new String[]{"1","1","1","1"};
        
        Elemento e1 = new Elemento(v1,"0");
        Elemento e2 = new Elemento(v2,"1");
        Elemento e3 = new Elemento(v3,"2");
        Elemento e4 = new Elemento(v4,"3");
        Elemento e5 = new Elemento(v5,"4");
        Elemento e6 = new Elemento(v6,"7");
        Elemento e7 = new Elemento(v7,"9");
        Elemento e8 = new Elemento(v8,"10");
        Elemento e9 = new Elemento(v9,"13");
        Elemento e10 = new Elemento(v10,"14");
        Elemento e11 = new Elemento(v11,"15");
        
        Mac m = new Mac(min);
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
