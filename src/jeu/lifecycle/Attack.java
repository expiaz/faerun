package jeu.lifecycle;

import jeu.classes.Guerrier;
import jeu.utils.Logger;

public class Attack extends Event {

    private Guerrier attacker;
    private Guerrier victim;
    private DiceRoll roll;

    private int damages;
    private int finalDamages;

    public Attack(Guerrier attacker, Guerrier victim, int damages, int finalDamages, DiceRoll roll){

        this.attacker = attacker;
        this.victim = victim;
        this.damages = damages;
        this.finalDamages = finalDamages;
        this.roll = roll;
    }


    public Guerrier getAttacker() {
        return attacker;
    }

    public Guerrier getVictim() {
        return victim;
    }

    public DiceRoll getRoll() {
        return roll;
    }

    public int getDamages() {
        return damages;
    }

    public int getFinalDamages() {
        return finalDamages;
    }

    public String toString(){
        return super.toString() + " " + attacker + " hit " + victim + " for " + this.getFinalDamages() + " with " + this.getRoll().getRolls().size() + " dices";
    }

}
