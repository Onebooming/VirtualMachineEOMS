package com.onebooming.view;
import com.onebooming.AppConstants;
import com.onebooming.DAO;
import com.onebooming.base.BaseDao;
import com.onebooming.dao.VMachineDAO;
import com.onebooming.model.VMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddView extends JFrame{
    private static final long serialVersionUID = -1984182788841566838L;

    private JPanel jPanelCenter, jPanelSouth;
    private JButton addButton, exitButton;
    private JTextField name, sno, user, monitor, cpu, memory, storage, status;

    public AddView() {
        init();
    }

    private void init() {
        setTitle(AppConstants.ADDVIEW_TITLE);
        // center panel
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(9, 2));
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_NAME));
        name = new JTextField();
        jPanelCenter.add(name);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_SNO));
        sno = new JTextField();
        jPanelCenter.add(sno);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_USER));
        user = new JTextField();
        jPanelCenter.add(user);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_MONITOR));
        monitor = new JTextField();
        jPanelCenter.add(monitor);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_CPU));
        cpu = new JTextField();
        jPanelCenter.add(cpu);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_MEMORY));
        memory = new JTextField();
        jPanelCenter.add(memory);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_STORAGE));
        storage = new JTextField();
        jPanelCenter.add(storage);
        jPanelCenter.add(new JLabel(AppConstants.STUDENT_STATUS));
        status = new JTextField();
        jPanelCenter.add(status);
        jPanelCenter.add(new JLabel("-------------------------------------------------"));
        jPanelCenter.add(new JLabel("-------------------------------------------------"));

        // south panel
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1, 2));
        addButton = new JButton(AppConstants.ADDVIEW_ADDBUTTON);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()) {
                    //Student stu = new Student();
                    VMachine vm = new VMachine();
                    vm.setId(vm.hashCode());
                    //buildStudent(stu);
                    buildVM(vm);
                    boolean isSuccess = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao)).add(vm);
                    if (isSuccess) {
                        setEmpty();
                        if (com.onebooming.view.MainView.currPageNum < 0 || com.onebooming.view.MainView.currPageNum > 99) {
                            com.onebooming.view.MainView.currPageNum = 1;
                        }
                        String[][] result = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao))
                                .list(com.onebooming.view.MainView.currPageNum);
                        com.onebooming.view.MainView.initJTable(com.onebooming.view.MainView.jTable, result);
                    }
                }
            }
        });
        jPanelSouth.add(addButton);
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
        setBounds(470, 200, 400, 270);
        setResizable(false);
        setVisible(true);
    }

    private boolean check() {
        boolean result = false;
        if ("".equals(name.getText()) || "".equals(sno.getText()) || "".equals(user.getText())
                || "".equals(monitor.getText()) || "".equals(cpu.getText()) || "".equals(memory.getText())
                || "".equals(storage.getText()) || "".equals(status.getText())) {
            return result;
        } else {
            result = true;
        }
        return result;
    }


    private void buildVM(VMachine vm){
        vm.setName(name.getText());
        vm.setSno(sno.getText());
        vm.setUser(user.getText());
        vm.setMonitor(monitor.getText());
        vm.setCpuNum(cpu.getText());
        vm.setMemory(memory.getText());
        vm.setStorage(storage.getText());
        vm.setStatus(status.getText());
    }

    private void setEmpty() {
        name.setText("");
        sno.setText("");
        user.setText("");
        monitor.setText("");
        cpu.setText("");
        memory.setText("");
        storage.setText("");
        status.setText("");
    }
}
