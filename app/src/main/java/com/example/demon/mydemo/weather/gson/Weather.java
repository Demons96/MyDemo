package com.example.demon.mydemo.weather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gyp52 on 17/11/27.
 * 总的实例类
 * http://guolin.tech/api/china/24/226
 * http://guolin.tech/api/weather?cityid=CN101300101&key=4644209d93d9436784cc5b1d1e7cbd49
 */

public class Weather {

    public String status;   // 状态码

    public Basic basic;     // 城市的基本信息

    public AQI aqi;         // 空气质量

    public Now now;         // 天气信息

    public Suggestion suggestion;   // 关于当前天气的生活建议

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;     // 天气预报

}
/*
 "HeWeather": [
        {
            "status": "ok",
            "aqi": {

            },
            "basic": {

            },
            "daily_forecast": [

            ],
            "now": {

            },
            "suggestion": {

            }
        }
    ]
 */