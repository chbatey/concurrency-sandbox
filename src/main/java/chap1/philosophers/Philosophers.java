package chap1.philosophers;

/**
 * P -f- P -f- P -f- P -f- P -f-
 */
public class Philosophers {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Get going");
        int n = 10;

        Philosopher[] philosophers = new Philosopher[n];
        Fork[] forks = new Fork[n];

        for (int i = 0; i < n; i++) {
            forks[i] = new Fork(i);
        }
        for (int i = 0; i < n; i++) {
            int left = i == 0 ? (n-1) : i-1;
            philosophers[i] = new Philosopher(i, forks[left], forks[i]);
        }

        System.out.println("Get eating");
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

        System.out.println("Waiting for the dinner to finish");
        for (Philosopher philosopher : philosophers) {
            philosopher.join();
        }

        int eaten = 0;
        for (Philosopher philosopher : philosophers) {
            if (philosopher.eaten()) eaten++;
        }
        System.out.println("Finished eating: " + eaten);
    }
}
