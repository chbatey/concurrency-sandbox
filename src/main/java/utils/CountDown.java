package utils;

import java.util.concurrent.CountDownLatch;

public class CountDown {
    public static void main(String[] args) {
        int n = 5
        final CountDownLatch cdl = new CountDownLatch(n);

        new Thread() {
            @Override
            public void run() {
                cdl.countDown();
                try {
                    cdl.await();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }

            }
        }.start();
    }


}
