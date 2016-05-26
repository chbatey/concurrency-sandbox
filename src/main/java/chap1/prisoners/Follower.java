package chap1.prisoners;

/**
 * A follower never decides if every prisoner has been in the room
 * and only turns off the light a designated numberof times.
 *
 * A follower never turns the light on.
 *
 */
public class Follower implements Prisoner {
    private int turnedOff = 0;
    private int maxTurnedOff;
    private int id;

    public Follower(int maxTurnedOff, int id) {
        this.maxTurnedOff = maxTurnedOff;
        this.id = id;
    }

    @Override
    public boolean enterRoom(Switch s) {
        if (turnedOff < maxTurnedOff) {
            System.out.println(id + " Turning light off");
            if (s.off()) {
                System.out.println(id + " turned the light off!");
                turnedOff++;
            } else {
                System.out.println(id + " light already off");
            }
        }

        return false;
    }
}
