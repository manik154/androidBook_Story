package com.startingandroid.sqlitedatabasetutorial.Model;

import java.io.Serializable;

public class Book implements Serializable
{
    private int id;
    private String name, email, content, year, number,icon;

    public Book(int id,String name,String content,String icon) {
        this.id=id;
        this.name = name;
        this.content = content;
    this.icon=icon;
    }

    public Book(int id) {
        this.id = id;
        this.name = name;
        this.content = content;
   this.icon=icon;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon =icon;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
