package com.a3.javase.chapter9.thread;

import java.awt.Color;

public class Ball implements Runnable{
	// 初始化一些对象名  
    private Color color;  
    private int x, y, radiu, vx, vy;  
    private BallFrame bf;  
    private int id;  
  
    /** 
     * 构造函数 
     *  
     * @param color小球颜色 
     * @param x小球横坐标 
     * @param y小球纵坐标 
     * @param radiu小球直径 
     * @param vx小球横向速度 
     * @param vy小球纵向速度 
     * @param bf面板 
     * @param id标志 
     */  
    public Ball(Color color, int x, int y, int radiu, int vx, int vy,  
            BallFrame bf, int id) {  
        this.color = color;  
        this.x = x;  
        this.y = y;  
        this.radiu = radiu;  
        this.vx = vx;  
        this.vy = vy;  
        this.bf = bf;  
        this.id = id;  
    }  
  
    // 重写run方法 
    public void run() {  
        
        // 执行无限循环  
        while (true) {  
            // System.out.println("第"+id+"个球的x:"+x +"   y:"+y);  
            x += vx;// 改变x的速度  
            y += vy;// 改变y的速度  
            // 如果x越界  
            if (x <= 0 || x + radiu >= bf.getWidth()) {  
                vx = -vx;// x速度反向  
                if (x < 0)  
                    x = 0;  
                else if (x > bf.getWidth() - radiu)  
                    x = bf.getWidth() - radiu;  
                else {  
                }  
            }  
            // 如果y越界  
            else if (y <= 0 || y + radiu >= bf.getHeight()) {  
                vy = -vy;// y速度反向  
                if (y < 0)  
                    y = 0;  
                else if (y > bf.getHeight() - radiu)  
                    y = bf.getHeight() - radiu;  
                else {  
                }  
            } else {  
            }  
  
            try {  
                Thread.sleep(10);// 设置睡眠时间为10ms  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            // 重绘  
            bf.repaint();  
        }  
    }  
  
    public Color getcolor() {  
        return color;  
    }  
  
    public void setcolor(Color color) {  
        this.color = color;  
    }  
  
    public int getX() {  
        return x;  
    }  
  
    public void setX(int x) {  
        this.x = x;  
    }  
  
    public int getY() {  
        return y;  
    }  
  
    public void setY(int y) {  
        this.y = y;  
    }  
  
    public int getRadiu() {  
        return radiu;  
    }  
  
    public void setRadiu(int radiu) {  
        this.radiu = radiu;  
    }  
  
    public int getVx() {  
        return vx;  
    }  
  
    public void setVx(int vx) {  
        this.vx = vx;  
    }  
  
    public int getVy() {  
        return vy;  
    }  
  
    public void setVy(int vy) {  
        this.vy = vy;  
    }  
}
