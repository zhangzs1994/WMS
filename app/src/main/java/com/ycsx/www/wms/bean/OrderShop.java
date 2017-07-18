package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/7/4.
 */
public class OrderShop {

    /**
     * data : [{"uid":3,"pid":6,"price":5,"pname":"百事可乐","num":2},{"uid":3,"pid":1,"price":7.5,"pname":"可口可乐","num":3},{"uid":3,"pid":6,"price":10,"pname":"百事可乐","num":4}]
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
         * uid : 3
         * pid : 6
         * price : 5
         * pname : 百事可乐
         * num : 2
         */

        private int uid;
        private int pid;
        private double price;
        private String pname;
        private int num;
        private String pictureUrl;
        private double iocost;
        private String describee;
        private String nondefectiveNum;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public double getIocost() {
            return iocost;
        }

        public void setIocost(double iocost) {
            this.iocost = iocost;
        }

        public String getDescribee() {
            return describee;
        }

        public void setDescribee(String describee) {
            this.describee = describee;
        }

        public String getNondefectiveNum() {
            return nondefectiveNum;
        }

        public void setNondefectiveNum(String nondefectiveNum) {
            this.nondefectiveNum = nondefectiveNum;
        }
    }
}
