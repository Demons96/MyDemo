package com.example.demon.mydemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.demon.mydemo.R;

/**
 * Created by gyp52 on 17/11/4.
 * RecyclerView的适配器
 */

public class FruitAdapterRecyclerView extends RecyclerView.Adapter<FruitAdapterRecyclerView.ViewHolder> {
    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        // view：Recycler子项的最外层布局
        public ViewHolder(View view) {
            super(view);
            fruitView = view;   // 用与设置点击事件
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    // 把要展示的数据源传进来
    public FruitAdapterRecyclerView(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    // 创建ViewHolder的实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_fruit_item_recycler_view_vertical, parent, false);  //垂直
//                .inflate(R.layout.view_fruit_item_recycler_view_horizontal, parent, false); //水平
        final ViewHolder holder = new ViewHolder(view);

        // 设置整个子View的监听事件
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        // 设置子View里面的图像的点击事件
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    // 对RecyclerView子项的数据进行赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    // 告诉RecyclerView有多少个子项
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
