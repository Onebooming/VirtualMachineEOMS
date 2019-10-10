package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test2 {
    private JFrame frame = new JFrame("背景图片测试");

    private JPanel imagePanel ;

    private ImageIcon background;

    public static void main(String[] args)
    {
        new test2();
    }

    public test2()
    {
        background = new ImageIcon("one.jpg");//背景图片
        JLabel label = new JLabel(background);//把背景图片显示在一个标签里面
        //     把标签的大小位置设置为图片刚好填充整个面板
        label.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        //     把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        imagePanel = (JPanel)frame.getContentPane();
        imagePanel.setOpaque(false);
        //     内容窗格默认的布局管理器为BorderLayout
        imagePanel.setLayout(new FlowLayout());
        imagePanel.add(new JButton("测试按钮"));

        frame.getLayeredPane().setLayout(null);
        //     把背景图片添加到分层窗格的最底层作为背景
        frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(background.getIconWidth(),background.getIconHeight());
        frame.setVisible(true);
    }
}
