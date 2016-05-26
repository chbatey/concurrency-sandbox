package chap1.prisoners;

public class Leader implements Prisoner {

    private int turnOns = 0;
    private final int numberOfPrisoners;

    public Leader(int numberOfPrisoners, int waits) {
        this.numberOfPrisoners = numberOfPrisoners;
    }

    @Override
    public boolean enterRoom(Switch s) {

        if (!s.switchOn()) {
            System.out.println("Turning light back on");
            turnOns++;
        } else {
            System.out.println("Light already off");
        }
        if (turnOns == numberOfPrisoners) {
            System.out.println("Had n-1 turn offs, all good.");
            return true;
        }
        System.out.println("Waiting for more turn offs");
        return false;
    }
}
