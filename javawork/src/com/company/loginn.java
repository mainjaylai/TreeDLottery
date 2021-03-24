package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

class game extends JFrame implements ActionListener {
    private loginn login;
    private JPanel jp = new JPanel();
    private int[] num = new int[3];
    public int totalmoney;
    private JLabel bglabel = new JLabel();
    private JLabel ownmoney = new JLabel();
    private JLabel jl1 = new JLabel();
    private JLabel jl2 = new JLabel("请输入您的号码");
    private JButton jb1 = new JButton("提交");
    private JButton back = new JButton("返回");
    private JButton jb2 = new JButton("刷新中奖号码");
    private JTextField text = new JTextField();
    private JLabel times = new JLabel("<html><body>请输入你要的倍数<br>不输入默认为1倍<body></html>");
    private JTextField time = new JTextField();
    private int[] cost = {104, 200, 5, 50, 45, 90, 60, 25, 99, 4, 50, 5, 30};
    private int[][] winmoney = {{1040}, {346, 173}, {10}, {104}, {230, 12, 2}, {1040, 345, 172, 104, 69, 49, 37, 29, 23, 19, 16, 15, 15, 14}, {37, 19}, {470, 21}, {6}, {104}, {8}, {65}};
    public int flag;
    private ImageIcon icon = new ImageIcon("image//3.jpeg");

    public game(int money, int flag1) {
        flag = flag1;
        totalmoney = money;
        ownmoney.setText("您的总金额为" + totalmoney + "元");
        this.setSize(900, 450); //设置窗口的大小
        this.setLocation(300, 200);//设置窗口的初始位置
        jp.setLayout(null);
        bglabel.setIcon(icon);//将图片设置到Jlabel中
        bglabel.setBounds(0, 0, 900, 450);//设图片显示的区域
        ownmoney.setBounds(365, 20, 300, 40);
        ownmoney.setFont(new Font(null, Font.PLAIN, 25));
        times.setBounds(200, 190, 200, 30);
        time.setBounds(350, 190, 50, 30);
        jl1.setBounds(220, 80, 260, 30);
        jl1.setFont(new Font(null, Font.PLAIN, 14));
        jl2.setBounds(200, 140, 100, 30);
        jb1.setBounds(400, 250, 100, 30);
        jb2.setBounds(500, 80, 150, 30);
        text.setBounds(350, 140, 200, 30);
        back.setBounds(50, 380, 100, 30);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        text.addActionListener(this);
        back.addActionListener(this);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(text);
        jp.add(back);
        jp.add(ownmoney);
        jp.add(time);
        jp.add(times);
        jp.add(bglabel);
        update(num);
        this.add(jp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == text) {
            text.requestFocus();
        } else if (e.getSource() == jb1) {
            create(num, text.getText(), flag, time.getText());
            text.setText("");
            time.setText("");
        } else if (e.getSource() == jb2) {
            update(num);
        } else if (e.getSource() == back) {
            login = new loginn(totalmoney);
            this.dispose();
            login.setVisible(true);
        } else if (e.getSource() == times) {
            times.requestFocus();
        }
    }

    int money1(int flag, int[] num) {
        int samennum = 1, sum = 0;
        int check[] = new int[10];
        for (int i = 0; i < 3; i++) {
            check[num[i]]++;
            sum += num[i];
            if (check[num[i]] >= 2)
                samennum = check[num[i]];
        }
        if (flag == 2 || flag == 7) {
            return winmoney[flag - 1][2 - samennum];
        } else if (flag == 5)
            return winmoney[flag - 1][3 - samennum];
        else if (flag == 8)
            return 0;
        else if (flag == 6)
            return winmoney[flag - 1][sum < 27 - sum ? sum : 27 - sum];
        else return winmoney[flag - 1][0];

    }

    void update(int[] num) {
        Random rand = new Random();
        for (int i = 0; i < 3; i++)
            num[i] = rand.nextInt(10);
        int mon = money1(this.flag, num);
        if (mon == 0)
            jl1.setText("本轮的中奖号码为" + num[0] + num[1] + num[2] + "   奖金未知");
        else
            jl1.setText("本轮的中奖号码为" + num[0] + num[1] + num[2] + "   奖金为" + mon + "元");
    }

