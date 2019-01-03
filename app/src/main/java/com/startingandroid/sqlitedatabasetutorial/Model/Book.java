package com.startingandroid.sqlitedatabasetutorial.Model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String name, content, number, icon, bookmark;

    public Book(int id, String name, String content, String icon, String bookmark) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.icon = icon;
        this.bookmark = bookmark;

    }

    public Book(int id) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.icon = icon;
        this.bookmark =bookmark;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }
}
