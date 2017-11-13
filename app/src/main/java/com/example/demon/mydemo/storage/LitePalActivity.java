package com.example.demon.mydemo.storage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.LogUtil;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * 用了开源的LitePal对象关系映射模式数据库
 */
public class LitePalActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_lite_pal_activity);

        textView = findViewById(R.id.database_tv);

        findViewById(R.id.create_database).setOnClickListener(this);    //创建数据库
        findViewById(R.id.add_data).setOnClickListener(this);    //添加数据
        findViewById(R.id.update_data).setOnClickListener(this);    //修改数据
        findViewById(R.id.delete_data).setOnClickListener(this);    //删除数据
        findViewById(R.id.query_data).setOnClickListener(this);    //查询数据
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_database:      /** 创建数据库 */
                Connector.getDatabase();
                Toast.makeText(this, "创建数据库成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_data:             /** 增加数据 */
                BookLitePal book = new BookLitePal();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
                break;
            case R.id.update_data:          /** 修改数据 */
                BookLitePal book1 = new BookLitePal();
                book1.setName("第一本书");
                book1.setAuthor("作者");
                book1.setPages(454);
                book1.setPrice(16.96);
                book1.setPress("Unknow");
                book1.save();

                // 第一种修改方式，对已存储的对象重新赋值
                book1.setPages(300);
                book1.save();

                // 第二种修改方式，updateAll方法
                book1.setPrice(14.95);
                book1.setPress("Anchor");
                book1.updateAll("name = ? and author = ?", "第一本书", "作者");

                // 想更新成默认值不能直接设置需调用以下方法
                book1.setToDefault("press");
                book1.updateAll();
                break;
            case R.id.delete_data:          /** 删除数据 */
                DataSupport.deleteAll(BookLitePal.class, "price < ?", "15");
                break;
            case R.id.query_data:           /** 查询数据*/
                textView.setText("");

                //指定条件查询：哪几列、约束、排序默认升序asc、数量
//                DataSupport.select("name").where("page>?","400")
//                        .order("price desc").limit(3).find(BookLitePal.class);

                //通过SQL查询、返回Cursor对象
//                DataSupport.findBySQL("SQL语句");

                List<BookLitePal> books = DataSupport.findAll(BookLitePal.class);
                for (BookLitePal book2 : books) {

                    textView.append("book name is " + book2.getName() + "\n");
                    textView.append("book author is " + book2.getAuthor() + "\n");
                    textView.append("book pages is " + book2.getPages() + "\n");
                    textView.append("book price is " + book2.getPrice() + "\n");
                    textView.append("book press is " + book2.getPress() + "\n\n");

                    LogUtil.d("MainActivity", "book name is: " + book2.getName());
                    LogUtil.d("MainActivity", "book author is: " + book2.getAuthor());
                    LogUtil.d("MainActivity", "book pages is: " + book2.getPages());
                    LogUtil.d("MainActivity", "book price is: " + book2.getPrice());
                    LogUtil.d("MainActivity", "book press is: " + book2.getPress());
                }
                break;
        }
    }

}
