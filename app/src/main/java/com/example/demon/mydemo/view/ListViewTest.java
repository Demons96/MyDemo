
package com.example.demon.mydemo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewTest extends BaseActivity {
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list_view_test_activity);
        initFruits(); // 初始化水果数据
        FruitAdapterListView adapter = new FruitAdapterListView(ListViewTest.this, R.layout.view_fruit_item_list_view, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(ListViewTest.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

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
}
