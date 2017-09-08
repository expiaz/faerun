package jeu.classes;

import jeu.Team;
import jeu.lifecycle.Attack;
import jeu.lifecycle.DiceRoll;

abstract public class Guerrier {

    protected static final int BASE_PV = 100;
    protected static final int BASE_PROTECTION = 1;
    protected static final int BASE_FORCE = 10;
    protected static final int BASE_COST = 1;

    private int pv;
    private Team team;

    public Guerrier(Team team){
        this.team = team;
        this.pv = BASE_PV;
    }

    /**
     * training cost
     * @return the cost of the unit in ressources
     */
    public int getCost(){
        return BASE_COST;
    }

    /**
     * current HP of the unit
     * @return current unit pv
     */
    public int getPv(){
        return this.pv;
    }

    public Team getTeam(){
        return this.team;
    }

    /**
     * current HP of the unit
     * @return current unit pv
     */
    protected int setPv(int pv){
        this.pv = pv;
        if(this.pv <= 0){
            this.die();
            return this.pv = 0;
        }
        return this.pv;
    }

    /**
     * current unit protection
     * @return
     */
    public int getProtection(){
        return BASE_PROTECTION;
    }

    /**
     * current number of rolls of dices for damage calculation
     * @return unit force
     */
    public int getForce(){
        return BASE_FORCE;
    }

    /**
     * is this unit dead ? (hp < 100)
     * @return
     */
    public boolean isDead(){
        return this.pv <= 0;
    }

    /**
     * remove the unit from the game
     */
    protected void die(){
        //this.team.removeUnit(this);
    }


    /**
     * reduce pv by the attack damage
     * @param montant
     * @return realDamages taken
     */
    protected int takeDamages(int montant) {
        int damages = montant / this.getProtection();
        this.setPv(this.getPv() - damages);
        return damages;
    }

    /**
     * call takeDamage on ennemi multiplied by damages (rolls of dice * this unit force)
     * @param victim
     * @return Attack a description of the attack
     */
    public Attack attack(Guerrier victim){
        DiceRoll roll = new DiceRoll(this.getForce());
        int damages = roll.getTotal();
        int finalDamages = victim.takeDamages(damages);

        return new Attack(this, victim, damages, finalDamages, roll);
    }


    public String toString(){
        return "[" + this.team + "]" + this.getClass().getSimpleName() + "[" + this.pv + "]";
    }


}
