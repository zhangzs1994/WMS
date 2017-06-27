package com.ycsx.www.wms.bean;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public class UserInfo {
    private String userName; //用户名
    private String userPassword; //用户密码
    private String flag; //用户等级
    private String name;  //姓名
    private int age;   //年龄
    private String phone; //手机
    private String email;  //邮箱
    private String sex;   //性别
    private String status; //用户状态
    private String superior; //上级
    private int id;  //用户表ID

    public UserInfo() {
    }

    public UserInfo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserInfo(int id, String userPassword) {
        this.id = id;
        this.userPassword = userPassword;
    }

    public UserInfo(String userName, String userPassword, String flag, String name, int age,
                    String phone, String email, String sex, String status, int id, String superior) {
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
        this.id = id;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
