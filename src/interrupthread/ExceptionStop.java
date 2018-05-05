package interrupthread;

import org.junit.Test;

/**
 * @author liutao
 * @date 2018/5/4  21:00
 */
public class ExceptionStop extends Thread {

    /**
     * 测试在线程被中断的时候
     * 循环外的语句是否执行
     *
     * 结果是执行，且for不会再执行并且退出
     */
    @Override
    public void run() {
        super.run();
        for(int i =0  ; i< 50000000 ;i++){
            if(this.interrupted()){
                System.out.println("被中断：");
                break;
            }
            System.out.println("i="+(i+1));
        }
        System.out.println("输出，for继续，线程没有停止");
    }

    /**
     *  遇到中断标志，直接break了
     *  跳出循环
     */
//    @Override
//    public void run() {
//        super.run();
//        for (int i = 0; i < 50000000; i++) {
//            if (this.interrupted()) {
//                System.out.println("被中断：");
//                break;
//            }
//            System.out.println("i=" + (i + 1));
//        }
//    }

    /**
     * try到异常时，线程中断，进入catch代码块
     */
//    @Override
//    public void run() {
//        super.run();
//        try {
//            for (int i = 0; i < 50000000; i++) {
//                if (this.interrupted()) {
//                    System.out.println("被中断：");
//                    throw new InterruptedException();
//                }
//                System.out.println("i=" + (i + 1));
//            }
//            System.out.println("OK");
//        } catch (InterruptedException e) {
//            System.out.println("catch");
//            e.printStackTrace();
//        }
//    }

    @Test
    public void go() {
        ExceptionStop thrread = new ExceptionStop();
        try {
            thrread.start();
            Thread.sleep(1000);
            thrread.interrupt();
        } catch (Exception e) {

        }
    }
}
