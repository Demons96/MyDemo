package com.example.demon.mydemo.weather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by gyp52 on 17/11/27.
 * 市
 */

public class City extends DataSupport {
    private int id;
    private String cityName;    // 市名

    private int cityCode;       // 市代码
    private int provinceId;     // 当前市所属的省

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

}

/*
[
    {
        "id": 226,
        "name": "南宁"
    },
    {
        "id": 227,
        "name": "崇左"
    },
    {
        "id": 228,
        "name": "柳州"
    },
    {
        "id": 229,
        "name": "来宾"
    },
    {
        "id": 230,
        "name": "桂林"
    },
    {
        "id": 231,
        "name": "梧州"
    },
    {
        "id": 232,
        "name": "贺州"
    },
    {
        "id": 233,
        "name": "贵港"
    },
    {
        "id": 234,
        "name": "玉林"
    },
    {
        "id": 235,
        "name": "百色"
    },
    {
        "id": 236,
        "name": "钦州"
    },
    {
        "id": 237,
        "name": "河池"
    },
    {
        "id": 238,
        "name": "北海"
    },
    {
        "id": 239,
        "name": "防城港"
    }
]
*/