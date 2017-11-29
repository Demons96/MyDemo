package com.example.demon.mydemo.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gyp52 on 17/11/27.
 * 关于当前天气的生活建议
 */

public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;     // 舒适

    @SerializedName("cw")
    public CarWash carWash;     // 较适宜

    public Sport sport;         // 较适宜

    public class Comfort {

        @SerializedName("txt")
        public String info;

    }

    public class CarWash {

        @SerializedName("txt")
        public String info;

    }

    public class Sport {

        @SerializedName("txt")
        public String info;

    }

}
/*
    "suggestion": {
        "air": {
            "brf": "中",
            "txt": "气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"
        },
        "comf": {
            "brf": "舒适",
            "txt": "白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"
        },
        "cw": {
            "brf": "较适宜",
            "txt": "较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"
        },
        "drsg": {
            "brf": "较舒适",
            "txt": "建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"
        },
        "flu": {
            "brf": "较易发",
            "txt": "昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"
        },
        "sport": {
            "brf": "较适宜",
            "txt": "天气较好，较适宜进行各种运动，但因湿度偏高，请适当降低运动强度。"
        },
        "trav": {
            "brf": "适宜",
            "txt": "天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"
        },
        "uv": {
            "brf": "弱",
            "txt": "紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"
        }
    }
 */