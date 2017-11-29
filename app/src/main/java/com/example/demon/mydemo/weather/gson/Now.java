package com.example.demon.mydemo.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gyp52 on 17/11/27.
 * 天气信息
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;  // 温度

    @SerializedName("cond")
    public More more;

    public class More {

        @SerializedName("txt")
        public String info;

    }

}
/*
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