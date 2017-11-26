package com.example.demon.mydemo.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RecyclerView的用法
 */
public class RecyclerViewTest extends BaseActivity {
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recycler_view_test_activity);

        initFruits();   //初始化水果数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        /**
         * 1使用线性布局实现垂直、水平的列表
         * /
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //设置为水平、默认为纵向
        recyclerView.setLayoutManager(layoutManager);
         */ //1

        /**
         * 2使用StaggeredGridLayout布局实现瀑布流
         * 参数1：指定布局的列数
         * 参数2：排列方向
         */
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        */ //2

        FruitAdapterRecyclerView adapter = new FruitAdapterRecyclerView(fruitList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 1使用线性布局实现垂直、水平的列表
     * /
    private void initFruits() {
        for (int i = 0; i < 10; i++) {
            Fruit apple = new Fruit("Apple" + i, R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana" + i, R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange" + i, R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon" + i, R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear" + i, R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape" + i, R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple" + i, R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry" + i, R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry" + i, R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango" + i, R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }
     */ //1


    /**
     * 2使用StaggeredGridLayout布局实现瀑布流
     */
    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

    // 随机将输入的内容加长
    private String getRandomLengthName(String name) {
        Random random = new Random();
        // 生成1到20之间的随机数
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
//        */ //2

}
