package com.ycsx.www.wms.bean;

import java.util.List;

/**
 * Created by ZZS_PC on 2017/6/28.
 */
public class AddOrderInfo {
    private int id;
    private String name;
    private String ouaddress;
    private String dateTime;
    private String ocost;
    private List<DataBean> data;

    public static class DataBean{
        private int pid;
        private int num;
        private double price;

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

    public String getOcost() {
        return ocost;
    }

    public void setOcost(String ocost) {
        this.ocost = ocost;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
}
