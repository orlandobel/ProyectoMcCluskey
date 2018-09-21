/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author orlando
 */
public class BitsPanel extends JPanel implements ActionListener {
    private int index;
    private Map nota = new HashMap();
    
    public BitsPanel() {
        this.setSize(300,400);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new FlowLayout());
    }
    
    public void Bts() {
        Bits b = new Bits();
        this.add(b);
        this.validate();
        this.nota.put(index, b);
        index++;
    }
    
    
}
