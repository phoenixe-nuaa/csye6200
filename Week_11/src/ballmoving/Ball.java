package ballmoving;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
    Random rand = new Random();
    int x = rand.nextInt(1000);
    int y = rand.nextInt(600);
    int R = rand.nextInt(100);// 半径
    private int speedX = rand.nextInt(20), speedY = rand.nextInt(20);// 小球运动速度
    private int r = 1000, d = 600;// 右限，下限
    private Graphics g;
    private Framball fb;

    public void setG(Graphics g, Framball fb) {
        this.g = g;
        this.fb = fb;
    }

    public void run() {// 表示小球的运动，可反弹，必须放在while循环中才能跑起来
        g.setColor(fb.getContentPane().getBackground());//切换为背景色
        g.fillOval(x - R - speedX, y - R - speedY, R, R);//减掉R表示在坐标替换为圆心，减掉速度表示将上一个小球掩盖掉
        g.setColor(Color.black);
        g.fillOval(x - R, y - R, R, R);
        if (y >= d)//当y接触到下限时
            speedY *= -1;//速度反向
        else if (y <= 0)
            speedY *= -1;
        if (x >= r)
            speedX *= -1;
        else if (x <= 0)
            speedX *= -1;
        x += speedX;//每一次run，就移动一次速度值
        y += speedY;
        //只执行一次run方法,小球是不会动的
    }
}