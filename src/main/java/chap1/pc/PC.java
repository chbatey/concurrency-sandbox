package chap1.pc;

public class PC {
    public static void main(String[] args) throws InterruptedException {
        // First solve using the same can that Alice and Bob can see
        int iterations = 100;
        Can can = new Can();
        Yard yard = new Yard();
        Alice a = new Alice(can, yard, iterations);
        Bob b = new Bob(can, yard, iterations);

        a.start();
        b.start();

        a.join();
        b.join();

        // Then solve with two cans assuming Bob can not see Alice's can

        /*
            two cans for interrupts
         */
    }
}
