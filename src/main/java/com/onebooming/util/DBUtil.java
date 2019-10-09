package com.onebooming.util;
import com.onebooming.AppConstants;

import java.sql.*;
public class DBUtil {
    private static DBUtil db;

    //���ض����ݿ�����ӣ��Ự���� ִ��SQL��䲢�����ӵ��������з��ؽ��
    private Connection con;

    //��ʾԤ�����SQL���Ķ���
    //SQL�����Ԥ���벢�洢��PreparedStatement�����С� Ȼ�����ʹ�øö�������Ч��ִ�д���䡣
    private PreparedStatement ps;

    //��ʾ���ݿ����������ݱ�ͨ��ͨ��ִ�в�ѯ���ݿ��������ɡ�
    private ResultSet resultSet;

    //˽��Ĭ�Ϲ��췽���������ⲿ��������
    private DBUtil(){
        //onebooming:����ʱ��������
        //con = this.getCon();
    }

    //����ģʽ--ÿ��ֻ����һ������ʹ����Դ
    public static DBUtil getDBUtil(){
        if(db == null){
            db = new DBUtil();
        }

        return db;
    }

    //ִ��sql���
    public int executeUpdate(String sql){
        int result = -1;
        if(getCon() == null){
            return result;
        }
        try{
            //PreparedStatement prepareStatement(String sql)
            //����һ�� PreparedStatement�������ڽ���������SQL��䷢�͵����ݿ⡣
            ps = con.prepareStatement(sql);
            //int executeUpdate()
            //ִ���ڸ�SQL���PreparedStatement������������һ��SQL���ݲ������ԣ�DML�����
            result = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    //ִ��sql���
    /*
        para1��SQL��� String����
        para2����������
     */
    public int executeUpdate(String sql,Object[] obj){
        int result = -1;
        if(getCon() == null){
            return result;
        }
        try{
            ps = con.prepareStatement(sql);
            for(int i = 0; i<obj.length; i++){
                //void setObject(int parameterIndex,Object x) throws SQLException
                // ʹ�ø�����������ָ��������ֵ��
                // ��Object���ݸ����ݱ��i������
                ps.setObject(i+1, obj[i]);
            }
            result = ps.executeUpdate();
            close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    //ִ�д� PreparedStatement�����е�SQL��ѯ�������ز�ѯ PreparedStatement�� ResultSet����
    public ResultSet executeQuery(String sql){
        if(getCon() == null){
            return null;
        }
        try{
            //PreparedStatement prepareStatement(String sql)
            //����һ�� PreparedStatement�������ڽ���������SQL��䷢�͵����ݿ⡣
            ps = con.prepareStatement(sql);
            //ResultSet executeQuery() throws SQLException
            //ִ�д� PreparedStatement�����е�SQL��ѯ�������ز�ѯ PreparedStatement�� ResultSet����
            resultSet = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    /*
        ִ�в�ѯ
        ����һ��ResultSet����
     */
    public ResultSet executeQuery(String sql, Object[] obj){
        if(getCon() == null){
            return null;
        }
        try{
            ps = con.prepareStatement(sql);
            //����PrepareStatement����ֵ
            for(int i = 0; i < obj.length; i++){
                ps.setObject(i+1,obj[i]);
            }
            //ִ��PrepareStatement����Ĳ�ѯ��������ѯ������ظ����ݱ����resultSet
            /*
            ResultSet executeQuery() throws SQLException
            ִ�д� PreparedStatement�����е�SQL��ѯ�������ز�ѯ PreparedStatement�� ResultSet����

            ResultSet���󱣳�һ�����ָ���䵱ǰ�������С�
            ��������λ�ڵ�һ��֮ǰ��
            next����������ƶ�����һ�У�
            ����������ResultSet������û�и�����ʱ����false ����˿�����whileѭ����ʹ��ѭ���������������
             */
            resultSet = ps.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    /*
        ִ��sql���
     */
    public boolean exeute(String sql){
        if(getCon() == null){
            return false;
        }
        try{
            //Statement createStatement() throws SQLException
            // ����һ��Statement�������ڽ�SQL��䷢�͵����ݿ�
            // û�в�����SQL���ͨ��ʹ��Statement����ִ�С�
            Statement statement = con.createStatement();
            //boolean execute(String sql)
            //ִ�и�����SQL��䣬����ܻ᷵�ض�����
            statement.execute(sql);
            //�ͷ���Դ
            statement.close();
            return true;
        }catch(SQLException e){
            //e.printStackTrace();
            return false;
        }
    }

    //�������ݿ�����,����Connection����
    private Connection getCon(){
        try{
            if(con == null || con.isClosed()){
                //public static ��<?> forName(String className) throws ClassNotFoundException
                //����������ַ������Ƶ����ӿ�������������
                //���ô˷����൱�ڣ�Class.forName(className, true, currentLoader)
                //����currentLoader��ʾ��ǰ��Ķ������������
                Class.forName(AppConstants.JDBC_DRIVER);

                /*
                public static Connection getConnection(String url,String user,String password)
                ���Խ�����������ݿ�URL�����ӡ� DriverManager���Դ�һ����ע���JDBC����������ѡ���ʵ�����������
                ����
                url - ��ʽΪ jdbc:subprotocol:subname�����ݿ���ַ
                user - �������ӵ����ݿ��û�
                password - �û�����
                ��� - ��URL������
                 */
                con = DriverManager.getConnection(AppConstants.JDBC_URL,AppConstants.JDBC_USERNAME,AppConstants.JDBC_PASSWORD);
            }
        }catch (ClassNotFoundException e){
            System.out.println("jdbc driver is not found.");
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }

    /*
       �����ͷ���Դ
     */
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
