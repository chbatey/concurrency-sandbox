package chap1.philosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private Philosopher owner;
    private final int id;
    private final ReentrantLock lock = new ReentrantLock();

    public Fork(int i) {
        this.id = i;
    }

    public boolean take(Philosopher p) throws InterruptedException {
        System.out.println(String.format("%s after fork %s", p, id));
        boolean gotLock = lock.tryLock(100, TimeUnit.MILLISECONDS);
        try {
            if (gotLock) {
                if (owner == null) {
                    this.owner = p;
                    System.out.printf("%s got fork %s\n", p, id);
                    return true;
                }
            }
        } finally {
            if (gotLock) lock.unlock();
        }
        System.out.printf("%s unable to get fork%s giving up\n", p, id);
        return false;
    }

    public void giveup(Philosopher p) {
        System.out.printf("%s giving up fork %s\n", p, id);
        lock.lock();
        try {
            if (owner != p) throw new RuntimeException("Invariant broken: giving up a fork a philosopher does not own");
            System.out.printf("%s given up fork %s\n", p, id);
            owner = null;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public String toString() {
        return "Fork{" +
                " id=" + id +
                '}';
    }
}
