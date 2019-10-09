package com.onebooming.view;
/**
 * ��¼����
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
    //JPanel��һ��ͨ�õ�����������
    private JPanel jPanelCenter, jPanelSouth;
    //JTextField��һ�����������������༭�����ı�
    private JTextField username;
    //JPasswordField��һ�����������������༭�����ı���������ͼָʾ��������ݣ�������ʾԭʼ�ַ�
    private JPasswordField password;
    //ʵ�֡��ơ���ť��
    //��ť�������ã�����һ���̶����ܵ�Action�Ŀ��ơ� ʹ�ô���ť��Action����ֱ�����ð�ť֮�⣬���кܶ�ô���
    private JButton loginButton, resetButton;

    public LoginView() {
        init();
    }

    //�����ʼ��
    private void init() {
        //this.setTitle("Login");
        this.setTitle(AppConstants.LOGIN_TITLE);
        //����һ��Jpanel����
        jPanelCenter = new JPanel();
        //setLayout���ô������Ĳ��ֹ�������
        //�÷������Ĳ��������Ϣ�����ʹ�����νṹ��Ч��
        //GridLayout����һ�����ֹ�����(ʵ����LayoutManager�ӿ�)������һ��������������ھ��������С�
        // �������ֳɵȴ�С�ľ��Σ�����ÿ�������з���һ�������
        jPanelCenter.setLayout(new GridLayout(3, 2));

        /*
            public void add(PopupMenu popup)��ָ���ĵ����˵���ӵ������
            PopupMenu---MenuContainer---JLabel��-MenuContainer��ʵ����
            JLabel(String text)  ʹ��ָ�����ı�����һ�� JLabelʵ����
         */
        //��ӵ�¼�����
        jPanelCenter.add(new JLabel(AppConstants.LOGIN_USERNAME));

        //����һ��JTextField���󣬸�ֵ��username
        username = new JTextField();
        //public void add(PopupMenu popup)��ָ���ĵ����˵���ӵ����
        jPanelCenter.add(username);
        //��¼�������
        jPanelCenter.add(new JLabel(AppConstants.LOGIN_PASSWORD));
        password = new JPasswordField();
        // enter key listener
        password.addKeyListener(new LoginListener());
        jPanelCenter.add(password);
        jPanelCenter.add(new JLabel("----------------------------------------------"));
        jPanelCenter.add(new JLabel("----------------------------------------------"));

        /*
            ���õ�¼����ĵ�¼��ť�����ð�ť�����������ͼ��̼���
         */
        jPanelSouth = new JPanel();
        //һ������
        jPanelSouth.setLayout(new GridLayout(1, 2));
        //��¼��ť����
        loginButton = new JButton(AppConstants.LOGIN);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });
        jPanelSouth.add(loginButton);
        //���ð�ť����
        resetButton = new JButton(AppConstants.RESET);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                password.setText("");
            }
        });
        jPanelSouth.add(resetButton);

        //����λ��
        /*
        public Component add(Component comp,int index)�ڸ�����λ�ý�ָ���������ӵ�������
         */
        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        /*
        public void setDefaultCloseOperation(int operation)�����û��ڴ˿�����������رա�ʱĬ��ִ�еĲ����� ������ָ������ѡ��֮һ��
            DO_NOTHING_ON_CLOSE ��������WindowConstants ������Ҫ���κ�����; Ҫ�������WindowListener�����windowClosing�����Ĳ�����
            HIDE_ON_CLOSE ����WindowConstants���壩���ڵ����κ���ע���WindowListener������Զ����ؿ�ܡ�
            DISPOSE_ON_CLOSE ����WindowConstants���壩���ڵ����κ���ע���WindowListener������Զ����غʹ���ÿ�ܡ�
            EXIT_ON_CLOSE ����JFrame���壩��ʹ��System exit�����˳�exit���� ����Ӧ�ó�����ʹ�á�
        ��ֵĬ������ΪHIDE_ON_CLOSE �� �Դ����Ե�ֵ�ĸ��ĵ��´������Ը����¼�����������Ϊ��defaultCloseOperation
         */
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        /*
         public void setBounds(int x,int y,int width,int height)�ƶ�������������Ĵ�С��
         ���Ͻǵ���λ����x��y ���³ߴ���width��height ��
         */
        this.setBounds(450, 250, 375, 140);
        //this.setBounds(450,230,575,280);

        //public void setResizable(boolean resizable)���øÿ���Ƿ�������û�������С��
        this.setResizable(false);
        //public void setVisible(boolean b) b��ֵ��ʾ�����ش˴��� ��
        this.setVisible(true);
    }

    private class LoginListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //public int getKeyCode()��������¼��еļ������������keyCode��
            //����Ǽ�����ʵ�ʼ�����������
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
