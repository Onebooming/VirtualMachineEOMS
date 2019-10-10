package view;

import javax.swing.*;

public class JpanelImage extends JFrame {
    private JPanel main,p;
    private Icon ico;
    private JLabel l;
    public JpanelImage(){
        init();
    }

    private void init() {
        main = new JPanel();
        p = new JPanel();
        l = new JLabel();
        ico = new ImageIcon("one.jpg");
        l.setIcon(ico);
        l.setBounds(10,10, ico.getIconWidth(),ico.getIconHeight());
        p.add(l,new Integer(Integer.MIN_VALUE));
        this.setVisible(true);
        this.add(main);
        main.add(p);
        this.pack();
    }
}
