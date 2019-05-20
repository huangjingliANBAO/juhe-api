package com.juhe.service;

        import com.alibaba.fastjson.JSONArray;
        import com.alibaba.fastjson.JSONObject;
        import com.juhe.entity.Car;
        import org.apache.commons.httpclient.HttpClient;
        import org.apache.commons.httpclient.HttpException;
        import org.apache.commons.httpclient.methods.GetMethod;
        import org.apache.commons.httpclient.util.HttpURLConnection;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

/**
 * 请求聚合数据的驾考宝典的service类
 */
public class CarService {
    public static List<Car> getCar(String subject,String model,String rand){
        List<Car> carList = new ArrayList<>();
        HttpClient client = new HttpClient();
        //请求uri，服务器返回的将是JSON数据
        GetMethod method = new GetMethod("http://v.juhe.cn/jztk/query?subject=1&model=c1&key=c4f2990faaa62e53f2c80704b91b83b6&testType=rand" +subject+model+rand);
        int code;
        String result = "";
        try{
            //执行请求
            code = client.executeMethod(method);
            //如果成功
            if (code == HttpURLConnection.HTTP_OK){
                //将数据转出String
                result = method.getResponseBodyAsString();
                //通过fastjson将String转成java对象
                JSONObject jsonObject = JSONObject.parseObject(result);
                carList = JSONArray.parseArray(jsonObject.get("result").toString(),Car.class);
                carList.forEach(car -> System.out.println(car));
            }
        }catch (HttpException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        method.releaseConnection();
        return carList;
    }
}
