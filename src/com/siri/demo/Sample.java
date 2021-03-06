package com.siri.demo;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "10393915";
    public static final String API_KEY = "uMW9gDfpsz6M9x77VyIzrs0T";
    public static final String SECRET_KEY = "hZKqzY0psGspLXihks6F6DorO6mE7pD6";

    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", 8080);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        JSONObject res = client.asr("com.siri.demo.test.pcm", "pcm", 16000, null);
        System.out.println(res.toString(2));

    }
}
