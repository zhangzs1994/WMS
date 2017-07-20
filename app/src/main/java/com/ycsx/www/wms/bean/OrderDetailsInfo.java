package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/19.
 */
public class OrderDetailsInfo {

    /**
     * data : [{"ostatus":0,"qvalue":"主库","classify":1,"freightamount":5,"ouaddress":"洪山广场","iocost":6.25,"wvalue":"<空>","pictureUrl":"http://192.168.10.88:8080/images/goods/tomcat.png","evalue":"销售订单","oid":314924,"receiving":"张志胜","octime":"2017-07-18 14:32:55","ocost":6.25,"price":1.25,"contact":"15827011725","name":"可口可乐","dvalue":"待审核","describee":"","goodsNum":"1232312"}]
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
         * ostatus : 0
         * qvalue : 主库
         * classify : 1
         * freightamount : 5
         * ouaddress : 洪山广场
         * iocost : 6.25
         * wvalue : <空>
         * pictureUrl : http://192.168.10.88:8080/images/goods/tomcat.png
         * evalue : 销售订单
         * oid : 314924
         * receiving : 张志胜
         * octime : 2017-07-18 14:32:55
         * ocost : 6.25
         * price : 1.25
         * contact : 15827011725
         * name : 可口可乐
         * dvalue : 待审核
         * describee :
         * goodsNum : 1232312
         */

        private int ostatus;
        private String qvalue;
        private int classify;
        private int freightamount;
        private String ouaddress;
        private double iocost;
        private String wvalue;
        private String pictureUrl;
        private String evalue;
        private int oid;
        private String receiving;
        private String octime;
        private double ocost;
        private double price;
        private String contact;
        private String name;
        private String dvalue;
        private String describee;
        private String goodsNum;
        private String uname;
        private String expressnumber;
        private String criteria;

        public int getOstatus() {
            return ostatus;
        }

        public void setOstatus(int ostatus) {
            this.ostatus = ostatus;
        }

        public String getQvalue() {
            return qvalue;
        }

        public void setQvalue(String qvalue) {
            this.qvalue = qvalue;
        }

        public int getClassify() {
            return classify;
        }

        public void setClassify(int classify) {
            this.classify = classify;
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

        public String getWvalue() {
            return wvalue;
        }

        public void setWvalue(String wvalue) {
            this.wvalue = wvalue;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public String getEvalue() {
            return evalue;
        }

        public void setEvalue(String evalue) {
            this.evalue = evalue;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getReceiving() {
            return receiving;
        }

        public void setReceiving(String receiving) {
            this.receiving = receiving;
        }

        public String getOctime() {
            return octime;
        }

        public void setOctime(String octime) {
            this.octime = octime;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDvalue() {
            return dvalue;
        }

        public void setDvalue(String dvalue) {
            this.dvalue = dvalue;
        }

        public String getDescribee() {
            return describee;
        }

        public void setDescribee(String describee) {
            this.describee = describee;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getExpressnumber() {
            return expressnumber;
        }

        public void setExpressnumber(String expressnumber) {
            this.expressnumber = expressnumber;
        }

        public String getCriteria() {
            return criteria;
        }

        public void setCriteria(String criteria) {
            this.criteria = criteria;
        }
    }
}
