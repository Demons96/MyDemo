package com.example.demon.mydemo.weather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by gyp52 on 17/11/27.
 * 县
 */

public class County extends DataSupport {
    private int id;
    private String countyName;  // 县名

    private String weatherId;   // 天气对应的id
    private int cityId;         // 当前县所对应json的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

}

/*
[
    {
        "id": 1635,
        "name": "南宁",
        "weather_id": "CN101300101"
    },
    {
        "id": 1636,
        "name": "邕宁",
        "weather_id": "CN101300103"
    },
    {
        "id": 1637,
        "name": "横县",
        "weather_id": "CN101300104"
    },
    {
        "id": 1638,
        "name": "隆安",
        "weather_id": "CN101300105"
    },
    {
        "id": 1639,
        "name": "马山",
        "weather_id": "CN101300106"
    },
    {
        "id": 1640,
        "name": "上林",
        "weather_id": "CN101300107"
    },
    {
        "id": 1641,
        "name": "武鸣",
        "weather_id": "CN101300108"
    },
    {
        "id": 1642,
        "name": "宾阳",
        "weather_id": "CN101300109"
    }
]
 */