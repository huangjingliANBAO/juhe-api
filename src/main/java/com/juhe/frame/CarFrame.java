package com.juhe.frame;

import com.juhe.entity.Car;
import com.juhe.service.CarService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 驾考宝典窗体
 */
public class CarFrame extends JFrame implements ActionListener {
    private JPanel cardPanel;//面板
    private JButton preButton, nextButton;//按钮
    private JPanel buttonPanel;//面板
    private CardLayout cardLayout;//卡片

    public CarFrame() {
        init();
        setTitle("驾考宝典");
        setSize(1500,680);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void init() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        add(cardPanel);
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(197, 218, 63));
        preButton = new JButton("上一题");
        nextButton = new JButton("下一题");
        buttonPanel.add(preButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
        preButton.addActionListener(this);
        nextButton.addActionListener(this);
        List<Car> carList = CarService.getCar("1","c1","order");
        showData(carList,cardPanel);
    }
    private void showData(List<Car> carList,JPanel testPanel){
        testPanel.removeAll();
        int length = carList.size();
        for (Car car:carList
             ) {
            JPanel jPanel = new JPanel();
            JLabel uriLabel = new JLabel();
            uriLabel.setText("<html><img src = '" + car.getUrl() + "'/></html>");
            JLabel testLabel = new JLabel();
            testLabel.setText("<html>" + car.getId() + car.getQuestion() +"<br/>"+"<h3><答案：" + car.getAnswer() +"<br/>" + car.getItem1() + car.getItem2()+ car.getItem3()+ car.getItem4() + car.getExplains() + "</h3></html>");
            Font font = new Font("微软雅黑",0,20);
            testLabel.setFont(font);
            jPanel.add(uriLabel);
            jPanel.add(testLabel);
            testPanel.add(jPanel);
        }
    }
    public static void main(String[] args) throws Exception {
        new CarFrame();
        String lookAndFeel =
                UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == preButton) {
            cardLayout.previous(cardPanel);
        } else {
            cardLayout.next(cardPanel);
        }
    }
}
