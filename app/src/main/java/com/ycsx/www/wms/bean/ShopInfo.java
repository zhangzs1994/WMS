package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/13.
 */
public class ShopInfo {

    /**
     * data : [{"totalPrice":100,"inventoryMinnum":0,"qualitytimeNum":0,"qualityTime":180,"spec":"重量","transactor":"李四","instockTime":"2017-05-28 15:38:40","describ":"有点甜！！！","inventoryMaxnum":0,"price":2.5,"acceptedNum":0,"id":14,"outstockNum":0,"stock":15,"goodsNum":null,"outstockTime":null,"inventoryStatus":"库存预警未开启","thePool":"主库","measurementUnit":"箱","barCode":null,"qualitytimeStatus":"保质期预警未开启","manufactureTime":"2017-01-28 15:38:40","goodsStatus":"已入库","name":"奥利奥2","instockNum":15,"category":"饼干类","retailPrice":1,"tradePrice":1,"acceptedGoods":"待检品"},{"totalPrice":100,"inventoryMinnum":0,"qualitytimeNum":0,"qualityTime":180,"spec":"重量","transactor":"李四","instockTime":"2017-06-09 15:38:40","describ":"有点甜！！！","inventoryMaxnum":0,"price":2.5,"acceptedNum":0,"id":15,"outstockNum":0,"stock":15,"goodsNum":null,"outstockTime":null,"inventoryStatus":"库存预警未开启","thePool":"主库","measurementUnit":"箱","barCode":null,"qualitytimeStatus":"保质期预警未开启","manufactureTime":"2017-01-28 15:38:40","goodsStatus":"已入库","name":"脉动3","instockNum":15,"category":"未分类","retailPrice":1,"tradePrice":1,"acceptedGoods":"待检品"}]
     * page : {"currentPage":4,"pageRecords":2,"totalPages":9,"totalRecords":17,"startRecord":6,"nextPage":null,"previousPage":null,"hasNextPage":false,"hasPreviousPage":false}
     * status : 10200
     * message : 请求操作成功
     */

    private PageBean page;
    private String status;
    private String message;
    private List<DataBean> data;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
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

    public static class PageBean {
        /**
         * currentPage : 4
         * pageRecords : 2
         * totalPages : 9
         * totalRecords : 17
         * startRecord : 6
         * nextPage : null
         * previousPage : null
         * hasNextPage : false
         * hasPreviousPage : false
         */

        private int currentPage;
        private int pageRecords;
        private int totalPages;
        private int totalRecords;
        private int startRecord;
        private String nextPage;
        private String previousPage;
        private boolean hasNextPage;
        private boolean hasPreviousPage;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageRecords() {
            return pageRecords;
        }

        public void setPageRecords(int pageRecords) {
            this.pageRecords = pageRecords;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public int getStartRecord() {
            return startRecord;
        }

        public void setStartRecord(int startRecord) {
            this.startRecord = startRecord;
        }

        public String getNextPage() {
            return nextPage;
        }

        public void setNextPage(String nextPage) {
            this.nextPage = nextPage;
        }

        public String getPreviousPage() {
            return previousPage;
        }

        public void setPreviousPage(String previousPage) {
            this.previousPage = previousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }
    }

    public static class DataBean {
        /**
         * totalPrice : 100.0
         * inventoryMinnum : 0
         * qualitytimeNum : 0
         * qualityTime : 180
         * spec : 重量
         * transactor : 李四
         * instockTime : 2017-05-28 15:38:40
         * describ : 有点甜！！！
         * inventoryMaxnum : 0
         * price : 2.5
         * acceptedNum : 0
         * id : 14
         * outstockNum : 0
         * stock : 15
         * goodsNum : null
         * outstockTime : null
         * inventoryStatus : 库存预警未开启
         * thePool : 主库
         * measurementUnit : 箱
         * barCode : null
         * qualitytimeStatus : 保质期预警未开启
         * manufactureTime : 2017-01-28 15:38:40
         * goodsStatus : 已入库
         * name : 奥利奥2
         * instockNum : 15
         * category : 饼干类
         * retailPrice : 1.0
         * tradePrice : 1.0
         * acceptedGoods : 待检品
         */

        private double totalPrice;
        private int inventoryMinnum;
        private int qualitytimeNum;
        private int qualityTime;
        private String spec;
        private String transactor;
        private String instockTime;
        private String describ;
        private int inventoryMaxnum;
        private double price;
        private int acceptedNum;
        private int id;
        private int outstockNum;
        private int stock;
        private String goodsNum;
        private String outstockTime;
        private String inventoryStatus;
        private String thePool;
        private String measurementUnit;
        private String barCode;
        private String qualitytimeStatus;
        private String manufactureTime;
        private String goodsStatus;
        private String name;
        private int instockNum;
        private String category;
        private double retailPrice;
        private double tradePrice;
        private String acceptedGoods;

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getInventoryMinnum() {
            return inventoryMinnum;
        }

        public void setInventoryMinnum(int inventoryMinnum) {
            this.inventoryMinnum = inventoryMinnum;
        }

        public int getQualitytimeNum() {
            return qualitytimeNum;
        }

        public void setQualitytimeNum(int qualitytimeNum) {
            this.qualitytimeNum = qualitytimeNum;
        }

        public int getQualityTime() {
            return qualityTime;
        }

        public void setQualityTime(int qualityTime) {
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
            return describ;
        }

        public void setDescrib(String describ) {
            this.describ = describ;
        }

        public int getInventoryMaxnum() {
            return inventoryMaxnum;
        }

        public void setInventoryMaxnum(int inventoryMaxnum) {
            this.inventoryMaxnum = inventoryMaxnum;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getAcceptedNum() {
            return acceptedNum;
        }

        public void setAcceptedNum(int acceptedNum) {
            this.acceptedNum = acceptedNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOutstockNum() {
            return outstockNum;
        }

        public void setOutstockNum(int outstockNum) {
            this.outstockNum = outstockNum;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
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

        public int getInstockNum() {
            return instockNum;
        }

        public void setInstockNum(int instockNum) {
            this.instockNum = instockNum;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public double getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(double retailPrice) {
            this.retailPrice = retailPrice;
        }

        public double getTradePrice() {
            return tradePrice;
        }

        public void setTradePrice(double tradePrice) {
            this.tradePrice = tradePrice;
        }

        public String getAcceptedGoods() {
            return acceptedGoods;
        }

        public void setAcceptedGoods(String acceptedGoods) {
            this.acceptedGoods = acceptedGoods;
        }
    }
}
