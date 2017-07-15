package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/19.
 */
public class OrderDetailsInfo {

    /**
     * data : [{"ostatus":3,"receiving":"气氛","freightamount":2,"ouaddress":"中建广场","iocost":125,"ocost":2124.36,"price":"2.50","contact":"18542654212","inventime":"2017-07-08 08:18:04","name":"农夫山泉","oid":213684}]
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
         * ostatus : 3
         * receiving : 气氛
         * freightamount : 2
         * ouaddress : 中建广场
         * iocost : 125
         * ocost : 2124.36
         * price : 2.50
         * contact : 18542654212
         * inventime : 2017-07-08 08:18:04
         * name : 农夫山泉
         * oid : 213684
         * pictureUrl : null
         */

        private int ostatus;
        private String receiving;
        private int freightamount;
        private String ouaddress;
        private double iocost;
        private double ocost;
        private double price;
        private String contact;
        private String inventime;
        private String name;
        private int oid;
        private String pictureUrl;

        public int getOstatus() {
            return ostatus;
        }

        public void setOstatus(int ostatus) {
            this.ostatus = ostatus;
        }

        public String getReceiving() {
            return receiving;
        }

        public void setReceiving(String receiving) {
            this.receiving = receiving;
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

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
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

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }
    }
}
