package interrupthread;

import org.junit.Test;

/**
 * @author liutao
 * @date 2018/5/4  21:24
 */
public class UseStop extends  Thread {
    @Override
    public void run() {
        super.run();
        for( int i =0;i<5000000;i++ ){
            System.out.println("i="+(i+1));
        }
    }

    @Test
    public void go(){
        UseStop thread = new UseStop();
        try {
            thread.start();
            Thread.sleep(8000);
            thread.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
