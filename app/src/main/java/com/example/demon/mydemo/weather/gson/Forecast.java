package com.example.demon.mydemo.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gyp52 on 17/11/27.
 * 天气预报
 * 数据中包含着数组，只需定义单日天气的实体类就好
 * 在实体集引用的时候用集合类型声明
 */

public class Forecast {

    public String date;     // 时间

    @SerializedName("cond")
    public More more;

    @SerializedName("tmp")
    public Temperature temperature;

    public class More {

        @SerializedName("txt_d")
        public String info;

    }

    public class Temperature {

        public String max;

        public String min;

    }

}
/*
    "daily_forecast": [
        {
            "astro": {
                "mr": "13:31",
                "ms": "00:30",
                "sr": "07:07",
                "ss": "18:02"
            },
            "cond": {
                "code_d": "103",
                "code_n": "104",
                "txt_d": "晴间多云",
                "txt_n": "阴"
            },
            "date": "2017-11-27",
            "hum": "69",
            "pcpn": "0.0",
            "pop": "0",
            "pres": "1018",
            "tmp": {
                "max": "23",
                "min": "17"
            },
            "uv": "5",
            "vis": "16",
            "wind": {
                "deg": "167",
                "dir": "东南风",
                "sc": "微风",
                "spd": "3"
            }
        },
        {
            "astro": {
                "mr": "14:10",
                "ms": "01:24",
                "sr": "07:08",
                "ss": "18:02"
            },
            "cond": {
                "code_d": "104",
                "code_n": "305",
                "txt_d": "阴",
                "txt_n": "小雨"
            },
            "date": "2017-11-28",
            "hum": "92",
            "pcpn": "4.9",
            "pop": "93",
            "pres": "1017",
            "tmp": {
                "max": "22",
                "min": "18"
            },
            "uv": "1",
            "vis": "17",
            "wind": {
                "deg": "154",
                "dir": "东南风",
                "sc": "微风",
                "spd": "5"
            }
        },
        {
            "astro": {
                "mr": "14:50",
                "ms": "02:19",
                "sr": "07:09",
                "ss": "18:02"
            },
            "cond": {
                "code_d": "305",
                "code_n": "305",
                "txt_d": "小雨",
                "txt_n": "小雨"
            },
            "date": "2017-11-29",
            "hum": "85",
            "pcpn": "0.0",
            "pop": "0",
            "pres": "1017",
            "tmp": {
                "max": "22",
                "min": "16"
            },
            "uv": "2",
            "vis": "16",
            "wind": {
                "deg": "12",
                "dir": "东北风",
                "sc": "微风",
                "spd": "5"
            }
        }
    ],
    "now": {
        "cond": {
            "code": "101",
            "txt": "多云"
        },
        "fl": "19",
        "hum": "89",
        "pcpn": "0",
        "pres": "1017",
        "tmp": "18",
        "vis": "10",
        "wind": {
            "deg": "153",
            "dir": "东南风",
            "sc": "微风",
            "spd": "7"
        }
    }
 */
