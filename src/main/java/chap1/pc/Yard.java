package chap1.pc;

public class Yard {
    private volatile boolean pets;
    private volatile boolean humanInYard;

    public void releaseThePets() {
        System.out.println(Thread.currentThread().getName() + " Releasing the pets " + this );
        if (humanInYard) throw new RuntimeException("Invariant broken: Pets releasted to eat the human");
        this.pets = true;
    }

    public void petsComeHome() {
        System.out.println(Thread.currentThread().getName() + " Pets leaving the yard " + this);
        if (!pets) throw new RuntimeException("Invariant broken: Pets already home");
        this.pets = false;
    }

    public void enterTheYard() {
        humanInYard = true;
        System.out.println(Thread.currentThread().getName() + " Enter the yard " + this);
        if (pets) throw new RuntimeException("Invariant broken: Someone is getting eaten!");
    }

    public void leaveTheYard() {
        humanInYard = false;
        System.out.println(Thread.currentThread().getName() + " Leaving the yard " + this);
    }

    @Override
    public String toString() {
        return "Yard{" +
                "pets=" + pets +
                ", humanInYard=" + humanInYard +
                '}';
    }
}
