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
 * DAO基类
 */
public class BaseDao {
    //获取DBUtil对象，此对象是不可final修饰的
    protected final DBUtil db = DBUtil.getDBUtil();
    protected ResultSet resultSet;
    private static BaseDao baseDao;

    public BaseDao() {
        init();
    }

    private void init() {

    }

    //实现对象创建
    public static synchronized BaseDao getAbilityDao(DAO dao) {
        switch (dao) {
            case AdminDao:
                //baseDao.getClass() 返回此 类 的运行时类。
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

    //释放ResultSet类资源
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
