package com.example.demon.mydemo.storage;

import org.litepal.crud.DataSupport;

/**
 * Created by gyp52 on 17/11/12.
 * LitePal对象关系映射表的类CategoryLitePal
 */
public class CategoryLitePal extends DataSupport {
    private int id;
    private String categoryName;
    private int categoryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
