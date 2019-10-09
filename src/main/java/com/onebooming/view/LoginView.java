package com.onebooming.view;
/**
 * 登录界面
 */

import com.onebooming.AppConstants;
import com.onebooming.DAO;
import com.onebooming.base.BaseDao;
import com.onebooming.dao.AdminDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView extends JFrame{
    private static final long serialVersionUID = -5278598737087831336L;
    //JPanel是一个通用的轻量级容器
    private JPanel jPanelCenter, jPanelSouth;
    //JTextField是一个轻量级组件，允许编辑单行文本
    private JTextField username;
    //JPasswordField是一个轻量级组件，允许编辑单行文本，其中视图指示输入的内容，但不显示原始字符
    private JPasswordField password;
    //实现“推”按钮。
    //按钮可以配置，并在一定程度上受到Action的控制。 使用带按钮的Action除了直接配置按钮之外，还有很多好处。
    private JButton loginButton, resetButton;

    public LoginView() {
        init();
    }

    //界面初始化
    private void init() {
        //this.setTitle("Login");
        this.setTitle(AppConstants.LOGIN_TITLE);
        //创建一个Jpanel对象
        jPanelCenter = new JPanel();
        //setLayout设置此容器的布局管理器。
        //该方法更改布局相关信息，因此使组件层次结构无效。
        //GridLayout类是一个布局管理器(实现了LayoutManager接口)，它将一个容器的组件放在矩形网格中。
        // 容器被分成等大小的矩形，并且每个矩形中放置一个组件。
        jPanelCenter.setLayout(new GridLayout(3, 2));

        /*
            public void add(PopupMenu popup)将指定的弹出菜单添加到组件。
            PopupMenu---MenuContainer---JLabel是-MenuContainer的实现类
            JLabel(String text)  使用指定的文本创建一个 JLabel实例。
         */
        //添加登录名组件
        jPanelCenter.add(new JLabel(AppConstants.LOGIN_USERNAME));

        //创建一个JTextField对象，赋值给username
        username = new JTextField();
        //public void add(PopupMenu popup)将指定的弹出菜单添加到组件
        jPanelCenter.add(username);
        //登录密码组件
        jPanelCenter.add(new JLabel(AppConstants.LOGIN_PASSWORD));
        password = new JPasswordField();
        // enter key listener
        password.addKeyListener(new LoginListener());
        jPanelCenter.add(password);
        jPanelCenter.add(new JLabel("----------------------------------------------"));
        jPanelCenter.add(new JLabel("----------------------------------------------"));

        /*
            设置登录界面的登录按钮和重置按钮，并加入鼠标和键盘监听
         */
        jPanelSouth = new JPanel();
        //一行两列
        jPanelSouth.setLayout(new GridLayout(1, 2));
        //登录按钮设置
        loginButton = new JButton(AppConstants.LOGIN);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });
        jPanelSouth.add(loginButton);
        //重置按钮设置
        resetButton = new JButton(AppConstants.RESET);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                password.setText("");
            }
        });
        jPanelSouth.add(resetButton);

        //设置位置
        /*
        public Component add(Component comp,int index)在给定的位置将指定的组件添加到此容器
         */
        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        /*
        public void setDefaultCloseOperation(int operation)设置用户在此框架上启动“关闭”时默认执行的操作。 您必须指定以下选项之一：
            DO_NOTHING_ON_CLOSE （定义在WindowConstants ）：不要做任何事情; 要求程序处理WindowListener对象的windowClosing方法的操作。
            HIDE_ON_CLOSE （在WindowConstants定义）：在调用任何已注册的WindowListener对象后自动隐藏框架。
            DISPOSE_ON_CLOSE （在WindowConstants定义）：在调用任何已注册的WindowListener对象后自动隐藏和处理该框架。
            EXIT_ON_CLOSE （在JFrame定义）：使用System exit方法退出exit程序。 仅在应用程序中使用。
        该值默认设置为HIDE_ON_CLOSE 。 对此属性的值的更改导致触发属性更改事件，属性名称为“defaultCloseOperation
         */
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        /*
         public void setBounds(int x,int y,int width,int height)移动并调整此组件的大小。
         左上角的新位置由x和y ，新尺寸由width和height 。
         */
        this.setBounds(450, 250, 375, 140);
        //this.setBounds(450,230,575,280);

        //public void setResizable(boolean resizable)设置该框架是否可以由用户调整大小。
        this.setResizable(false);
        //public void setVisible(boolean b) b的值显示或隐藏此窗口 。
        this.setVisible(true);
    }

    private class LoginListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //public int getKeyCode()返回与此事件中的键相关联的整数keyCode。
            //结果是键盘上实际键的整数代码
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                check();
            }
        }
    }

    private void check() {
        AdminDAO adminDAO = (AdminDAO) BaseDao.getAbilityDao(DAO.AdminDao);
        //AdminDAO adminDAO = AdminDAO.getInstance();
        if (adminDAO.queryForLogin(username.getText(), String.valueOf(password.getPassword()))) {
            //if(adminDAO.queryForLogin("test", "test")){
            dispose();
            new MainView();
        } else {
            username.setText("");
            password.setText("");
        }
    }
}
