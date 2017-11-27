package com.example.demon.mydemo.myclass;

import java.io.Serializable;

/**
 * Created by gyp52 on 17/11/26.
 * 存放用户信息
 * Serializable:用于Intent传递对象
 */

public class User implements Serializable{
    private String id;          // 用户id
    private String name;        // 用户名
    private String password;    // 密码
    private String phone;       // 电话
    private String email;       // 邮箱

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
