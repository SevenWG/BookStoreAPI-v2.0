package com.team404.bookstore.entity;

public class ShoppingCartEntity {
    private int id;
    private int userid;
    private String bookid;
    private int quantity;

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

        ShoppingCartEntity that = (ShoppingCartEntity) o;

        if (id != that.id) return false;
        if (userid != that.userid) return false;
        if (quantity != that.quantity) return false;
        if (bookid != null ? !bookid.equals(that.bookid) : that.bookid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userid;
        result = 31 * result + (bookid != null ? bookid.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
