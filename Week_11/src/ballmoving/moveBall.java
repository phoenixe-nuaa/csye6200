package ballmoving;

import java.util.ArrayList;

public class moveBall extends Thread{
    int i=0;
    private ArrayList<Ball> list;//=new ArrayList<Ball>();
    public void setL( ArrayList <Ball> list) {
        this.list=list;
    }
    public void run() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true) {
            for(i=0;i<list.size();i++) {
                list.get(i).run();
                try{sleep(100);}
                catch(Exception ef) {};
            }
        }
    }
}
