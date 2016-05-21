package chap1.philosophers;

public class Philosopher extends Thread {

    private final int id;
    private Fork left;
    private Fork right;
    private boolean eaten;

    public Philosopher(int i, Fork left, Fork right) {
        super("P" + i);
        id = i;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (!eaten) {
            try {
                boolean gotLeft = left.take(this);
                if (gotLeft) {
                    boolean gorRight = right.take(this);
                    if (gorRight) {
                        System.out.println(id + " Yum yum");
                        eaten = true;
                        left.giveup(this);
                        right.giveup(this);
                    } else {
                        System.out.printf("%s didn't get right fork, giving up left fork\n", this);
                        left.giveup(this);
                    }
                } else {
                    System.out.printf("%s failed to get left fork\n", this);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean eaten() {
        return eaten;
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
