import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用四个子线程计算1~100
 * 每个线程计算25次
 * 主线程执行在子线程之后
 */
public class Main  {
    private static final AtomicInteger sum = new AtomicInteger(0);
    private static final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
    static class MyThread {
        public static void start(int begin, int step){
            Thread t = new Thread(()->{
                int localSum = 0;
                for (int i = begin;i<=100;i+=step){
                    localSum+=i;
                }
                queue.offer(localSum);
                System.out.println(localSum);
            });
            t.setDaemon(false);
            t.start();
        }
    }

    private static int MAGIC = 4;
    public static void main(String[] args) throws InterruptedException {
      test3();
    }
    public static void test3()throws InterruptedException{
        int sum = 0;
        Lock lock = new ReentrantLock();
        AtomicInteger count = new AtomicInteger(0);
        for(int i = 0 ; ;i++){
            MyThread.start(i,4);
            count.getAndIncrement();
            if(i==10){
                break;
            }
        }
        while(count.decrementAndGet()>0){
            Integer integer = null;
            while(queue.size()<=0){
                Thread.sleep(0);
            }
            lock.lock();
            integer = queue.take();
            lock.unlock();
            sum+=Integer.valueOf(integer);
        }
        System.out.println(sum);
    }

    public static void test2()throws InterruptedException{
        int sum = 0;
        for(int i = 0 ; i<MAGIC;i++){
            MyThread.start(i,4);
        }
        for(int i = 0 ; i< MAGIC;i++){
            sum+=Integer.valueOf(queue.take());
        }
        System.out.println(sum);
    }
    public static void test1()throws InterruptedException{
        Lock lock = new ReentrantLock();
        int sum = 0;
        for(int i = 0;i<MAGIC;i++){
            MyThread.start(i,4);
        }
        for(int i = 0;i<MAGIC;i++){
            Integer integer = null;
            while(queue.size()<=0){
                Thread.sleep(0);
            }
            lock.lock();
            integer = queue.take();
            lock.unlock();
            sum+=Integer.valueOf(integer);
        }
        System.out.println(sum);
    }
}
