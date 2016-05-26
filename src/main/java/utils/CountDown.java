package utils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class CountDown {
    public static void main(String[] args) throws Exception {
        int n = 5;
        final CountDownLatch cdl = new CountDownLatch(n);
        Set<Thread> threads = new HashSet<>();

        for (int i  = 0; i < n; i++) {
            threads.add(new Thread("Thread " + i) {
                @Override
                public void run() {
                    cdl.countDown();
                    try {
                        System.out.println(Thread.currentThread() + " waiting to go!");
                        cdl.await();
                        System.out.println(Thread.currentThread() + " go go go !");
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread() + " gone gone gone");
                    } catch (InterruptedException e) {

                    }

                }
            });
        }

        System.out.println("Starting threads");
        threads.forEach(Thread::start);
        System.out.println("All threads started");
        threads.forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Finished joining threads");
    }


}
