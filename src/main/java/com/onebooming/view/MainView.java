package com.onebooming.view;
/**
 * 主菜单
 */

import com.onebooming.AppConstants;
import com.onebooming.DAO;
import com.onebooming.base.BaseDao;
import com.onebooming.dao.VMachineDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class MainView extends JFrame{
    private static final long serialVersionUID = 5870864087464173884L;

    private final int maxPageNum = 99;

    private JPanel jPanelNorth, jPanelSouth, jPanelCenter;
    private JButton jButtonFirst, jButtonLast, jButtonNext, jButtonPre, jButtonAdd, jButtonDelete, jButtonUpdate,
            jButtonFind, JButtonF1;
    private JLabel currPageNumJLabel;
    private JTextField condition;
    public static JTable jTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;

    public static String[] column = { "id", AppConstants.STUDENT_NAME, AppConstants.STUDENT_SNO,
            AppConstants.STUDENT_USER, AppConstants.STUDENT_MONITOR, AppConstants.STUDENT_CPU,
            AppConstants.STUDENT_MEMORY, AppConstants.STUDENT_STORAGE, AppConstants.STUDENT_STATUS };
    public static int currPageNum = 1;

    public MainView() {
        init();
    }

    private void init() {
        setTitle(AppConstants.MAINVIEW_TITLE);

        // north panel
        jPanelNorth = new JPanel();
        //设置布局：1行5列
        jPanelNorth.setLayout(new GridLayout(1, 6));
        //JTextField对象
        condition = new JTextField(AppConstants.PARAM_FIND_CONDITION);
        condition.addKeyListener(new FindListener());
        jPanelNorth.add(condition);
        // query by name
        jButtonFind = new JButton(AppConstants.PARAM_FIND);
        jButtonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                find();
            }
        });
        jButtonFind.addKeyListener(new FindListener());
        // add
        jPanelNorth.add(jButtonFind);
        jButtonAdd = new JButton(AppConstants.PARAM_ADD);
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddView();
            }
        });
        jPanelNorth.add(jButtonAdd);
        // delete
        jButtonDelete = new JButton(AppConstants.PARAM_DELETE);
        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new com.up.student.view.DeleteView();
                new com.onebooming.view.DeleteView();
            }
        });
        jPanelNorth.add(jButtonDelete);
        // update
        jButtonUpdate = new JButton(AppConstants.PARAM_UPDATE);
        jButtonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new com.up.student.view.UpdateView();
                new com.onebooming.view.UpdateView();
            }
        });
        jPanelNorth.add(jButtonUpdate);
        //new function
        JButtonF1 = new JButton(AppConstants.ATTACHVIEW_ATTACHBUTTON);
        JButtonF1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new com.onebooming.view.AttachFuntion();
            }
        });
        jPanelNorth.add(JButtonF1);




        // center panel
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(1, 1));

        // init jTable
        String[][] result = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao)).list(currPageNum);
        myTableModel = new DefaultTableModel(result, column);
        jTable = new JTable(myTableModel);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class, cr);
        initJTable(jTable, result);

        jScrollPane = new JScrollPane(jTable);
        jPanelCenter.add(jScrollPane);

        // south panel
        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1, 5));

        jButtonFirst = new JButton(AppConstants.MAINVIEW_FIRST);
        jButtonFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currPageNum = 1;
                String[][] result = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao)).list(currPageNum);
                initJTable(jTable, result);
                currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
                        + AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
            }
        });
        jButtonPre = new JButton(AppConstants.MAINVIEW_PRE);
        jButtonPre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currPageNum--;
                if (currPageNum <= 0) {
                    currPageNum = 1;
                }
                String[][] result = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao)).list(currPageNum);
                initJTable(jTable, result);
                currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
                        + AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
            }
        });
        jButtonNext = new JButton(AppConstants.MAINVIEW_NEXT);
        jButtonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currPageNum++;
                if (currPageNum > maxPageNum) {
                    currPageNum = maxPageNum;
                }
                String[][] result = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao)).list(currPageNum);
                initJTable(jTable, result);
                currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
                        + AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
            }
        });
        jButtonLast = new JButton(AppConstants.MAINVIEW_LAST);
        jButtonLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currPageNum = maxPageNum;
                String[][] result = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao)).list(currPageNum);
                initJTable(jTable, result);
                currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
                        + AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
            }
        });

        currPageNumJLabel = new JLabel(
                AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum + AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
        currPageNumJLabel.setHorizontalAlignment(JLabel.CENTER);

        jPanelSouth.add(jButtonFirst);
        jPanelSouth.add(jButtonPre);
        jPanelSouth.add(currPageNumJLabel);
        jPanelSouth.add(jButtonNext);
        jPanelSouth.add(jButtonLast);

        this.add(jPanelNorth, BorderLayout.NORTH);
        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);

        setBounds(400, 200, 750, 340);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void initJTable(JTable jTable, String[][] result) {
        ((DefaultTableModel) jTable.getModel()).setDataVector(result, column);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.setRowHeight(20);
        TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
        firsetColumn.setPreferredWidth(30);
        firsetColumn.setMaxWidth(30);
        firsetColumn.setMinWidth(30);
        TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(120);
        secondColumn.setMaxWidth(120);
        secondColumn.setMinWidth(100);
        TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(120);
        thirdColumn.setMaxWidth(120);
        thirdColumn.setMinWidth(100);
        TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(50);
        fourthColumn.setMaxWidth(60);
        fourthColumn.setMinWidth(30);
        TableColumn seventhColumn = jTable.getColumnModel().getColumn(6);
        seventhColumn.setPreferredWidth(30);
        seventhColumn.setMaxWidth(30);
        seventhColumn.setMinWidth(30);
        TableColumn ninthColumn = jTable.getColumnModel().getColumn(8);
        ninthColumn.setPreferredWidth(30);
        ninthColumn.setMaxWidth(30);
        ninthColumn.setMinWidth(30);
    }

    private class FindListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                find();
            }
        }
    }

    private void find() {
        currPageNum = 0;
        String param = condition.getText();
        if ("".equals(param) || param == null) {
            initJTable(MainView.jTable, null);
            currPageNumJLabel.setText(AppConstants.MAINVIEW_FIND_JLABEL);
            return;
        }
        String[][] result = ((VMachineDAO) BaseDao.getAbilityDao(DAO.VMachineDao)).queryByName(param);
        condition.setText("");
        initJTable(MainView.jTable, result);
        currPageNumJLabel.setText(AppConstants.MAINVIEW_FIND_JLABEL);
    }
}
