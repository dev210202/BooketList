package org.first.booketlist;

import com.google.gson.JsonElement;

public class BookInfo {
    String title;
    String link;
    String image;
    String author;
    String publisher;
    String pubdate;
    String description;

    public BookInfo(String title, String link, String image, String author, String publisher, String pubdate, String description) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
