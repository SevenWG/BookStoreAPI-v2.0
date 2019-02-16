package com.team404.bookstore.entity;

import org.hibernate.criterion.Order;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrdersEntity {
    private int id;
    private int userid;
    private Timestamp generationtime;
    private double totalprice;
    private Integer addressid;
    private String status;
    private double shipping;
    private double tax;
    private double aftertaxprice;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Timestamp getGenerationtime() {
        return generationtime;
    }

    public void setGenerationtime(Timestamp generationtime) {
        this.generationtime = generationtime;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getAftertaxprice() {
        return aftertaxprice;
    }

    public void setAftertaxprice(double aftertaxprice) {
        this.aftertaxprice = aftertaxprice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (id != that.id) return false;
        if (userid != that.userid) return false;
        if (Double.compare(that.totalprice, totalprice) != 0) return false;
        if (Double.compare(that.shipping, shipping) != 0) return false;
        if (Double.compare(that.tax, tax) != 0) return false;
        if (Double.compare(that.aftertaxprice, aftertaxprice) != 0) return false;
        if (amount != that.amount) return false;
        if (generationtime != null ? !generationtime.equals(that.generationtime) : that.generationtime != null)
            return false;
        if (addressid != null ? !addressid.equals(that.addressid) : that.addressid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    public OrdersEntity() {
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + userid;
        result = 31 * result + (generationtime != null ? generationtime.hashCode() : 0);
        temp = Double.doubleToLongBits(totalprice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (addressid != null ? addressid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        temp = Double.doubleToLongBits(shipping);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(aftertaxprice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + amount;
        return result;
    }

    public OrdersEntity(int userid, int amount, int addressid, float totalprice, boolean flag) {
        this.userid = userid;
        this.generationtime = GetTimestampValue();
        this.totalprice = totalprice;
        this.addressid = addressid;
        this.status = "Processing";
        if(flag) {
            this.shipping = 5;
            this.tax = this.totalprice * 0.13;
            this.aftertaxprice = this.totalprice + this.tax + this.shipping;
        }
        else {
            this.shipping = 8;
            this.tax = this.totalprice * 0.13;
            this.aftertaxprice = this.totalprice + this.tax + this.shipping;
        }
        this.amount = amount;
    }

    private String GetOrderGenerationTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return (String)df.format(new Date());
    }

    private Timestamp GetTimestampValue() {
        String s = GetOrderGenerationTime();
        return Timestamp.valueOf(s);
    }

}
