package chap1.pc;

public class Bob extends Thread {
    private Can can;
    private Yard yard;
    private int iterations;

    public Bob(Can can, Yard yard, int iterations) {
        super("Bob");
        this.can = can;
        this.yard = yard;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            // waits for the can to be up
            try {
                can.waitForUp();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // puts food in the yard
            yard.enterTheYard();

            // leaves the yard
            yard.leaveTheYard();

            // sets the can down
            can.pullString();
        }
    }
}
