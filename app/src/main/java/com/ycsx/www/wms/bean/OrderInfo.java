package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/17.
 */
public class OrderInfo {


    /**
     * data : [{"ostatus":1,"uid":24,"octime":1497407691000,"ouaddress":"苏州","uname":"发的","ocost":3654.36,"criteria":"订单没问题","datechanged":1497494110000,"oid":314876,"usid":11,"expressnumber":123245412333},{"ostatus":1,"uid":9,"octime":1496630153000,"ouaddress":"广西","uname":"爱啥啥","ocost":4836.25,"criteria":"审核通过,无需快递","datechanged":1497494180000,"oid":314877,"usid":12,"expressnumber":null},{"ostatus":1,"uid":12,"octime":1497593751000,"ouaddress":"汉阳","uname":"李增辉","ocost":4214.3,"criteria":"订单正常","datechanged":1497593775000,"oid":314882,"usid":24,"expressnumber":13245115445},{"ostatus":1,"uid":23,"octime":1497665480000,"ouaddress":"拉萨","uname":"卡尔","ocost":1242.23,"criteria":null,"datechanged":1497665500000,"oid":314883,"usid":22,"expressnumber":null},{"ostatus":1,"uid":12,"octime":1497665579000,"ouaddress":"青海","uname":"d速度","ocost":4536.35,"criteria":"订单没问题","datechanged":1497665596000,"oid":314884,"usid":11,"expressnumber":null}]
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
         * ostatus : 1
         * uid : 24
         * octime : 1497407691000
         * ouaddress : 苏州
         * uname : 发的
         * ocost : 3654.36
         * criteria : 订单没问题
         * datechanged : 1497494110000
         * oid : 314876
         * usid : 11
         * expressnumber : 123245412333
         */

        private int ostatus;
        private int uid;
        private String octime;
        private String ouaddress;
        private String uname;
        private double ocost;
        private String criteria;
        private String datechanged;
        private int oid;
        private int usid;
        private String expressnumber;

        public int getOstatus() {
            return ostatus;
        }

        public void setOstatus(int ostatus) {
            this.ostatus = ostatus;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getOctime() {
            return octime;
        }

        public void setOctime(String octime) {
            this.octime = octime;
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

        public double getOcost() {
            return ocost;
        }

        public void setOcost(double ocost) {
            this.ocost = ocost;
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

        public String getExpressnumber() {
            return expressnumber;
        }

        public void setExpressnumber(String expressnumber) {
            this.expressnumber = expressnumber;
        }
    }
}
