package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/7/7.
 */
public class UserRoles {

    /**
     * data : [{"valid":1,"menuNode":"102,203,402,501,502,503,601,801","flag":1},{"valid":1,"menuNode":"101,102,103,201,202,203,301,302,303,401,402,501,502,503,601,701,801","flag":2},{"valid":1,"menuNode":"102,103,201,202,203,301,402,501,502,503,10002,20002,30001","flag":3},{"valid":1,"menuNode":"102,103,201,202,203,402,501,502,503,30001","flag":4}]
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
         * valid : 1
         * menuNode : 102,203,402,501,502,503,601,801
         * flag : 1
         */

        private int valid;
        private String menuNode;
        private int flag;

        public int getValid() {
            return valid;
        }

        public void setValid(int valid) {
            this.valid = valid;
        }

        public String getMenuNode() {
            return menuNode;
        }

        public void setMenuNode(String menuNode) {
            this.menuNode = menuNode;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
