package com.ycsx.www.wms.bean;

/**
 * Created by ZZS_PC on 2017/6/5.
 */
public class Common {

    /**
     * data : null
     * page : null
     * status : 10200
     * message : 请求操作成功
     */

    private Object data;
    private Object page;
    private String status;
    private String message;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

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
}
