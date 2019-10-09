package com.onebooming.dao;

import com.onebooming.base.BaseDao;

import java.sql.SQLException;

/**
 * Admin��ɾ�Ĳ�
 */

public class AdminDAO extends BaseDao {
    //����ģʽ
    private static AdminDAO ad = null;
    public static synchronized AdminDAO getInstance(){
        if(ad == null){
            ad = new AdminDAO();
        }
        return ad;
    }

    /*
        ѯ�ʵ�¼���������ݿ⣬���admin���ݱ��д�����������˺�������ϵ����ݣ����¼�ɹ�
     */
    public boolean queryForLogin(String username,String password){
        boolean result = false;
        if(username.length() == 0 || password.length() == 0){
            return result;
        }
        String sql = "select * from admin where username=? and password=?";
        //���βδ��� param��
        String[] param = {username,password};
        //��DBUtil�������в�ѯ����
        resultSet = db.executeQuery(sql,param);
        try{
            //resultSet.next()Ϊtrue�������ݿ��в�ѯ������Ӧ���˺�����
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