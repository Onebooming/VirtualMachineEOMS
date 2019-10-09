package com.onebooming.base;

import com.onebooming.DAO;
import com.onebooming.dao.AdminDAO;
import com.onebooming.dao.VMachineDAO;
import com.onebooming.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Onebooming
 * @version 1.0
 * DAO����
 */
public class BaseDao {
    //��ȡDBUtil���󣬴˶����ǲ���final���ε�
    protected final DBUtil db = DBUtil.getDBUtil();
    protected ResultSet resultSet;
    private static BaseDao baseDao;

    public BaseDao() {
        init();
    }

    private void init() {

    }

    //ʵ�ֶ��󴴽�
    public static synchronized BaseDao getAbilityDao(DAO dao) {
        switch (dao) {
            case AdminDao:
                //baseDao.getClass() ���ش� �� ������ʱ�ࡣ
                if (baseDao == null || baseDao.getClass() !=  AdminDAO.class) {
                    baseDao = AdminDAO.getInstance();
                }
                break;
            case VMachineDao:
                if (baseDao == null || baseDao.getClass() != VMachineDAO.class) {
                   baseDao = VMachineDAO.getInstance();
                }
                break;
            default:
                break;

        }
        return baseDao;
    }

    //�ͷ�ResultSet����Դ
    protected void destroy(){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
