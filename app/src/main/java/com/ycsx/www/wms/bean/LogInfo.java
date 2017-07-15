package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/7/12.
 */
public class LogInfo {

    /**
     * data : [{"id":28,"datetime":"2017-07-05 15:37:16","operator":"admin","event":"save","content":"123"},{"id":29,"datetime":"2017-07-05 15:37:16","operator":"admin","event":"save","content":"456"},{"id":30,"datetime":"2017-07-05 15:37:16","operator":"admin","event":"save","content":"789"},{"id":31,"datetime":"2017-07-05 15:37:16","operator":"admin","event":"save","content":"111"},{"id":1,"datetime":"2017-07-05","operator":"zhaofan","event":"权限","content":"测试"}]
     * page : {"currentPage":1,"pageRecords":5,"totalPages":2,"totalRecords":6,"startRecord":0,"nextPage":2,"previousPage":1,"hasNextPage":true,"hasPreviousPage":false}
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
         * currentPage : 1
         * pageRecords : 5
         * totalPages : 2
         * totalRecords : 6
         * startRecord : 0
         * nextPage : 2
         * previousPage : 1
         * hasNextPage : true
         * hasPreviousPage : false
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
         * id : 28
         * datetime : 2017-07-05 15:37:16
         * operator : admin
         * event : save
         * content : 123
         */

        private int id;
        private String datetime;
        private String operator;
        private String event;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
