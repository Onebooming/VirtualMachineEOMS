package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyJPanel extends JPanel {
    Image image = null;
    public void paint(Graphics g){
        try{
            image = ImageIO.read(new File("C:\\Users\\10224683\\Desktop\\one.jpg"));
            g.drawImage(image, 0, 0, 550, 400, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
