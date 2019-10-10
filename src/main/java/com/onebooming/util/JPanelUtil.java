package com.onebooming.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JPanelUtil extends JPanel {
    Image image = null;
    public void paint(Graphics g){
        try{
            image = ImageIO.read(new File("C:\\Users\\10224683\\Pictures\\one.jpg"));
            g.drawImage(image, 0, 0, 30, 30, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
