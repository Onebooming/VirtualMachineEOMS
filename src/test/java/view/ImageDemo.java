package view;

import javax.swing.*;
import java.awt.*;

public class ImageDemo extends JFrame {
    MyJPanel mjp = null;
    public ImageDemo(){
        mjp = new MyJPanel();
        this.add(mjp);
        this.setSize(550,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ImageDemo imageDemo = new ImageDemo();

    }


}


