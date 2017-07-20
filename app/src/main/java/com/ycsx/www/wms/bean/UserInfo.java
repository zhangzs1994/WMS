package com.ycsx.www.wms.bean;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public class UserInfo {
    private String userName; //用户名
    private String userPassword; //用户密码
    private int flag; //用户等级
    private String flagValue; //用户等级
    private String name;  //姓名
    private String age;   //年龄
    private String phone; //手机
    private String email;  //邮箱
    private String sex;   //性别
    private String sexValue;   //性别
    private int status; //用户状态
    private String superior; //上级
    private String subordinate; //是否有下属员工，1：有；2：没有
    private int id;  //用户表ID

    public UserInfo() {
    }

    public UserInfo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserInfo(int id, String userPassword, String userName) {
        this.id = id;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    public UserInfo(String userName, String userPassword, int flag, String name, String age,
                    String phone, String email, String sex, int status, int id, String superior,
                    String subordinate, String flagValue) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.flag = flag;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.status = status;
        this.superior = superior;
        this.subordinate = subordinate;
        this.id = id;
        this.flagValue = flagValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(String subordinate) {
        this.subordinate = subordinate;
    }

    public String getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(String flagValue) {
        this.flagValue = flagValue;
    }

    public String getSexValue() {
        return sexValue;
    }

    public void setSexValue(String sexValue) {
        this.sexValue = sexValue;
    }
}
