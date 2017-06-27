package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/16.
 */
public class CategoryInfo {

    /**
     * data : [{"value":"未分组","colName":"flag","code":-1},{"value":"超级管理员","colName":"flag","code":0},{"value":"管理员","colName":"flag","code":1},{"value":"经理","colName":"flag","code":2},{"value":"主管","colName":"flag","code":3},{"value":"员工","colName":"flag","code":4},{"value":"仓管员","colName":"flag","code":4}]
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
         * value : 未分组
         * colName : flag
         * code : -1
         */

        private String value;
        private String colName;
        private int code;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColName() {
            return colName;
        }

        public void setColName(String colName) {
            this.colName = colName;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
