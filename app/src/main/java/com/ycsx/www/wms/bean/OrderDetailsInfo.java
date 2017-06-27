package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/19.
 */
public class OrderDetailsInfo {

    /**
     * data : [{"ostatus":1,"freightamount":5,"ouaddress":"汉阳","iocost":12.5,"ocost":4214.3,"price":2.5,"inventime":"2017-06-04 15:32:16","name":"脉动","oid":314882},{"ostatus":1,"freightamount":9,"ouaddress":"汉阳","iocost":463.2,"ocost":4214.3,"price":2.5,"inventime":"2017-06-05 17:23:14","name":"可口可乐","oid":314882}]
     * page : null
     * status : 10200
     * message : 请求操作成功
     */

    private String page;
    private String status;
    private String message;
    private List<DataBean> data;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
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
         * ostatus : 1
         * freightamount : 5
         * ouaddress : 汉阳
         * iocost : 12.5
         * ocost : 4214.3
         * price : 2.5
         * inventime : 2017-06-04 15:32:16
         * name : 脉动
         * oid : 314882
         */

        private int ostatus;
        private int freightamount;
        private String ouaddress;
        private double iocost;
        private double ocost;
        private double price;
        private String inventime;
        private String name;
        private int oid;

        public int getOstatus() {
            return ostatus;
        }

        public void setOstatus(int ostatus) {
            this.ostatus = ostatus;
        }

        public int getFreightamount() {
            return freightamount;
        }

        public void setFreightamount(int freightamount) {
            this.freightamount = freightamount;
        }

        public String getOuaddress() {
            return ouaddress;
        }

        public void setOuaddress(String ouaddress) {
            this.ouaddress = ouaddress;
        }

        public double getIocost() {
            return iocost;
        }

        public void setIocost(double iocost) {
            this.iocost = iocost;
        }

        public double getOcost() {
            return ocost;
        }

        public void setOcost(double ocost) {
            this.ocost = ocost;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getInventime() {
            return inventime;
        }

        public void setInventime(String inventime) {
            this.inventime = inventime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }
    }
}
