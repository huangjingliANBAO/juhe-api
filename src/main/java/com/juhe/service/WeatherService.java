package com.juhe.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juhe.entity.Weather;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.HttpURLConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {
public static List<Weather> getWeather(){
    List<Weather> weatherList = new ArrayList<>();
    HttpClient client = new HttpClient();
    GetMethod method = new GetMethod("http://apis.juhe.cn/simpleWeather/query?city=%E8%8B%8F%E5%B7%9E&key=146e98c59e36e536295509d81f5dd8f5" );
    int code;
    String future="";
    try{
        code = client.executeMethod(method);
        if (code == HttpURLConnection.HTTP_OK){
            future = method.getResponseBodyAsString();
            //通过fastjson将String转成Java对象
            JSONObject jsonObject = JSONObject.parseObject(future);
            Object o = jsonObject.get("result");
            JSONObject jsonObject1 = JSONObject.parseObject(o.toString());
            weatherList = JSONArray.parseArray(jsonObject1.get("future").toString(), Weather.class);
            weatherList.forEach(weather -> System.out.println(weather));
        }
    }catch (HttpException e){
        e.printStackTrace();
    }catch (IOException e){
        e.printStackTrace();
    }
    method.releaseConnection();
    return weatherList;
}
    public static void main(String[] args) {
    getWeather();
    }
}
