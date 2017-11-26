package com.example.demon.mydemo.materialDesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.view.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * toolbar标题
 * DrawerLayout侧滑菜单
 * FloatingActionButton悬浮按钮
 * Snackbar在提示中加入可交互按钮
 */
public class MaterialDesignActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout; // 滑动菜单

    // 显示水果的内容
    private Fruit[] fruits = {
            new Fruit("苹果apple", R.drawable.fr_apple), new Fruit("香蕉Banana", R.drawable.fr_banana),
            new Fruit("桔子orange", R.drawable.fr_orange), new Fruit("西瓜Watermelon", R.drawable.fr_watermelon),
            new Fruit("梨子pear", R.drawable.fr_pear), new Fruit("葡萄Grape", R.drawable.fr_grape),
            new Fruit("菠萝Pineapple", R.drawable.fr_pineapple), new Fruit("草莓Strawberry", R.drawable.fr_strawberry),
            new Fruit("樱桃Cherry", R.drawable.fr_cherry), new Fruit("芒果Mango", R.drawable.fr_mango)};
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;    //下拉刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // 标题上设置最左边的提醒按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // 显示最左边的导航按钮HomeAsUp
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // 设置滑动菜单监听点击事件
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_call:
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        // 悬浮按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 用于显示的view、显示的内容、显示的时长
                Snackbar.make(view, "删除数据Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("不！Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MaterialDesignActivity.this, "数据恢复了..Data restored", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        // 主界面使用卡片式布局显示新鲜水果
        initFruits();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        // 下拉刷新
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    // 初始化水果数据
    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    // 刷新水果数据
    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    /**
     * 创建Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    // 设置Menu的监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //打开滑动抽屉
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
