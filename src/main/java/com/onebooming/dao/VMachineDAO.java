package com.onebooming.dao;
/**
 * VMachine ��ɾ�Ĳ�
 */

import com.onebooming.base.BaseDao;
import com.onebooming.model.VMachine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class VMachineDAO extends BaseDao{
    //9������
    private final int fieldNum = 9;
    //ÿҳ�����ʾ15��
    private final int showNum = 15;

    //����һ����̬���󣨵���ģʽ�淶��
    private static VMachineDAO vmd = null;

    /*
        �������ģʽ������ʽ+���̣߳�
     */
    public static synchronized VMachineDAO getInstance(){
        if(vmd == null){
            vmd = new VMachineDAO();
        }
        return vmd;
    }

    //update
    //�޸�����
    public boolean update(VMachine vm){
        boolean flag = false;
        if(vm == null){
            return flag;
        }
        try{
            //���
            if(queryBySno(vm.getSno()) == 0){
                return flag;
            }
            //update
            //SQl:����������ѧ�Ÿ���������Student��������
            String sql =  "update vms set user=?,monitor=?,cpu=?,memory=?,storage=?,status=? where name=? and sno=?";
            String[] param = {vm.getUser(),vm.getMonitor(),vm.getCpuNum(),vm.getMemory(),
                    vm.getStorage(),vm.getStatus(),vm.getName(),vm.getSno()};
            //ִ�����ݿ������䣬��������Ϊparam
            int rowCount = db.executeUpdate(sql,param);
            //���³ɹ���rowCountΪ1
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
        �������ƺ�Ψһ��ʶ�ţ�ɾ�����ݿ����������Ϣ
     */
    public boolean delete(VMachine vm){
        boolean flag = false;
        if(vm == null){
            return flag;
        }
        //ɾ��vms���ݱ��ж�Ӧ������ѧ�ŵ��������������
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
        �����������Ϣ
     */
    public boolean add(VMachine vm){
        boolean flag = false;
        if(vm == null){
            return false;
        }
        try{
            //check
            //����Ψһ��ʶ�Ų�ѯ�Ƿ����и�����������ݣ�������ݿ����Ѵ��ڸñ�ʶ���������Ϣ���������
            if(queryBySno(vm.getSno()) == 1){
                return flag;
            }
            //insert

            //��Ϊid��prime key�������ڲ�������б���Ҫ��id������ȱʡ
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
    //����������ѯ���ݿ�
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
                //��resultSet��ӵ�list��
                buildList(resultSet,students,i);
                i++;
            }
            if(students.size()>0){
                result = new String[students.size()][fieldNum];
                for(int j = 0; j<students.size();j++){
                    //��list�м�¼��ӵ���ά������
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
                //��resultSet��ӵ�list��
                buildList(resultSet,students,i);
                i++;
            }
            if(students.size()>0){
                result = new String[students.size()][fieldNum];
                for(int j = 0; j<students.size();j++){
                    //��list�м�¼��ӵ���ά������
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

    //��resultSet��ӵ�list��
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

    //��list�м�¼��ӵ���ά������
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
        ����Ψһ��ʶ����ѯ����������д��ڶ�Ӧ��ʶ���ı�����򷵻�1
     */
    private  int queryBySno(String sno) throws SQLException{
        int result = 0;
        if("".equals(sno) || sno == null){
            return result;
        }
        String checkSql = "select * from vms where sno = ?";
        String[] checkParam = { sno };
        //ִ�д� PreparedStatement�����е�SQL��ѯ�������ز�ѯ PreparedStatement�� ResultSet����
        resultSet = db.executeQuery(checkSql,checkParam);
        if(resultSet.next()){
            result = 1;
        }
        return result;
    }
}
