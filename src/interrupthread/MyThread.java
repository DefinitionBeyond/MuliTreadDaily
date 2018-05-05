package interrupthread;

import org.junit.Test;

/**
 * 设置中断标志，线程会不会停止
 *
 * 事实是，设置中断标志是提醒该线程应该停止了
 * 是否停止还的看该线程本身决定是否停止
 *
 * @author liutao
 * @date 2018/5/4  19:45
 */
public class MyThread  extends Thread{
    @Override
    public void run() {
        super.run();
        for( int i =0;i<5000000;i++ ){
            System.out.println("i="+(i+1));
        }
    }
    @Test
    public void go(){
        try{
            MyThread thread = new MyThread();
            thread.start();
            thread.sleep(1000);
            thread.interrupt();//将调用者线程的中断状态设为true。
//            System.out.println(thread.currentThread().interrupted());
            System.out.println("线程是否被中断："+thread.interrupted());
            System.out.println("线程是否被中断："+thread.interrupted());
        }catch (Exception e){

        }
    }
    @Test
    public void go1(){
        Thread.currentThread().interrupt();
        System.out.println("线程是否被中断："+Thread.interrupted());
        System.out.println("线程是否被中断："+Thread.interrupted());
    }

    @Test
    public void go2(){
        try {
        MyThread thread = new MyThread();
        thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("线程是否被中断："+thread.isInterrupted());
            System.out.println("线程是否被中断："+thread.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
