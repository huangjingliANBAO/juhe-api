package com.juhe.frame;

import com.juhe.entity.News;
import com.juhe.service.JuHeApiService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewsFrame {
    private JPanel mainPanel;
    private JPanel navPanel;
    private JButton topBtn;
    private JButton sportsBtn;
    private JButton fashionBtn;
    private JPanel centerPanel;
    private JPanel topPanel;
    private JPanel sportPanel;
    private JPanel fashionPanel;

    public NewsFrame() {
        CardLayout cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.add("top", topPanel);
        centerPanel.add("sports", sportPanel);
        centerPanel.add("fashion", fashionPanel);
        List<News> newsList = JuHeApiService.getNews("top");
        showData(newsList,topPanel);
        cardLayout.show(centerPanel, "top");
        topBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              List<News> newsList1 = JuHeApiService.getNews("top");
              showData(newsList1,topPanel);
              cardLayout.show(centerPanel,"top");
            }
        });
        sportsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "sports");
                List<News> newsList = JuHeApiService.getNews("tiyu");
                showData(newsList,sportPanel);
            }
        });
        fashionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "fashion");
                List<News> newsList = JuHeApiService.getNews("shishang");
                showData(newsList,fashionPanel);
            }
        });
    }
    private void showData(List<News> newsList, JPanel contentPanel) {
        contentPanel.removeAll();
        int length = newsList.size();
        int row = length % 6 == 0 ? length / 6 : length / 6 + 1;
        GridLayout gridLayout = new GridLayout(row, 4, 15, 30);
        contentPanel.setLayout(gridLayout);
        for (News news : newsList) {
            //显示新闻图片
            JPanel jPanel = new JPanel();
            JLabel imgLabel = new JLabel();
            imgLabel.setText("<html><img src='" + news.getThumbnailPicS() + "' /></html>");
            //显示标题
            JLabel titleLabel = new JLabel();
            titleLabel.setText("<html>" + news.getTitle() + "</html>");
            titleLabel.setPreferredSize(new Dimension(200, 100));
            Font font = new Font("微软雅黑", 0, 20);
            titleLabel.setFont(font);
            JLabel authorLabel = new JLabel();
            authorLabel.setText(news.getAuthorName());
            jPanel.add(imgLabel);
            jPanel.add(titleLabel);
            jPanel.add(authorLabel);
            jPanel.setPreferredSize(new Dimension(200, 200));
            contentPanel.add(jPanel);
        }
    }

    public static void main(String[] args) throws Exception{
        String lookAndFeel =
                UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        JFrame frame = new JFrame("NewsFrame");
        frame.setContentPane(new NewsFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
