package com.onebooming.view;

import com.onebooming.AppConstants;
import com.onebooming.util.JPanelUtil;

import javax.swing.*;
import java.awt.*;

public class AttachFuntion  extends JFrame {
    private static final long serialVersionUID = 5292738820127102732L;
    private JPanel jPanelCenter, jPanelSouth;
    private JButton  exitButton;
    private JTextField  text1, text2, text3;
    private JLabel pic;
    private Icon icon;
    public AttachFuntion() {
        init();
    }

    private void init() {
        setTitle(AppConstants.UPDATEVIEW_TITLE);
        // center panel
        jPanelCenter = new JPanel();
        //将面板拆分成1行2列
        jPanelCenter.setLayout(new GridLayout(1, 2));
        jPanelCenter.add(new JLabel("Onebooming"));
        //jPanelCenter.setMaximumSize();
        jPanelCenter.add(new JLabel("Onebooming"));
        // south panel
        jPanelSouth = new JPanel();
        //将面板拆分成1行2列
        jPanelSouth.setLayout(new GridLayout(1, 2));
        //JPanel中在第一列中添加图片
        /*pic = new JLabel();
        icon = new ImageIcon("C:\\Users\\10224683\\Pictures\\one.jpg");
        pic.setIcon(icon);
        pic.setBounds(20, 20, icon.getIconWidth(),icon.getIconHeight());
        jPanelSouth.add(pic,new Integer(Integer.MIN_VALUE));
        jPanelSouth.add(new JLabel("Onebooming"));*/
        JPanelUtil mjp = new JPanelUtil();
        pic = new JLabel();
        pic.add(mjp);
        jPanelSouth.add(mjp);
        jPanelSouth.add(new JLabel("Onebooming"));

        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(470, 200, 400, 400);
        setResizable(false);
        setVisible(true);
    }
}
