package com.example.demon.mydemo.weather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by gyp52 on 17/11/27.
 * 省的数据库模型代码
 */

public class Province extends DataSupport {
    private int id;                 // 每个实体都应该有的字段
    private String provinceName;    // 省名

    private int provinceCode;       // 省的json代号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}

/*
 [
     {
     "id": 1,
     "name": "北京"
     },
     {
     "id": 2,
     "name": "上海"
     },
     {
     "id": 3,
     "name": "天津"
     },
     {
     "id": 4,
     "name": "重庆"
     },
     {
     "id": 5,
     "name": "香港"
     },
     {
     "id": 6,
     "name": "澳门"
     },
     {
     "id": 7,
     "name": "台湾"
     },
     {
     "id": 8,
     "name": "黑龙江"
     },
     {
     "id": 9,
     "name": "吉林"
     },
     {
     "id": 10,
     "name": "辽宁"
     },
     {
     "id": 11,
     "name": "内蒙古"
     },
     {
     "id": 12,
     "name": "河北"
     },
     {
     "id": 13,
     "name": "河南"
     },
     {
     "id": 14,
     "name": "山西"
     },
     {
     "id": 15,
     "name": "山东"
     },
     {
     "id": 16,
     "name": "江苏"
     },
     {
     "id": 17,
     "name": "浙江"
     },
     {
     "id": 18,
     "name": "福建"
     },
     {
     "id": 19,
     "name": "江西"
     },
     {
     "id": 20,
     "name": "安徽"
     },
     {
     "id": 21,
     "name": "湖北"
     },
     {
     "id": 22,
     "name": "湖南"
     },
     {
     "id": 23,
     "name": "广东"
     },
     {
     "id": 24,
     "name": "广西"
     },
     {
     "id": 25,
     "name": "海南"
     },
     {
     "id": 26,
     "name": "贵州"
     },
     {
     "id": 27,
     "name": "云南"
     },
     {
     "id": 28,
     "name": "四川"
     },
     {
     "id": 29,
     "name": "西藏"
     },
     {
     "id": 30,
     "name": "陕西"
     },
     {
     "id": 31,
     "name": "宁夏"
     },
     {
     "id": 32,
     "name": "甘肃"
     },
     {
     "id": 33,
     "name": "青海"
     },
     {
     "id": 34,
     "name": "新疆"
     }
 ]
 */