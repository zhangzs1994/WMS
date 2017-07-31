package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/28.
 */
public class AddOrderInfo {
    private int id;
    private int paymentway;
    private String name;
    private String contact;
    private String receiving;
    private String ouaddress;
    private String classify;
    private String remarke;
    private String dateTime;
    private String actualcost;
    private double ocost;
    private List<DataBean> data;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public static class DataBean{
        private int pid;
        private int num;
        private double price;
        private double iocost;
        private String describe;


        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getIocost() {
            return iocost;
        }

        public void setIocost(double iocost) {
            this.iocost = iocost;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getReceiving() {
        return receiving;
    }

    public void setReceiving(String receiving) {
        this.receiving = receiving;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOuaddress() {
        return ouaddress;
    }

    public void setOuaddress(String ouaddress) {
        this.ouaddress = ouaddress;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getOcost() {
        return ocost;
    }

    public void setOcost(double ocost) {
        this.ocost = ocost;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getRemarke() {
        return remarke;
    }

    public void setRemarke(String remarke) {
        this.remarke = remarke;
    }

    public int getPaymentway() {
        return paymentway;
    }

    public void setPaymentway(int paymentway) {
        this.paymentway = paymentway;
    }

    public String getActualcost() {
        return actualcost;
    }

    public void setActualcost(String actualcost) {
        this.actualcost = actualcost;
    }
}
