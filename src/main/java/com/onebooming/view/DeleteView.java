package com.onebooming.view;
/**
 * 删除学生
 */

import com.onebooming.AppConstants;
import com.onebooming.DAO;
import com.onebooming.base.BaseDao;
import com.onebooming.dao.VMachineDAO;
import com.onebooming.model.VMachine;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteView extends JFrame {
    private static final long serialVersionUID = -7668153283910203959L;

    private JPanel jPanelCenter, jPanelSouth;
    private JButton deleteButton, exitButton;
    private JTextField name, sno; // 根据姓名+学号删除学生

    public DeleteView() {
        init();
    }

    private void init() {
        setTitle(AppConstants.DELETEVIEW_TITLE);
        // center panel
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(3, 2));
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_NAME));
        name = new JTextField();
        jPanelCenter.add(name);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_SNO));
        sno = new JTextField();
        jPanelCenter.add(sno);
        jPanelCenter.add(new JLabel("-------------------------------------------------"));
        jPanelCenter.add(new JLabel("-------------------------------------------------"));

        // south panel
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1, 2));
        deleteButton = new JButton(AppConstants.DELETEVIEW_DELETEBUTTON);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()) {
                    VMachine vm = new VMachine();
                    buildVM(vm);
                    boolean isSuccess = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao)).delete(vm);
                    if (isSuccess) {
                        setEmpty();
                        if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
                            MainView.currPageNum = 1;
                        }
                        String[][] result = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao))
                                .list(MainView.currPageNum);
                        MainView.initJTable(MainView.jTable, result);
                    }
                }
            }
        });
        jPanelSouth.add(deleteButton);
        exitButton = new JButton(AppConstants.EXITBUTTON);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jPanelSouth.add(exitButton);

        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(470, 250, 400, 130);
        setResizable(false);
        setVisible(true);
    }

    private boolean check() {
        boolean result = false;
        if ("".equals(name.getText()) || "".equals(sno.getText())) {
            return result;
        } else {
            result = true;
        }
        return result;
    }

    private void buildVM(VMachine vMachine) {
        vMachine.setName(name.getText());
        vMachine.setSno(sno.getText());
    }

    private void setEmpty() {
        name.setText("");
        sno.setText("");
    }
}
