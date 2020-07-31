package org.first.booketlist.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.JsonElement;

import java.io.Serializable;

@Entity
public class BookInfo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "link")
    String link;
    @ColumnInfo(name = "image")
    String image;
    @ColumnInfo(name = "author")
    String author;
    @ColumnInfo(name = "publisher")
    String publisher;
    @ColumnInfo(name = "pubdate")
    String pubdate;
    @ColumnInfo(name = "description")
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

    @Override
    public String toString() {
        return
                "uid=" + uid +
                        ", title='" + title + '\'' +
//                        ", title='" + link + '\'' +
//                        ", title='" + image + '\'' +
//                        ", title='" + author + '\'' +
//                        ", title='" + title + '\'' +
                        '}';
    }
}
