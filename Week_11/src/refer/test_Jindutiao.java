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

    private GoThread t = null; // ����һ���߳�

    private Runnable run = null;// ����������߳�

    public test_Jindutiao() {
        this.setLayout(new FlowLayout());
        add(progressBar);
        text.setEditable(false);
        add(text);
        add(start);
        add(end);
        start.addActionListener(new Start());
        end.addActionListener(new End());

        run = new Runnable() {// ʵ��������������߳�
        public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("threadA");
                progressBar.setValue(count);  // ���ý�����
                text.setText(STR + String.valueOf(count) + "%"); // �ұ���ʾ�ı��� �����ֱ仯��
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
            if (flag) {   // flagΪtrue
                count++;
                System.out.println(Thread.currentThread().getName());
                System.out.println("threadB");
                SwingUtilities.invokeLater(run);// �������ŵ��¼��ɷ��̵߳Ķ�����
            }
        }
    }

    private class Start implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            flag = true;
            if (t == null) {
                t = new GoThread();
                t.start(); // ����go��������
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