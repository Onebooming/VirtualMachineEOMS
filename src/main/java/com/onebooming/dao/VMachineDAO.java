package com.onebooming.dao;
/**
 * VMachine 增删改查
 */

import com.onebooming.base.BaseDao;
import com.onebooming.model.VMachine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class VMachineDAO extends BaseDao{
    //9项数据
    private final int fieldNum = 9;
    //每页最多显示15行
    private final int showNum = 15;

    //建立一个静态对象（单例模式规范）
    private static VMachineDAO vmd = null;

    /*
        单例设计模式（饱汉式+多线程）
     */
    public static synchronized VMachineDAO getInstance(){
        if(vmd == null){
            vmd = new VMachineDAO();
        }
        return vmd;
    }

    //update
    //修改数据
    public boolean update(VMachine vm){
        boolean flag = false;
        if(vm == null){
            return flag;
        }
        try{
            //检查
            if(queryBySno(vm.getSno()) == 0){
                return flag;
            }
            //update
            //SQl:根据姓名和学号更新数据中Student数据内容
            String sql =  "update vms set user=?,monitor=?,cpu=?,memory=?,storage=?,status=? where name=? and sno=?";
            String[] param = {vm.getUser(),vm.getMonitor(),vm.getCpuNum(),vm.getMemory(),
                    vm.getStorage(),vm.getStatus(),vm.getName(),vm.getSno()};
            //执行数据库更新语句，更新内容为param
            int rowCount = db.executeUpdate(sql,param);
            //更新成功则rowCount为1
            if(rowCount == 1){
                flag = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return flag;
    }


    //delete
    /*
        根据名称和唯一标识号，删除数据库中虚拟机信息
     */
    public boolean delete(VMachine vm){
        boolean flag = false;
        if(vm == null){
            return flag;
        }
        //删除vms数据表中对应姓名和学号的虚拟机对象数据
        String sql = "delete from vms where name=? and sno=?";
        String[] param = {vm.getName(),vm.getSno()};
        int rowCount = db.executeUpdate(sql,param);
        if(rowCount == 1){
            flag = true;
        }
        destroy();
        return flag;
    }

    //add
    /*
        增加虚拟机信息
     */
    public boolean add(VMachine vm){
        boolean flag = false;
        if(vm == null){
            return false;
        }
        try{
            //check
            //根据唯一标识号查询是否已有该虚拟机的数据，如果数据库中已存在该标识号虚拟机信息，则不能添加
            if(queryBySno(vm.getSno()) == 1){
                return flag;
            }
            //insert

            //因为id是prime key，所以在插入语句中必须要有id，不能缺省
            String sql = "insert into vms(id,name,sno,user,monitor,cpu,memory,storage,status) values(?,?,?,?,?,?,?,?,?) ";
            String[] param = {String.valueOf(vm.getId()),vm.getName(),vm.getSno(),vm.getUser(),vm.getMonitor(),vm.getCpuNum(),
                    vm.getMemory(),vm.getStorage(),vm.getStatus()};
            if(db.executeUpdate(sql,param) == 1) {
                flag = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return flag;
    }

    //query by name
    //根据姓名查询数据库
    public String[][] queryByName(String name){
        String[][] result = null;
        if(name.length() < 0){
            return result;
        }
        List<VMachine> students = new ArrayList<VMachine>();
        int i = 0;
        String sql = "select * from vms where name like ?";
        String[] param = {"%" + name + "%"};
        resultSet = db.executeQuery(sql,param);
        try{
            while(resultSet.next()){
                //将resultSet添加到list中
                buildList(resultSet,students,i);
                i++;
            }
            if(students.size()>0){
                result = new String[students.size()][fieldNum];
                for(int j = 0; j<students.size();j++){
                    //将list中记录添加到二维数组中
                    buildResult(result,students,j);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }

        return result;
    }

    //query
    public String[][] list(int pageNum){
        String[][] result = null;
        if(pageNum < 1){
            return result;
        }
        List<VMachine> students = new ArrayList<VMachine>();
        int i = 0;
        int beginNum = (pageNum -1)*showNum;
        String sql = "select * from vms limit ?,?";
        Integer[] param = {beginNum,showNum};
        resultSet = db.executeQuery(sql,param);
        try{
            while (resultSet.next()){
                //将resultSet添加到list中
                buildList(resultSet,students,i);
                i++;
            }
            if(students.size()>0){
                result = new String[students.size()][fieldNum];
                for(int j = 0; j<students.size();j++){
                    //将list中记录添加到二维数组中
                    buildResult(result,students,j);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }

        return result;
    }

    //将resultSet添加到list中
    private void buildList(ResultSet resultSet,List<VMachine> list, int i) throws SQLException{
        VMachine vm = new VMachine();
        vm.setId(i+1);
        vm.setName(resultSet.getString("name"));
        vm.setSno(resultSet.getString("sno"));
        vm.setUser(resultSet.getString("user"));
        vm.setMonitor(resultSet.getString("monitor"));
        vm.setCpuNum(resultSet.getString("cpu"));
        vm.setMemory(resultSet.getString("memory"));
        vm.setStorage(resultSet.getString("storage"));
        vm.setStatus(resultSet.getString("status"));
        list.add(vm);
    }

    //将list中记录添加到二维数组中
    private void buildResult(String[][] result,List<VMachine> vms,int j){
        VMachine vm = vms.get(j);
        result[j][0] = String.valueOf(vm.getId());
        result[j][1] = vm.getName();
        result[j][2] = vm.getSno();
        result[j][3] = vm.getUser();
        result[j][4] = vm.getMonitor();
        result[j][5] = vm.getCpuNum();
        result[j][6] = vm.getMemory();
        result[j][7] = vm.getStorage();
        result[j][8] = vm.getStatus();
    }

    //query by sno
    /*
        根据唯一标识符查询：如果数据中存在对应标识符的表对象，则返回1
     */
    private  int queryBySno(String sno) throws SQLException{
        int result = 0;
        if("".equals(sno) || sno == null){
            return result;
        }
        String checkSql = "select * from vms where sno = ?";
        String[] checkParam = { sno };
        //执行此 PreparedStatement对象中的SQL查询，并返回查询 PreparedStatement的 ResultSet对象
        resultSet = db.executeQuery(checkSql,checkParam);
        if(resultSet.next()){
            result = 1;
        }
        return result;
    }
}
