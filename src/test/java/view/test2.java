package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test2 {
    private JFrame frame = new JFrame("����ͼƬ����");

    private JPanel imagePanel ;

    private ImageIcon background;

    public static void main(String[] args)
    {
        new test2();
    }

    public test2()
    {
        background = new ImageIcon("one.jpg");//����ͼƬ
        JLabel label = new JLabel(background);//�ѱ���ͼƬ��ʾ��һ����ǩ����
        //     �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
        label.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        //     �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
        imagePanel = (JPanel)frame.getContentPane();
        imagePanel.setOpaque(false);
        //     ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout
        imagePanel.setLayout(new FlowLayout());
        imagePanel.add(new JButton("���԰�ť"));

        frame.getLayeredPane().setLayout(null);
        //     �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
        frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(background.getIconWidth(),background.getIconHeight());
        frame.setVisible(true);
    }
}