    void create(int[] num, String text, int flag, String timesText) {
        base temp = new base();
        switch (flag) {
            case 1:
                temp = new single(num, text, winmoney[flag - 1]);
                break;
            case 2:
                temp = new group(num, text, winmoney[flag - 1]);
                break;
            case 3:
                temp = new one1(num, text, winmoney[flag - 1]);
                break;
            case 4:
                temp = new towd(num, text, winmoney[flag - 1]);
                break;
            case 5:
                temp = new guess1d(num, text, winmoney[flag - 1]);
                break;
            case 6:
                temp = new guesssum(num, text, winmoney[flag - 1]);
                break;
            case 7:
                temp = new guess2d(num, text, winmoney[flag - 1]);
                break;
            case 8:
                temp = new general(num, text, winmoney[flag - 1]);
                break;
            case 9:
                temp = new bigorsmall(num, text, winmoney[flag - 1]);
                break;
            case 10:
                temp = new guesssame(num, text, winmoney[flag - 1]);
                break;
            case 11:
                temp = new guesseven(num, text, winmoney[flag - 1]);
                break;
            case 12:
                temp = new lottery(num, text, winmoney[flag - 1]);
                break;
        }
        showmessage(temp.money(), timesText, flag);
    }

    void showmessage(int money, String timesText, int flag) {
        int time = 1;
        if (timesText.equals("")) {
        } else if (!timesText.matches("^[0-9]*$")) {
            JOptionPane.showMessageDialog(
                    jp,
                    "请输入正确的倍数",
                    "警告",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        } else time = Integer.parseInt(timesText);
        if (money == -1) {
            JOptionPane.showMessageDialog(
                    jp,
                    "请输入正确的投注方式",
                    "警告",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            int result = JOptionPane.showConfirmDialog(
                    jp,
                    "确认花" + cost[flag - 1] * time + "元来购买彩票",
                    "提示",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                if (totalmoney < cost[flag - 1] * time) {
                    JOptionPane.showMessageDialog(
                            jp,
                            "啊哦，您的余额已不足，请及时充值",
                            "遗憾",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }
                totalmoney -= cost[flag - 1] * time;
                ownmoney.setText("您的总金额为" + totalmoney + "元");
                if (money == 0)
                    JOptionPane.showMessageDialog(
                            jp,
                            "很遗憾，差点就中奖啦，再接再厉",
                            "遗憾",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                else {
                    totalmoney += money * time;
                    ownmoney.setText("您的总金额为" + totalmoney + "元");
                    JOptionPane.showMessageDialog(
                            jp,
                            "恭喜您，中奖啦，中奖金额为" + money * time + "元",
                            "恭喜",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        }
    }
}

public class loginn extends JFrame implements ActionListener {
    private JPanel jf = new JPanel();
    final public int size = 12;
    private JButton[] jb = new JButton[size];
    private String[] jbtext = {"single", "group", "oned", "twod", "guess1d", "sum", "猜2D", "通选", "猜大小", "猜三同", "猜奇偶", "拖拉机"};
    private int[] cost1 = {104, 200, 5, 50, 45, 90, 60, 25, 99, 4, 50, 5, 30};
    private game game;
    private int tempmoney;
    private ImageIcon icon1 = new ImageIcon("image//1.jpeg");

    public loginn(int money) {
        tempmoney = money;
        this.setTitle("选择界面");
        this.setSize(900, 450); //设置窗口的大小
        this.setLocation(300, 200);//设置窗口的初始位置
        GridLayout fl = new GridLayout(3, 4);//使用流布局
        jf.setLayout(fl);//修改布局管理
        icon1.setImage(icon1.getImage().getScaledInstance(225, 150, Image.SCALE_DEFAULT));
        for (int i = 0; i < size; i++) {
            jb[i] = new JButton("<html><body>" + jbtext[i] + "<br>单价为" + cost1[i] + "元" + "<body></html>", icon1);
            jb[i].setHorizontalTextPosition(JButton.CENTER);
            jb[i].setFont(new Font(null, Font.BOLD, 19));
            jf.add(jb[i]);
            jb[i].addActionListener(this);
        }
        jf.setVisible(true); //显示窗口
        this.add(jf);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < size; i++) {
            if (e.getSource() == jb[i]) {
                game = new game(tempmoney, i + 1);
                game.totalmoney = tempmoney;
                game.setTitle(jbtext[i] + "  单价为" + cost1[i] + "元");
                break;
            }
        }
        game.setVisible(true);
        this.dispose();
    }
}

