package com.example.demon.mydemo.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gyp52 on 17/11/27.
 * 城市的基本信息
 */

public class Basic {

    @SerializedName("city")     // 不适合直接命名的字段可用注解方式使其产生映射
    public String cityName;     // 城市名

    @SerializedName("id")
    public String weatherId;    // 天气id

    public Update update;       // 天气更新时间

    public class Update {

        @SerializedName("loc")
        public String updateTime;

    }

}
/*
    "basic": {
        "city": "南宁",
        "cnty": "中国",
        "id": "CN101300101",
        "lat": "22.82402039",
        "lon": "108.32000732",
        "update": {
            "loc": "2017-11-27 22:53",
            "utc": "2017-11-27 14:53"
        }
    }
 */
