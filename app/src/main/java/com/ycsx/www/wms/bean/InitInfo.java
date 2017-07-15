package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/7/14.
 */
public class InitInfo {

    /**
     * data : [{"serverIP":"192.168.10.88","id":1,"imgUplodUrl":"\\imges\\goods","AppAdvert":"http://img03.taobaocdn.com/bao/uploaded/i3/TB1GDTKLXXXXXcwXXXXXXXXXXXX_!!0-item_pic.jpg,http://img01.taobaocdn.com/bao/uploaded/i1/TB1T7c8OpXXXXaLXpXXXXXXXXXX_!!0-item_pic.jpg","version":1}]
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
         * serverIP : 192.168.10.88
         * id : 1
         * imgUplodUrl : \imges\goods
         * AppAdvert : http://img03.taobaocdn.com/bao/uploaded/i3/TB1GDTKLXXXXXcwXXXXXXXXXXXX_!!0-item_pic.jpg,http://img01.taobaocdn.com/bao/uploaded/i1/TB1T7c8OpXXXXaLXpXXXXXXXXXX_!!0-item_pic.jpg
         * version : 1
         */

        private String serverIP;
        private int id;
        private String imgUplodUrl;
        private String AppAdvert;
        private int version;

        public String getServerIP() {
            return serverIP;
        }

        public void setServerIP(String serverIP) {
            this.serverIP = serverIP;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUplodUrl() {
            return imgUplodUrl;
        }

        public void setImgUplodUrl(String imgUplodUrl) {
            this.imgUplodUrl = imgUplodUrl;
        }

        public String getAppAdvert() {
            return AppAdvert;
        }

        public void setAppAdvert(String AppAdvert) {
            this.AppAdvert = AppAdvert;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}

