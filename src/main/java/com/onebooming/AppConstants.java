package com.onebooming;

/**
 * @author Onebooming
 * @version 1.0
 * ģ��˵��������
 */

public class AppConstants {
    // jdbc

    public static final String JDBC_URL = "jdbc:mysql://localhost/VMEOMS?serverTimezone=UTC";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "root";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    // student field
    public static final String STUDENT_NAME = "�������";
    public static final String STUDENT_SNO = "Ψһ��ʶ��";
    public static final String STUDENT_USER = "ʹ����";
    public static final String STUDENT_MONITOR = "����Ա";
    public static final String STUDENT_CPU = "CPU����";
    public static final String STUDENT_MEMORY = "�ڴ��С";
    public static final String STUDENT_STORAGE = "�洢����";
    public static final String STUDENT_STATUS = "״̬";

    // login view
    public static final String LOGIN_TITLE = "��¼����";
    public static final String LOGIN_USERNAME = "�û���";
    public static final String LOGIN_PASSWORD = "����";
    public static final String LOGIN = "��¼";
    public static final String RESET = "����";

    // main view
    public static final String MAINVIEW_TITLE = "�������άϵͳ";
    public static final String MAINVIEW_PAGENUM_JLABEL_DI = "�� ";
    public static final String MAINVIEW_PAGENUM_JLABEL_YE = "/99 ҳ";
    public static final String MAINVIEW_FIND_JLABEL = "��ѯ���";
    public static final String MAINVIEW_FIRST = "��ҳ";
    public static final String MAINVIEW_LAST = "ĩҳ";
    public static final String MAINVIEW_PRE = "��һҳ";
    public static final String MAINVIEW_NEXT = "��һҳ";
    public static final String PARAM_FIND_CONDITION = "";
    public static final String PARAM_FIND = "����";
    public static final String PARAM_ADD = "���";
    public static final String PARAM_DELETE = "ɾ��";
    public static final String PARAM_UPDATE = "����";

    // add view
    public static final String ADDVIEW_TITLE = "����������Ϣ";
    public static final String ADDVIEW_ADDBUTTON = "���";
    public static final String EXITBUTTON = "�˳�";

    // delete view
    public static final String DELETEVIEW_TITLE = "ɾ���������Ϣ";
    public static final String DELETEVIEW_DELETEBUTTON = "ɾ��";

    // update view
    public static final String UPDATEVIEW_TITLE = "�����������Ϣ";
    public static final String UPDATEVIEW_UPDATEBUTTON = "����";
}
