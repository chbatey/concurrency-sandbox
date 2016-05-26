package chap1.pc;

public class Can {

    private boolean up = true;

    public synchronized void waitForDown() throws InterruptedException {
        if (up) {
            wait();
        }
    }

    public synchronized void putUp() {
        if (up) throw new RuntimeException("Putting up an up can");
        up = true;
        notify();
    }

    public synchronized void pullString() {
        if (!up) throw new RuntimeException("Putting down a down can");
        up = false;
        notify();
    }

    public synchronized void waitForUp() throws InterruptedException {
        if (!up) {
            wait();
        }
    }
}
