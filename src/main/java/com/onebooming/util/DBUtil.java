package com.onebooming.util;
import com.onebooming.AppConstants;

import java.sql.*;
public class DBUtil {
    private static DBUtil db;

    //与特定数据库的连接（会话）。 执行SQL语句并在连接的上下文中返回结果
    private Connection con;

    //表示预编译的SQL语句的对象。
    //SQL语句已预编译并存储在PreparedStatement对象中。 然后可以使用该对象多次有效地执行此语句。
    private PreparedStatement ps;

    //表示数据库结果集的数据表，通常通过执行查询数据库的语句生成。
    private ResultSet resultSet;

    //私有默认构造方法，不让外部创建对象
    private DBUtil(){
        //onebooming:构造时及即连接
        //con = this.getCon();
    }

    //单例模式--每次只允许一个对象使用资源
    public static DBUtil getDBUtil(){
        if(db == null){
            db = new DBUtil();
        }

        return db;
    }

    //执行sql语句
    public int executeUpdate(String sql){
        int result = -1;
        if(getCon() == null){
            return result;
        }
        try{
            //PreparedStatement prepareStatement(String sql)
            //创建一个 PreparedStatement对象，用于将参数化的SQL语句发送到数据库。
            ps = con.prepareStatement(sql);
            //int executeUpdate()
            //执行在该SQL语句PreparedStatement对象，它必须是一个SQL数据操纵语言（DML）语句
            result = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    //执行sql语句
    /*
        para1：SQL语句 String类型
        para2：对象数组
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
                // 使用给定对象设置指定参数的值。
                // 将Object传递给数据表第i个参数
                ps.setObject(i+1, obj[i]);
            }
            result = ps.executeUpdate();
            close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    //执行此 PreparedStatement对象中的SQL查询，并返回查询 PreparedStatement的 ResultSet对象
    public ResultSet executeQuery(String sql){
        if(getCon() == null){
            return null;
        }
        try{
            //PreparedStatement prepareStatement(String sql)
            //创建一个 PreparedStatement对象，用于将参数化的SQL语句发送到数据库。
            ps = con.prepareStatement(sql);
            //ResultSet executeQuery() throws SQLException
            //执行此 PreparedStatement对象中的SQL查询，并返回查询 PreparedStatement的 ResultSet对象
            resultSet = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    /*
        执行查询
        返回一个ResultSet对象
     */
    public ResultSet executeQuery(String sql, Object[] obj){
        if(getCon() == null){
            return null;
        }
        try{
            ps = con.prepareStatement(sql);
            //设置PrepareStatement对象值
            for(int i = 0; i < obj.length; i++){
                ps.setObject(i+1,obj[i]);
            }
            //执行PrepareStatement对象的查询，并将查询结果返回给数据表对象resultSet
            /*
            ResultSet executeQuery() throws SQLException
            执行此 PreparedStatement对象中的SQL查询，并返回查询 PreparedStatement的 ResultSet对象。

            ResultSet对象保持一个光标指向其当前的数据行。
            最初，光标位于第一行之前。
            next方法将光标移动到下一行，
            并且由于在ResultSet对象中没有更多行时返回false ，因此可以在while循环中使用循环来遍历结果集。
             */
            resultSet = ps.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    /*
        执行sql语句
     */
    public boolean exeute(String sql){
        if(getCon() == null){
            return false;
        }
        try{
            //Statement createStatement() throws SQLException
            // 创建一个Statement对象，用于将SQL语句发送到数据库
            // 没有参数的SQL语句通常使用Statement对象执行。
            Statement statement = con.createStatement();
            //boolean execute(String sql)
            //执行给定的SQL语句，这可能会返回多个结果
            statement.execute(sql);
            //释放资源
            statement.close();
            return true;
        }catch(SQLException e){
            //e.printStackTrace();
            return false;
        }
    }

    //进行数据库连接,返回Connection对象
    private Connection getCon(){
        try{
            if(con == null || con.isClosed()){
                //public static 类<?> forName(String className) throws ClassNotFoundException
                //返回与给定字符串名称的类或接口相关联的类对象。
                //调用此方法相当于：Class.forName(className, true, currentLoader)
                //其中currentLoader表示当前类的定义类加载器。
                Class.forName(AppConstants.JDBC_DRIVER);

                /*
                public static Connection getConnection(String url,String user,String password)
                尝试建立与给定数据库URL的连接。 DriverManager尝试从一组已注册的JDBC驱动程序中选择适当的驱动程序
                参数
                url - 形式为 jdbc:subprotocol:subname的数据库网址
                user - 正在连接的数据库用户
                password - 用户密码
                结果 - 与URL的连接
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
       依次释放资源
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
