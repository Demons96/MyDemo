package com.example.demon.mydemo.weather.gson;

/**
 * Created by gyp52 on 17/11/27.
 * 空气质量
 */

public class AQI {

    public AQICity city;

    public class AQICity {

        public String aqi;  // 空气质量指数

        public String pm25;

    }

}
/*
    "aqi": {
        "city": {
            "aqi": "94",
            "co": "2",
            "no2": "87",
            "o3": "5",
            "pm10": "138",
            "pm25": "67",
            "qlty": "良",
            "so2": "11"
        }
    }
 */