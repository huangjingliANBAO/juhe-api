package com.juhe.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juhe.entity.News;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.HttpURLConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求聚合数据的新闻数据的service类
 */
public class JuHeApiService {
    public static List<News> getNews(String type) {
        List<News> newsList = new ArrayList<>();
        HttpClient client = new HttpClient();
        // 请求的uri，服务器返回的将是JSON数据
        GetMethod method = new GetMethod("http://v.juhe.cn/toutiao/index?type=" + type + "&key=6d0c33bf560d268d3ecbd95cebf08c0d");
        int code;
        String data = "";
        try {
            //执行请求
            code = client.executeMethod(method);
            //如果成功
            if (code == HttpURLConnection.HTTP_OK) {
                //将数据转成String
                data = method.getResponseBodyAsString();
                //通过fastjson将String转成Java对象
                JSONObject jsonObject = JSONObject.parseObject(data);
                Object o = jsonObject.get("result");
                JSONObject jsonObject1 = JSONObject.parseObject(o.toString());
                newsList = JSONArray.parseArray(jsonObject1.get("data").toString(), News.class);
                newsList.forEach(news -> System.out.println(news));
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        method.releaseConnection();//释放连接
        return newsList;
    }

    public static void main(String[] args) {
        getNews("tiyu");
    }
}
