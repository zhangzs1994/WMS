package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/5/23.
 */
public class LoginInfo {


    /**
     * data : [{"superior":null,"flag":null,"mail":"707821289@qq.com","phone":"18672346113","sex":null,"name":"","userpwd":"123456","id":4,"age":null,"username":"ceshi","status":1}]
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
         * superior : null
         * flag : null
         * mail : 707821289@qq.com
         * phone : 18672346113
         * sex : null
         * name :
         * userpwd : 123456
         * id : 4
         * age : null
         * username : ceshi
         * status : 1
         */

        private String superior;
        private String flag;
        private String mail;
        private String phone;
        private String sex;
        private String name;
        private String userpwd;
        private int id;
        private int age;
        private String username;
        private String status;

        public String getSuperior() {
            return superior;
        }

        public void setSuperior(String superior) {
            this.superior = superior;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserpwd() {
            return userpwd;
        }

        public void setUserpwd(String userpwd) {
            this.userpwd = userpwd;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
