package com.example.demon.mydemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demon.mydemo.R;

import java.util.List;

/**
 * Created by gyp52 on 17/11/4.
 * 自定义的ListView适配器类
 */

public class FruitAdapterListView extends ArrayAdapter<Fruit> {
    private int resourceId; // 资源id

    public FruitAdapterListView(Context context, int textViewResourceId, List<Fruit> objects) {
        // 上下文，ListView子布局id，数据
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    /**
     * @param position 第几个
     * @param convertView 将之前加载好的布局进行缓存
     * @param parent 子项布局
     * @return
     * 在每个子项滚动到屏幕内都会被调用
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);    // 获取当前项的Fruit实例数据
        View view;      // ListView中的子视图
        ViewHolder viewHolder;  // 对控件的实例进行缓存
        if (convertView == null) {
            // 为子项加载传入的布局parent、
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        assert fruit != null;   // 如条件不成立系统将给出警告并退出
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }

}
