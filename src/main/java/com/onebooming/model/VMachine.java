package com.onebooming.model;
/**
 * 虚拟机类
 */
public class VMachine {
    private int id;
    //虚拟机名
    private String name;
    //虚拟机唯一标识序号
    private String sno;
    //虚拟机使用者
    private String user;
    //虚拟机管理者
    private String monitor;
    //虚拟机CPU数
    private String cpuNum;
    //虚拟内存大小
    private String memory;
    //虚拟机存储容量
    private String storage;
    //虚拟机状态：正常、警告、错误、关机
    private String status;

    public VMachine(){

    }

    public VMachine(int id, String name, String sno,
                    String user, String monitor, String cpuNum,
                    String memory, String storage, String statues) {
        this.id = id;
        this.name = name;
        this.sno = sno;
        this.user = user;
        this.monitor = monitor;
        this.cpuNum = cpuNum;
        this.memory = memory;
        this.storage = storage;
        this.status = statues;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(String cpuNum) {
        this.cpuNum = cpuNum;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
