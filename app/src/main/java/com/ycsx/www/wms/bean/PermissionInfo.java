package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/5/28.
 */
public class PermissionInfo {

    /**
     * data : [{"nodeText":"系统管理","flag":2},{"nodeText":"订单管理","flag":2},{"nodeText":"账号管理","flag":2},{"nodeText":"数据统计","flag":2}]
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
         * nodeText : 系统管理
         * flag : 2
         */

        private String nodeText;
        private int flag;

        public String getNodeText() {
            return nodeText;
        }

        public void setNodeText(String nodeText) {
            this.nodeText = nodeText;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
