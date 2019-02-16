package ballmoving;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Framball extends JFrame{
	
    private static ArrayList <Ball> list = new ArrayList<Ball>();//新建的list一定要初始化
    private Graphics g;
    public void showUI() {

        this.setTitle("小球");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        // 设置布局,流式布局
        this.setLayout(new FlowLayout());
        this.setVisible(true);
         g=this.getGraphics();
    }

    public static void main(String[] args) {
        Framball fb=new Framball();
        fb.showUI();//可见才能得到画布

        drawBall db=new drawBall();
        db.setG(fb.g,fb,list);//传三个参数
        db.start();//启动线程，线程启动后，他会自动运行，并且只运行run方法，想要运行其他方法必须另外调用

        //moveBall中的list是来自drawBall中的，所以必须等db执行完才能启动moveBall
        moveBall mb=new moveBall();
        mb.setL(list);
        mb.start();//启动线程

    }

}
