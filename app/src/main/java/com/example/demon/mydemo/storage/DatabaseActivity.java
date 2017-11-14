package com.example.demon.mydemo.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.util.LogUtil;

/**
 * 数据库的用法
 */
public class DatabaseActivity extends BaseActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper = null;
    private TextView textView;  //显示数据库的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_database_activity);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);

        textView = findViewById(R.id.database_tv);

        findViewById(R.id.create_database).setOnClickListener(this);    //创建数据库
        findViewById(R.id.add_data).setOnClickListener(this);    //添加数据
        findViewById(R.id.update_data).setOnClickListener(this);    //修改数据
        findViewById(R.id.delete_data).setOnClickListener(this);    //删除数据
        findViewById(R.id.query_data).setOnClickListener(this);    //查询数据
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.create_database:      /** 创建数据库 */
//                dbHelper.getWritableDatabase();     //创建或者打开
                break;
            case R.id.add_data:             /** 增加数据 */
                ContentValues values1 = new ContentValues();
                // 开始组装第一条数据
                values1.put("name", "The Da Vinci Code");
                values1.put("author", "Dan Brown");
                values1.put("pages", 454);
                values1.put("price", 16.96);
                db.insert("Book", null, values1); // 插入第一条数据
                values1.clear();

                // 开始组装第二条数据
                values1.put("name", "The Lost Symbol");
                values1.put("author", "Dan Brown");
                values1.put("pages", 510);
                values1.put("price", 19.95);
                db.insert("Book", null, values1); // 插入第二条数据
                values1.clear();
                break;
            case R.id.update_data:          /** 修改数据 */
                ContentValues values2 = new ContentValues();
                values2.put("price", 10.99);
                db.update("Book", values2, "name = ?", new String[]{"The Da Vinci Code"});
                values2.clear();
                break;
            case R.id.delete_data:          /** 删除数据 */
                db.delete("Book", "pages > ?", new String[]{"500"});
                break;
            case R.id.query_data:           /** 查询数据*/
                textView.setText("");   //显示之前清空一下
                /**
                 * 查询Book表中所有的数据
                 * 1表名、2查询的表元素、34约束查询的某几行数据、56分组、7排序方式
                 */
                Cursor cursor4 = db.query("Book", null, null,
                        null, null, null, null);
                if (cursor4.moveToFirst()) {
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        int id = cursor4.getInt(cursor4.getColumnIndex("id"));
                        String name = cursor4.getString(cursor4.getColumnIndex("name"));
                        String author = cursor4.getString(cursor4.getColumnIndex("author"));
                        int pages = cursor4.getInt(cursor4.getColumnIndex("pages"));
                        double price = cursor4.getDouble(cursor4.getColumnIndex("price"));

                        textView.append("book name is " + id + "\n");
                        textView.append("book name is " + name + "\n");
                        textView.append("book author is " + author + "\n");
                        textView.append("book pages is " + pages + "\n");
                        textView.append("book price is " + price + "\n\n");

                        LogUtil.d("MainActivity", "book name is " + name);
                        LogUtil.d("MainActivity", "book author is " + author);
                        LogUtil.d("MainActivity", "book pages is " + pages);
                        LogUtil.d("MainActivity", "book price is " + price);
                    } while (cursor4.moveToNext());
                }
                cursor4.close();
                break;
        }
    }

    @Override
    protected void onDestroy() {    //退出活动时关闭数据库
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    // 操作数据库的类
    class MyDatabaseHelper extends SQLiteOpenHelper {

        // 书本表
        private static final String CREATE_BOOK = "create table Book ("
                + "id integer primary key autoincrement, "
                + "author text, "   //作者
                + "price real, "    //价格
                + "pages integer, " //页数
                + "name text)";     //书名

        // 记录图书的分类
        private static final String CREATE_CATEGORY = "create table Category ("
                + "id integer primary key autoincrement, "
                + "category_name text, "
                + "category_code integer)";

        private Context mContext;

        MyDatabaseHelper(Context context, String name,
                         SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            mContext = context;
        }

        // 创建数据库
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_BOOK);
            db.execSQL(CREATE_CATEGORY);
            Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
        }

        // 升级数据库
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists Book");
            db.execSQL("drop table if exists Category");
            onCreate(db);
        }

    }

}
