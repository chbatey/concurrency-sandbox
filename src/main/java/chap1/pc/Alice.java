package chap1.pc;

public class Alice extends Thread {
    private Can can;
    private Yard yard;
    private int iterations;

    public Alice(Can can, Yard yard, int iterations) {
        super("Alice");
        this.can = can;
        this.yard = yard;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            // wait for can to be down
            try {
                can.waitForDown(); // consume
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // release the pets
            yard.releaseThePets();


            // wait for pets to return
            yard.petsComeHome();

            // sets the can up
            can.putUp();
        }
    }
}
