package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/16.
 */
public class OutStockInfo {

    /**
     * data : [{"freightamount":2,"iocost":125,"pid":9,"oid":213684,"rid":6,"qualityTime":null,"spec":"重量","transactor":null,"describ":"有点甜！！！","manufactureTime":"2017-01-28 15:38:40","price":"2.50","inventime":"2017-07-08 08:18:04","name":"农夫山泉","category":2,"value":"饮料类"}]
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
         * freightamount : 2
         * iocost : 125
         * pid : 9
         * oid : 213684
         * rid : 6
         * qualityTime : null
         * spec : 重量
         * transactor : null
         * describ : 有点甜！！！
         * manufactureTime : 2017-01-28 15:38:40
         * price : 2.50
         * inventime : 2017-07-08 08:18:04
         * name : 农夫山泉
         * category : 2
         * value : 饮料类
         * pictureUrl : null
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
        private String price;
        private String inventime;
        private String name;
        private int category;
        private String value;
        private String pictureUrl;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }
    }
}
