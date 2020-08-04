package org.first.booketlist.model;

import java.util.List;

public class BookData {
    String lastBuildDate;
    int total;
    int start;
    int display;
    List<BookInfo> items;

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public List<BookInfo> getItems() {
        return items;
    }

    public void setItems(List<BookInfo> items) {
        this.items = items;
    }
}
