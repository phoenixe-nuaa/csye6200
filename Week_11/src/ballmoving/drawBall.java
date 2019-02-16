package ballmoving;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class drawBall extends Thread{
    ArrayList <Ball> list=new ArrayList<Ball>();//注意后面的写法，写错会报空指针异常
    Graphics g;
    Framball fb;

    public void setG(Graphics g,Framball fb,ArrayList <Ball> list) {
        this.g=g;
        this.fb=fb;
        this.list=list;
    }

    public void run() {
        while(true) {
            Ball b=new Ball();//循环中不断新建一个球
            b.setG(g, fb);//每个球都设置自己的g和fb
            list.add(b);//把球放进队列中
            try{
                sleep(10);
            }catch(Exception ef) {}
            System.out.println(list.size());
            if(list.size()==10)break;//设置小球存储的个数
        }
    }
}
