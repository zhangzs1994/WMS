package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/16.
 */
public class OutStockInfo {

    /**
     * data : [{"freightamount":8,"iocost":125,"pid":6,"oid":3,"rid":5,"qualityTime":"1年","spec":"一打","transactor":"张三","describ":"OKKKKKK","manufactureTime":1485589120000,"price":2.5,"inventime":1495120484000,"name":"百事可乐","category":2,"acceptedGoods":2},{"freightamount":12,"iocost":125,"pid":9,"oid":3,"rid":6,"qualityTime":"2年","spec":"重量","transactor":null,"describ":"有点甜！！！","manufactureTime":1485589120000,"price":2.5,"inventime":1495514690000,"name":"农夫山泉","category":2,"acceptedGoods":3}]
     * page : {"currentPage":1,"pageRecords":2,"totalPages":3,"totalRecords":5,"startRecord":0,"nextPage":2,"previousPage":1,"hasNextPage":true,"hasPreviousPage":false}
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
         * freightamount : 8
         * iocost : 125.0
         * pid : 6
         * oid : 3
         * rid : 5
         * qualityTime : 1年
         * spec : 一打
         * transactor : 张三
         * describ : OKKKKKK
         * manufactureTime : 1485589120000
         * price : 2.5
         * inventime : 1495120484000
         * name : 百事可乐
         * category : 2
         * acceptedGoods : 2
         */

        private int freightamount;
        private double iocost;
        private int pid;
        private int oid;
        private int rid;
        private String qualityTime;
        private String spec;
        private String transactor;
        private String describ;
        private String manufactureTime;
        private double price;
        private String inventime;
        private String name;
        private int category;
        private int acceptedGoods;
        private String instockTime;
        private String outstockTime;
        private String stock;
        private String goodsStatus;

        public int getFreightamount() {
            return freightamount;
        }

        public void setFreightamount(int freightamount) {
            this.freightamount = freightamount;
        }

        public double getIocost() {
            return iocost;
        }

        public void setIocost(double iocost) {
            this.iocost = iocost;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getQualityTime() {
            return qualityTime;
        }

        public void setQualityTime(String qualityTime) {
            this.qualityTime = qualityTime;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getTransactor() {
            return transactor;
        }

        public void setTransactor(String transactor) {
            this.transactor = transactor;
        }

        public String getDescrib() {
            return describ;
        }

        public void setDescrib(String describ) {
            this.describ = describ;
        }

        public String getManufactureTime() {
            return manufactureTime;
        }

        public void setManufactureTime(String manufactureTime) {
            this.manufactureTime = manufactureTime;
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

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public int getAcceptedGoods() {
            return acceptedGoods;
        }

        public void setAcceptedGoods(int acceptedGoods) {
            this.acceptedGoods = acceptedGoods;
        }

        public String getInstockTime() {
            return instockTime;
        }

        public void setInstockTime(String instockTime) {
            this.instockTime = instockTime;
        }

        public String getOutstockTime() {
            return outstockTime;
        }

        public void setOutstockTime(String outstockTime) {
            this.outstockTime = outstockTime;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getGoodsStatus() {
            return goodsStatus;
        }

        public void setGoodsStatus(String goodsStatus) {
            this.goodsStatus = goodsStatus;
        }
    }
}
