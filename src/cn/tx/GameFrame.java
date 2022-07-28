package cn.tx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

public class GameFrame extends JFrame {
    HeroPlane heroPlane;

    //定义子弹安全集合
    Vector<Bullet> bullets = new Vector<>();
    //敌机集合
    Vector<EnemyPlane> enmys = new Vector<>();

    GameFrame frame;

    public GameFrame() {
        frame = this;
        //创建英雄机
        heroPlane = new HeroPlane();
        heroPlane.start();
        this.setSize(500, 760);
        this.setTitle("雷霆战机");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        //产生敌机线程
        new Thread(new Runnable() {
            Random r = new Random();
            @Override
            public void run() {
                while (true) {
                    //添加敌机时候x轴随机
                    EnemyPlane enemyPlane = new EnemyPlane(r.nextInt(500),0,frame);
                    enemyPlane.start();
                    enmys.add(enemyPlane);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public void paint(Graphics g) {
        System.out.println("绘制画板");
        BufferedImage image = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);
        Graphics bi = image.getGraphics();

        bi.drawImage(new ImageIcon("img/飞机/MAP02_01.png").getImage(), 0, 0, null);

        bi.drawImage(heroPlane.img, heroPlane.x, heroPlane.y, heroPlane.width, heroPlane.height, null);
        //飞机发标
        for (int i = 0; i < bullets.size(); i++) {

            Bullet bullet = bullets.get(i);
            if (bullet.y > 0)
                bi.drawImage(bullet.image, bullet.x, bullet.y -= bullet.speed, bullet.width, bullet.height, null);
            else
                bullets.remove(bullet);
        }

        //画敌机
        for (int i = 0; i < enmys.size(); i++) {

            EnemyPlane ep = enmys.get(i);

            if (ep.y < 760)
                bi.drawImage(ep.img, ep.x, ep.y += ep.speed, ep.width, ep.height, null);
            else
                enmys.remove(ep);
        }


        g.drawImage(image, 0, 0, null);


    }

    public static void main(String[] args) {

        GameFrame frame = new GameFrame();
        Player player = new Player(frame);
        frame.addKeyListener(player);
    }
}