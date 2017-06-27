package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/13.
 */
public class ShopInfo {


    /**
     * data : [{"qualityTime":"2年","spec":"重量","transactor":"李四","instockTime":1495957120000,"describ":"有点甜！！！","manufactureTime":1485589120000,"goodsStatus":"已入库","price":2.5,"name":"奥利奥2","id":14,"category":"饼干类","stock":15,"outstockTime":null,"acceptedGoods":"待检品"},{"qualityTime":"2年","spec":"重量","transactor":"李四","instockTime":1496993920000,"describ":"有点甜！！！","manufactureTime":1485589120000,"goodsStatus":"已入库","price":2.5,"name":"脉动3","id":15,"category":"未分类","stock":15,"outstockTime":null,"acceptedGoods":"待检品"}]
     * page : {"currentPage":4,"pageRecords":2,"totalPages":6,"totalRecords":11,"startRecord":6,"nextPage":null,"previousPage":null,"hasNextPage":false,"hasPreviousPage":false}
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
         * totalPages : 6
         * totalRecords : 11
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
        private Object nextPage;
        private Object previousPage;
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

        public Object getNextPage() {
            return nextPage;
        }

        public void setNextPage(Object nextPage) {
            this.nextPage = nextPage;
        }

        public Object getPreviousPage() {
            return previousPage;
        }

        public void setPreviousPage(Object previousPage) {
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
         * qualityTime : 2年
         * spec : 重量
         * transactor : 李四
         * instockTime : 1495957120000
         * describ : 有点甜！！！
         * manufactureTime : 1485589120000
         * goodsStatus : 已入库
         * price : 2.5
         * name : 奥利奥2
         * id : 14
         * category : 饼干类
         * stock : 15
         * outstockTime : null
         * acceptedGoods : 待检品
         */

        private String qualityTime;
        private String spec;
        private String transactor;
        private String instockTime;
        private String describ;
        private String manufactureTime;
        private String goodsStatus;
        private double price;
        private String name;
        private int id;
        private String category;
        private int stock;
        private String outstockTime;
        private String acceptedGoods;

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

        public String getGoodsStatus() {
            return goodsStatus;
        }

        public void setGoodsStatus(String goodsStatus) {
            this.goodsStatus = goodsStatus;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getOutstockTime() {
            return outstockTime;
        }

        public void setOutstockTime(String outstockTime) {
            this.outstockTime = outstockTime;
        }

        public String getAcceptedGoods() {
            return acceptedGoods;
        }

        public void setAcceptedGoods(String acceptedGoods) {
            this.acceptedGoods = acceptedGoods;
        }
    }
}
