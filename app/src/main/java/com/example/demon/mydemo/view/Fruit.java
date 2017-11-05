package com.example.demon.mydemo.view;

/**
 * Created by gyp52 on 17/11/4.
 * 定义一个实体类作为适配器的适配类型
 */

public class Fruit {
    private String name;    //水果名
    private int imageId;    //资源id

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
