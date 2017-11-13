package com.example.demon.mydemo.storage;

import org.litepal.crud.DataSupport;

/**
 * Created by gyp52 on 17/11/12.
 * 对象关系映射表的类Book
 */

public class BookLitePal extends DataSupport {
    private int id;
    private String author;  //作者
    private double price;   //价格
    private int pages;      //页数
    private String name;    //名称
    private String press;   //出版社

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}
