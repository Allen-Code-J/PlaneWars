package cn.tx;

import javax.swing.*;
import java.awt.*;

public class HeroPlane extends Thread{
    //英雄机在画布上的位置
    int x = 200,y = 600;
    int width = 50, height = 50;

    int speed = 10;//飞机速度

    Image img = new ImageIcon("img/飞机/10011.png").getImage();


    //定义方向键
    boolean up,down,left,right;


    public HeroPlane() {
    }

    public HeroPlane(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        while(true) {
            if(up){
                y = y - speed;
            }
            if(down){
                y = y + speed;
            }
            if(left){
                x = x - speed;
            }
            if(right){
                x = x + speed;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
