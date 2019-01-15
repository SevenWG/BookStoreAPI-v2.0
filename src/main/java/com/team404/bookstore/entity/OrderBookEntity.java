package com.team404.bookstore.entity;

public class OrderBookEntity {
    private int id;
    private int orderid;
    private String bookid;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderBookEntity that = (OrderBookEntity) o;

        if (id != that.id) return false;
        if (orderid != that.orderid) return false;
        if (quantity != that.quantity) return false;
        if (bookid != null ? !bookid.equals(that.bookid) : that.bookid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderid;
        result = 31 * result + (bookid != null ? bookid.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
