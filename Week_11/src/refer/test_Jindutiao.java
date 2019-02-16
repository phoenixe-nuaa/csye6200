package refer;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class test_Jindutiao extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String STR = "Completed : ";
    private JProgressBar progressBar = new JProgressBar();
    private JTextField text = new JTextField(10);
    private JButton start = new JButton("Start");
    private JButton end = new JButton("End");
    private boolean flag = false;
    private int count = 0;

    private GoThread t = null; // 创建一个线程

    private Runnable run = null;// 更新组件的线程

    public test_Jindutiao() {
        this.setLayout(new FlowLayout());
        add(progressBar);
        text.setEditable(false);
        add(text);
        add(start);
        add(end);
        start.addActionListener(new Start());
        end.addActionListener(new End());

        run = new Runnable() {// 实例化更新组件的线程
        public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("threadA");
                progressBar.setValue(count);  // 设置进度条
                text.setText(STR + String.valueOf(count) + "%"); // 右边显示文本框 （数字变化）
            }
        };
    }

    private void go() {
        while (count < 100) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (flag) {   // flag为true
                count++;
                System.out.println(Thread.currentThread().getName());
                System.out.println("threadB");
                SwingUtilities.invokeLater(run);// 将对象排到事件派发线程的队列中
            }
        }
    }

    private class Start implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            flag = true;
            if (t == null) {
                t = new GoThread();
                t.start(); // 调用go（）方法
            }
        }
    }

    class GoThread extends Thread {
        public void run() {
            // do something...
            go();
        }
    }

    private class End implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            flag = false;
        }
    }

    public static void main(String[] args) {
    	test_Jindutiao fg = new test_Jindutiao();
        fg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fg.setSize(300, 100);
        fg.setVisible(true);
    }
}