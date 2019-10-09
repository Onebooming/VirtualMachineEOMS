package com.onebooming.run;

import com.onebooming.util.DBUtil;
import com.onebooming.view.LoginView;

/**
 * 主函数
 */

public class Main {
    public static void initDB(){
        DBUtil dbUtil = DBUtil.getDBUtil();

        //检查数据库是否初始化
        if(dbUtil.exeute("select 1 from admin")){
            return;
        }

        //初始化数据库
        //admin
        dbUtil.exeute("create table if not exists admin(id int primary key," +
                "name varchar(32)," +
                "username varchar(32)," +
                "password varchar(32))");
        dbUtil.exeute("insert into admin(id, name, username, password) values(1, 'admin', 'test', 'test')");

        //Student
        dbUtil.exeute("create table if not exists vms(" +
                "id int primary key," +
                "sno varchar(16) ," +
                "name varchar(32)," +
                "user varchar(12)," +
                "monitor varchar(32)," +
                "cpu varchar(64)," +
                "memory varchar(32)," +
                "storage varchar(32)," +
                "status varchar(16))");
    }

    public static void main(String[] args) {
        initDB();
        new LoginView();
        DBUtil.getDBUtil().close();
    }
}
