package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/13.
 */
public class ShopInfo {

    /**
     * data : [{"totalPrice":"2.50","inventoryMinnum":"10","qualitytimeNum":"50","qualityTime":"180","spec":"重量","transactor":"李四","instockTime":"2017-06-27 15:38:40","describ":"有点甜！！！","inventoryMaxnum":"50","price":"2.50","acceptedNum":"20","id":22,"outstockNum":"0","stock":"100","goodsNum":"11111","outstockTime":null,"inventoryStatus":"库存预警开启","pictureUrl":null,"thePool":"主库","measurementUnit":"<空>","nondefectiveNum":2,"barCode":"22222","qualitytimeStatus":"保质期预警未开启","manufactureTime":"2017-01-28 15:38:40","goodsStatus":"已入库","name":"黄鹤楼","instockNum":"100","category":"未分类","retailPrice":"2.50","tradePrice":"2.50"}]
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
         * totalPrice : 2.50
         * inventoryMinnum : 10
         * qualitytimeNum : 50
         * qualityTime : 180
         * spec : 重量
         * transactor : 李四
         * instockTime : 2017-06-27 15:38:40
         * describ : 有点甜！！！
         * inventoryMaxnum : 50
         * price : 2.50
         * acceptedNum : 20
         * id : 22
         * outstockNum : 0
         * stock : 100
         * goodsNum : 11111
         * outstockTime : null
         * inventoryStatus : 库存预警开启
         * pictureUrl : null
         * thePool : 主库
         * measurementUnit : <空>
         * nondefectiveNum : 2
         * barCode : 22222
         * qualitytimeStatus : 保质期预警未开启
         * manufactureTime : 2017-01-28 15:38:40
         * goodsStatus : 已入库
         * name : 黄鹤楼
         * instockNum : 100
         * category : 未分类
         * retailPrice : 2.50
         * tradePrice : 2.50
         * brand :
         */

        private String totalPrice;
        private String inventoryMinnum;
        private String qualitytimeNum;
        private String qualityTime;
        private String spec;
        private String transactor;
        private String instockTime;
        private String describe1;
        private String inventoryMaxnum;
        private String price;
        private String acceptedNum;
        private int id;
        private String outstockNum;
        private String stock;
        private String goodsNum;
        private String outstockTime;
        private String inventoryStatus;
        private String pictureUrl;
        private String thePool;
        private String measurementUnit;
        private int nondefectiveNum;
        private String barCode;
        private String qualitytimeStatus;
        private String manufactureTime;
        private String goodsStatus;
        private String name;
        private String instockNum;
        private String category;
        private String retailPrice;
        private String tradePrice;
        private String brand;

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getInventoryMinnum() {
            return inventoryMinnum;
        }

        public void setInventoryMinnum(String inventoryMinnum) {
            this.inventoryMinnum = inventoryMinnum;
        }

        public String getQualitytimeNum() {
            return qualitytimeNum;
        }

        public void setQualitytimeNum(String qualitytimeNum) {
            this.qualitytimeNum = qualitytimeNum;
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

        public String getInstockTime() {
            return instockTime;
        }

        public void setInstockTime(String instockTime) {
            this.instockTime = instockTime;
        }

        public String getDescrib() {
            return describe1;
        }

        public void setDescrib(String describe1) {
            this.describe1 = describe1;
        }

        public String getInventoryMaxnum() {
            return inventoryMaxnum;
        }

        public void setInventoryMaxnum(String inventoryMaxnum) {
            this.inventoryMaxnum = inventoryMaxnum;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAcceptedNum() {
            return acceptedNum;
        }

        public void setAcceptedNum(String acceptedNum) {
            this.acceptedNum = acceptedNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOutstockNum() {
            return outstockNum;
        }

        public void setOutstockNum(String outstockNum) {
            this.outstockNum = outstockNum;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getOutstockTime() {
            return outstockTime;
        }

        public void setOutstockTime(String outstockTime) {
            this.outstockTime = outstockTime;
        }

        public String getInventoryStatus() {
            return inventoryStatus;
        }

        public void setInventoryStatus(String inventoryStatus) {
            this.inventoryStatus = inventoryStatus;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public String getThePool() {
            return thePool;
        }

        public void setThePool(String thePool) {
            this.thePool = thePool;
        }

        public String getMeasurementUnit() {
            return measurementUnit;
        }

        public void setMeasurementUnit(String measurementUnit) {
            this.measurementUnit = measurementUnit;
        }

        public int getNondefectiveNum() {
            return nondefectiveNum;
        }

        public void setNondefectiveNum(int nondefectiveNum) {
            this.nondefectiveNum = nondefectiveNum;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getQualitytimeStatus() {
            return qualitytimeStatus;
        }

        public void setQualitytimeStatus(String qualitytimeStatus) {
            this.qualitytimeStatus = qualitytimeStatus;
        }

        public String getManufactureTime() {
            return manufactureTime;
        }

        public void setManufactureTime(String manufactureTime) {
            this.manufactureTime = manufactureTime;
        }

        public String getGoodsStatus() {
            return goodsStatus;
        }

        public void setGoodsStatus(String goodsStatus) {
            this.goodsStatus = goodsStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInstockNum() {
            return instockNum;
        }

        public void setInstockNum(String instockNum) {
            this.instockNum = instockNum;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(String retailPrice) {
            this.retailPrice = retailPrice;
        }

        public String getTradePrice() {
            return tradePrice;
        }

        public void setTradePrice(String tradePrice) {
            this.tradePrice = tradePrice;
        }

        public String getDescribe1() {
            return describe1;
        }

        public void setDescribe1(String describe1) {
            this.describe1 = describe1;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }
    }
}
