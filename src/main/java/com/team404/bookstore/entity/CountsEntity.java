package com.team404.bookstore.entity;

public class CountsEntity {
    private int id;
    private int counts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountsEntity that = (CountsEntity) o;

        if (id != that.id) return false;
        if (counts != that.counts) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + counts;
        return result;
    }
}
