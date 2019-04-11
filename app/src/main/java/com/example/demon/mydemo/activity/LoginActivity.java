package com.example.demon.mydemo.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demon.mydemo.MainActivity;
import com.example.demon.mydemo.R;
import com.example.demon.mydemo.myclass.User;
import com.example.demon.mydemo.util.BaseActivity;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    static private User user = new User();
    private SharedPreferences pref;     // 读
    private SharedPreferences.Editor editor;    // 写
    private EditText accountEdit;   //账号
    private EditText passwordEdit;  //密码

    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        findViewById(R.id.login).setOnClickListener(this);  //登陆

        boolean isRemember = pref.getBoolean("remember_password", false);   // 是否记住密码
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            user.setName(pref.getString("account", ""));
            user.setPassword(pref.getString("password", ""));
            accountEdit.setText(user.getName());
            passwordEdit.setText(user.getPassword());
            rememberPass.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        user.setName(accountEdit.getText().toString());
        user.setPassword(passwordEdit.getText().toString());

        // 密码是admin，就认为登录成功
        if (user.getPassword().equals("admin")) {
            editor = pref.edit();   // 存数据用
            if (rememberPass.isChecked()) { // 检查复选框是否被选中
                editor.putBoolean("remember_password", true);
                editor.putString("account", user.getName());
                editor.putString("password", user.getPassword());
            } else {
                editor.clear();
            }
            editor.apply();
            startMain(user);
        } else {
            Toast.makeText(LoginActivity.this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    private void startMain(User u) {
        MainActivity.actionStart(LoginActivity.this, u);
        finish();
    }

}
