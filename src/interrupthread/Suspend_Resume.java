package interrupthread;

import org.junit.Test;

/**
 * @author liutao
 * @date 2018/5/4  22:59
 */
public class Suspend_Resume extends Thread {
    private long  i =0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        while(true){
            i++;
        }
    }
    @Test
    public void go(){
        Suspend_Resume thread = new Suspend_Resume();
        try {
            thread.start();
            Thread.sleep(4000);
            thread.suspend();

            thread.suspend();
            System.out.println("timeA= "+System.currentTimeMillis()+": i= "+thread.getI());
            Thread.sleep(4000);
            System.out.println("timeA= "+System.currentTimeMillis()+": i= "+thread.getI());
            thread.resume();
            System.out.println("timeA= "+System.currentTimeMillis()+": i= "+thread.getI());

            thread.suspend();
            Thread.sleep(4000);

            System.out.println("timeB= "+System.currentTimeMillis()+": i= "+thread.getI());
            Thread.sleep(4000);
            System.out.println("timeB= "+System.currentTimeMillis()+": i= "+thread.getI());
//            thread.resume();
//            System.out.println("timeB= "+System.currentTimeMillis()+": i= "+thread.getI());



        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
