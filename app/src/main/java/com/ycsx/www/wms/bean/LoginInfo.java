package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/5/23.
 */
public class LoginInfo {

    /**
     * data : [{"sexValue":null,"subordinateValue":"无下级","flag":4,"mail":"","subordinate":"2","statusValue":"在职","sex":"0","flagValue":"员工","userpwd":"123456","superior":"zzs","phone":"","name":"abc","id":40,"age":"","username":"abc","status":1}]
     * page : null
     * status : 10200
     * message : 请求操作成功
     */

    private Object page;
    private String status;
    private String message;
    private List<DataBean> data;

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sexValue : null
         * subordinateValue : 无下级
         * flag : 4
         * mail :
         * subordinate : 2
         * statusValue : 在职
         * sex : 0
         * flagValue : 员工
         * userpwd : 123456
         * superior : zzs
         * phone :
         * name : abc
         * id : 40
         * age :
         * username : abc
         * status : 1
         * menuNode : 1
         */

        private String sexValue;
        private String subordinateValue;
        private int flag;
        private String mail;
        private String subordinate;
        private String statusValue;
        private String sex;
        private String flagValue;
        private String userpwd;
        private String superior;
        private String phone;
        private String name;
        private int id;
        private String age;
        private String username;
        private String menuNode;
        private int status;

        public String getSexValue() {
            return sexValue;
        }

        public void setSexValue(String sexValue) {
            this.sexValue = sexValue;
        }

        public String getSubordinateValue() {
            return subordinateValue;
        }

        public void setSubordinateValue(String subordinateValue) {
            this.subordinateValue = subordinateValue;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getSubordinate() {
            return subordinate;
        }

        public void setSubordinate(String subordinate) {
            this.subordinate = subordinate;
        }

        public String getStatusValue() {
            return statusValue;
        }

        public void setStatusValue(String statusValue) {
            this.statusValue = statusValue;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getFlagValue() {
            return flagValue;
        }

        public void setFlagValue(String flagValue) {
            this.flagValue = flagValue;
        }

        public String getUserpwd() {
            return userpwd;
        }

        public void setUserpwd(String userpwd) {
            this.userpwd = userpwd;
        }

        public String getSuperior() {
            return superior;
        }

        public void setSuperior(String superior) {
            this.superior = superior;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMenuNode() {
            return menuNode;
        }

        public void setMenuNode(String menuNode) {
            this.menuNode = menuNode;
        }
    }
}
