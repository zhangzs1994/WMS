package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/17.
 */
public class OrderInfo {

    /**
     * data : [{"ostatus":0,"classify":1,"shipper":0,"ouaddress":"dsfd","uname":"fangyang","usname":null,"criteria":null,"datechanged":null,"oid":314886,"usid":0,"uid":2,"receiving":"虞姬","octime":"2017-06-29 14:30:53","ocost":1242.3,"contact":"18542654212","shippername":null,"dvalue":"待审核","value":"销售订单","expressnumber":null}]
     * page : {"currentPage":16,"pageRecords":1,"totalPages":24,"totalRecords":24,"startRecord":15,"nextPage":17,"previousPage":15,"hasNextPage":true,"hasPreviousPage":true}
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
         * currentPage : 16
         * pageRecords : 1
         * totalPages : 24
         * totalRecords : 24
         * startRecord : 15
         * nextPage : 17
         * previousPage : 15
         * hasNextPage : true
         * hasPreviousPage : true
         */

        private int currentPage;
        private int pageRecords;
        private int totalPages;
        private int totalRecords;
        private int startRecord;
        private int nextPage;
        private int previousPage;
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

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getPreviousPage() {
            return previousPage;
        }

        public void setPreviousPage(int previousPage) {
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
         * ostatus : 0
         * classify : 1
         * shipper : 0
         * ouaddress : dsfd
         * uname : fangyang
         * usname : null
         * criteria : null
         * datechanged : null
         * oid : 314886
         * usid : 0
         * uid : 2
         * receiving : 虞姬
         * octime : 2017-06-29 14:30:53
         * ocost : 1242.3
         * contact : 18542654212
         * shippername : null
         * dvalue : 待审核
         * value : 销售订单
         * expressnumber : null
         */

        private int ostatus;
        private int classify;
        private int shipper;
        private String ouaddress;
        private String uname;
        private String usname;
        private String criteria;
        private String datechanged;
        private int oid;
        private int usid;
        private int uid;
        private String receiving;
        private String octime;
        private double ocost;
        private String contact;
        private String shippername;
        private String dvalue;
        private String value;
        private String expressnumber;

        public int getOstatus() {
            return ostatus;
        }

        public void setOstatus(int ostatus) {
            this.ostatus = ostatus;
        }

        public int getClassify() {
            return classify;
        }

        public void setClassify(int classify) {
            this.classify = classify;
        }

        public int getShipper() {
            return shipper;
        }

        public void setShipper(int shipper) {
            this.shipper = shipper;
        }

        public String getOuaddress() {
            return ouaddress;
        }

        public void setOuaddress(String ouaddress) {
            this.ouaddress = ouaddress;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getUsname() {
            return usname;
        }

        public void setUsname(String usname) {
            this.usname = usname;
        }

        public String getCriteria() {
            return criteria;
        }

        public void setCriteria(String criteria) {
            this.criteria = criteria;
        }

        public String getDatechanged() {
            return datechanged;
        }

        public void setDatechanged(String datechanged) {
            this.datechanged = datechanged;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public int getUsid() {
            return usid;
        }

        public void setUsid(int usid) {
            this.usid = usid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getShippername() {
            return shippername;
        }

        public void setShippername(String shippername) {
            this.shippername = shippername;
        }

        public String getDvalue() {
            return dvalue;
        }

        public void setDvalue(String dvalue) {
            this.dvalue = dvalue;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getExpressnumber() {
            return expressnumber;
        }

        public void setExpressnumber(String expressnumber) {
            this.expressnumber = expressnumber;
        }
    }
}
