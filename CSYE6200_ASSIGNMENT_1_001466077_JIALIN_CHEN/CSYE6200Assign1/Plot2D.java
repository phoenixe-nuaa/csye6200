import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Plot2D {
	
	public static void generateMap(List<Student> stuList) {
		JFrame jframe = new JFrame("Plot");
		JPanel jpanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				// 设置线条粗细
				g2.setStroke( new BasicStroke(3.0f));
				g2.setColor(Color.BLUE);
				
				
				// 给stuList的每个成员进行画线
				for(Student stu: stuList) {
					// 画当前的Student对象的位置点
					g.fillArc(stu.getX(), stu.getY(), 20, 20, 0, 360);
					// 在当前Student对象的旁边画一个标签
					g.drawString(stu.getName() + "(" + stu.getX() + ", " +
					stu.getY() + ")", stu.getX() + 25, stu.getY() + 25);
				}
				
				
				
				//画线
//				g2.drawLine(1, 1, 100, 100);
				
			
			
				/*g.drawOval(100, 70, 30, 30);// 头部（画圆形）
                g.drawRect(105, 100, 20, 30);// 身体（画矩形）
                g.drawLine(105, 100, 75, 120);// 左臂（画直线）
                g.drawLine(125, 100, 150, 120);// 右臂（画直线）
                g.drawLine(105, 130, 75, 150);// 左腿（画直线）
                g.drawLine(125, 130, 150, 150);// 右腿（画直线）
                g.drawString("xxx", 80, 40);
                g.fill3DRect(100, 200, 100, 100, false);
                // 画填充颜色的圆
                g.fillArc(200, 200, 50, 50, 0, 360);*/
                g2.dispose();
                g.dispose();
			}
		};
		jframe.add(jpanel);
		jframe.setSize(800, 800);
		jframe.setVisible(true);
	}
}
