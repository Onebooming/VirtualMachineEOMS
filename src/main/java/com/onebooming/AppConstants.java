package com.onebooming;

/**
 * @author Onebooming
 * @version 1.0
 * 模块说明：常量
 */

public class AppConstants {
    // jdbc

    public static final String JDBC_URL = "jdbc:mysql://localhost/VMEOMS?serverTimezone=UTC";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "root";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    // student field
    public static final String STUDENT_NAME = "虚拟机名";
    public static final String STUDENT_SNO = "唯一标识符";
    public static final String STUDENT_USER = "使用者";
    public static final String STUDENT_MONITOR = "管理员";
    public static final String STUDENT_CPU = "CPU核数";
    public static final String STUDENT_MEMORY = "内存大小";
    public static final String STUDENT_STORAGE = "存储容量";
    public static final String STUDENT_STATUS = "状态";

    // login view
    public static final String LOGIN_TITLE = "登录界面";
    public static final String LOGIN_USERNAME = "用户名";
    public static final String LOGIN_PASSWORD = "密码";
    public static final String LOGIN = "登录";
    public static final String RESET = "重置";

    // main view
    public static final String MAINVIEW_TITLE = "虚拟机运维系统";
    public static final String MAINVIEW_PAGENUM_JLABEL_DI = "第 ";
    public static final String MAINVIEW_PAGENUM_JLABEL_YE = "/99 页";
    public static final String MAINVIEW_FIND_JLABEL = "查询结果";
    public static final String MAINVIEW_FIRST = "首页";
    public static final String MAINVIEW_LAST = "末页";
    public static final String MAINVIEW_PRE = "上一页";
    public static final String MAINVIEW_NEXT = "下一页";
    public static final String PARAM_FIND_CONDITION = "";
    public static final String PARAM_FIND = "查找";
    public static final String PARAM_ADD = "添加";
    public static final String PARAM_DELETE = "删除";
    public static final String PARAM_UPDATE = "更新";

    // add view
    public static final String ADDVIEW_TITLE = "添加虚拟机信息";
    public static final String ADDVIEW_ADDBUTTON = "添加";
    public static final String EXITBUTTON = "退出";

    // delete view
    public static final String DELETEVIEW_TITLE = "删除虚拟机信息";
    public static final String DELETEVIEW_DELETEBUTTON = "删除";

    // update view
    public static final String UPDATEVIEW_TITLE = "更新虚拟机信息";
    public static final String UPDATEVIEW_UPDATEBUTTON = "更新";
}
