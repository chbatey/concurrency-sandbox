package chap1.prisoners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Prison {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start the prisoner game!");

        // Pick a strategy up front

        // Switch room (register)

        // Each prisoner will visit the switch arbitarily often, at least N times

        // At any time you all need to say that you ahve visited the room

        // Solve knowing that the switch starts off

        Switch s = new Switch();
        int n = 100;
        int knowing = simulate(n, s, 1, n-1);
        // Solve not knowing the initial state
        /*
            Solve for N where

            Above solution won't work as the leader could miss if the light starts on a prisoner
            will turn it off and do it again and the leader will wait for ever waiting for n-1
            turn offs.

            The leader can miss at most 1 off so have each prisoner turn it off twice and wait for
            (2(n - 1) -1). Where (n-1) is his switch ons and the final -1 accounts for missing at most
            1 of the turns ons.
         */

        s.switchOn();
        int notKnowing = simulate(n, s, 2, 2*n-3);

        System.out.println("Knowing: " + knowing);
        System.out.println("Not knowing: " + notKnowing);
    }

    private static int simulate(int n, Switch s, int offs, int waits) {
        List<Prisoner> prisoners = new ArrayList<>();
        short[] in = new short[n];

        prisoners.add(new Leader(n, waits));
        for (int i = 1; i < n; i++) {
           prisoners.add(new Follower(offs, i));
        }

        int turns = 0;
        Random r = new Random();
        while (true) {
            turns++;
            int next = Math.abs(r.nextInt() % n);
            in[next] = 1;
//            System.out.println(next);
            Prisoner prisoner = prisoners.get(next);
            boolean declared = prisoner.enterRoom(s);
            if (declared) {
//                System.out.println("Prisoner has declared everyone has been in: " + prisoner);
                break;
            }
//            Thread.sleep(100);
//            System.out.println(Arrays.toString(in));
        }

        for (int i = 0; i < n; i++) {
            if (!(in[i] == 1)) throw new RuntimeException(i + " has not been in: BOOM!");
        }
        System.out.println("Success. Turns taken: " + turns);
        return turns;
    }

}
