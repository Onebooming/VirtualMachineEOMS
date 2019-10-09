package com.onebooming.dao;

import com.onebooming.base.BaseDao;

import java.sql.SQLException;

/**
 * Admin增删改查
 */

public class AdminDAO extends BaseDao {
    //单例模式
    private static AdminDAO ad = null;
    public static synchronized AdminDAO getInstance(){
        if(ad == null){
            ad = new AdminDAO();
        }
        return ad;
    }

    /*
        询问登录：遍历数据库，如果admin数据表中存在于输入的账号密码符合的数据，则登录成功
     */
    public boolean queryForLogin(String username,String password){
        boolean result = false;
        if(username.length() == 0 || password.length() == 0){
            return result;
        }
        String sql = "select * from admin where username=? and password=?";
        //将形参传入 param中
        String[] param = {username,password};
        //在DBUtil类中运行查询方法
        resultSet = db.executeQuery(sql,param);
        try{
            //resultSet.next()为true，则数据库中查询到了相应的账号密码
            if(resultSet.next()){
                result = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return result;

    }
}