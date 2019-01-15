package com.team404.bookstore.entity;

public class BookEntity {
    private String bookid;
    private String title;
    private int price;
    private String author;
    private int categoryid;
    private String imgUrl;
    private String description;
    private Integer publisherYear;
    private int inventory;

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPublisherYear() {
        return publisherYear;
    }

    public void setPublisherYear(Integer publisherYear) {
        this.publisherYear = publisherYear;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (price != that.price) return false;
        if (categoryid != that.categoryid) return false;
        if (inventory != that.inventory) return false;
        if (bookid != null ? !bookid.equals(that.bookid) : that.bookid != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (imgUrl != null ? !imgUrl.equals(that.imgUrl) : that.imgUrl != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (publisherYear != null ? !publisherYear.equals(that.publisherYear) : that.publisherYear != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookid != null ? bookid.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + categoryid;
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publisherYear != null ? publisherYear.hashCode() : 0);
        result = 31 * result + inventory;
        return result;
    }
}
