package com.a3.javase.chapter9.thread;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallFrame extends JPanel {

	// 实例化一个数组对象  
    private Ball[] ball = new Ball[5];  
    // 实例化一个随机数对象  
    private Random r = new Random();  
  
    public static void main(String[] args) {  
        // 实例化一个面板对象  
        BallFrame bf = new BallFrame();  
        // 调用initUI方法  
        bf.initUI();  
    }  
  
    // 界面函数  
    public void initUI() {  
        JFrame jf = new JFrame();// 实例化面板对象  
        jf.setSize(new Dimension(600, 600));// 设置面板大小  
        jf.setResizable(false);// 设置不可调节大小  
        jf.setDefaultCloseOperation(3);// 设置关闭按钮  
        jf.setLocationRelativeTo(null);// 设置窗体居中  
        this.setBackground(Color.white);// 设置面板背景为白色  
        jf.setVisible(true);// 设置窗体可见  
        jf.add(this, BorderLayout.CENTER);// 将面板添加到窗体上  
  
        for (int i = 0; i < ball.length; i++) {  
            // 实例化每个小球对象  
            ball[i] = new Ball(new Color(r.nextInt(255), r.nextInt(255),  
                    r.nextInt(255)), r.nextInt(550), r.nextInt(550), 50,  
                    r.nextInt(4) + 1, r.nextInt(4) + 1, this, i);  
        }  
        for (int i = 0; i < ball.length; i++) {  
            // 将每个小球线程运行起来  
            new Thread(ball[i]).start();  
        }  
    }  
  
    // 重写paint方法  
    public void paint(Graphics g) {  
        // 调用父类的paint方法  
        super.paint(g);  
        for (int i = 0; i < ball.length; i++) {  
            // 从ball中获取颜色并设置  
            g.setColor(ball[i].getcolor());  
            // 画出小球  
            g.fillOval(ball[i].getX(), ball[i].getY(), ball[i].getRadiu(),  
                    ball[i].getRadiu());  
        }  
        // 调用碰撞函数  
        collision();  
    }  
  
    // 碰撞函数  
    private void collision() {  
        // 距离数组，存储两小球间的距离  
        double[][] dis = new double[ball.length][ball.length];  
        for (int i = 0; i < ball.length; i++) {  
            for (int j = 0; j < ball.length; j++) {  
                // 计算两个小球间的距离  
                dis[i][j] = Math.sqrt(Math.pow(ball[i].getX() - ball[j].getX(),  
                        2) + Math.pow(ball[i].getY() - ball[j].getY(), 2));  
            }  
        }  
        for (int i = 0; i < ball.length; i++) {  
            for (int j = i + 1; j < ball.length; j++) {  
                if (dis[i][j] < (ball[i].getRadiu() + ball[j].getRadiu()) / 2) {  
                    int t;  
                    // 交换小球x方向的速度  
                    t = ball[i].getVx();  
                    ball[i].setVx(ball[j].getVx());  
                    ball[j].setVx(t);  
                    // 交换小球y方向的速度  
                    t = ball[i].getVy();  
                    ball[i].setVy(ball[j].getVy());  
                    ball[j].setVy(t);  
                    // 确定碰撞后第二个小球的位置  
                    int x2 = ball[j].getX() - ball[i].getX(), y2 = ball[j]  
                            .getY() - ball[i].getY();  
                    ball[j].setX(ball[i].getX() + x2);  
                    ball[j].setY(ball[j].getY() + y2);  
                } else {  
                }  
            }  
        }  
    }  
}  
