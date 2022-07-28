package cn.tx;

import javax.swing.*;
import java.awt.*;

public class EnemyPlane extends Thread {
    public GameFrame gf;
    int x , y ;
    int speed = 2;//飞机移动速度
    int width = 50, height = 50;

    Image img = new ImageIcon("img/飞机/10022.png").getImage();

    public EnemyPlane(int x, int y,GameFrame gf) {
        this.gf = gf;
        this.x = x;
        this.y = y;
    }

    public EnemyPlane(int x, int y, int width, int height, GameFrame gf) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gf = gf;
    }

    public void run() {
        while (true) {
            if (hit()) {
                System.out.println("hit.....");
                this.speed = 0;
                this.img = new ImageIcon("img/飞机/300350.png").getImage();
                try {
                    this.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gf.enmys.remove(this);
                break;
            }
            if (this.y >= 760) break;
        }
        try {
            this.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean hit() {
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle rect = null;
        for (int i = 0; i < gf.bullets.size(); i++) {
            Bullet bullet = gf.bullets.get(i);
            System.out.println("test hit");
            rect = new Rectangle(bullet.x, bullet.y - 1, bullet.width, bullet.height);
            if (myrect.intersects(rect)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "EnemyPlane{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}