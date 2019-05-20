package com.juhe.frame;


import com.juhe.entity.Weather;
import com.juhe.service.WeatherService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WeatherFrame extends JFrame {
    private JPanel conterPanel;
    public WeatherFrame(){
        init();
        setTitle("天气预报");
        setSize(1000,1000);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void init(){
        conterPanel = new JPanel();
        add(conterPanel);
        List<Weather> weatherList = WeatherService.getWeather();
        showData(weatherList,conterPanel);
    }
    public void showData(List<Weather> weatherList,JPanel contentPanel){
        contentPanel.removeAll();
        int length = weatherList.size();
        for (Weather weather:weatherList){
            JPanel jPanel = new JPanel();
            JLabel titleLabel = new JLabel();
            titleLabel.setText("<html>" +weather.getDate()+weather.getTemperature()+weather.getWeather()+ "</html>");
            Font font = new Font("宋体",0,20);
            titleLabel.setFont(font);
            jPanel.add(titleLabel);
            contentPanel.add(jPanel);
        }
    }
    public static void main(String[] args) {
        new WeatherFrame();
    }
}
