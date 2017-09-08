package jeu;

import jeu.classes.*;
import jeu.lifecycle.Event;
import jeu.utils.Logger;

import java.util.LinkedList;
import java.util.List;

public class Team {

    public final static String RED = "RED";
    public final static String BLUE = "BLUE";

    private final String name;
    private final int side;
    private final Castle castle;
    private LinkedList<Guerrier> units;

    public Team(String name, int side){
        this.name = name;
        this.side = side;
        this.castle = new Castle(this);
        this.units = new LinkedList<>();
    }

    /**
     * set units to begin the game with (every unit will go to castle's training first)
     * @param units
     */
    public void setBaseUnits(List<Guerrier> units){
        for(Guerrier u : units)
            this.castle.enlist(u);
    }

    public String toString(){
        return this.name.toUpperCase();
    }

    /**
     * helper to test unit generation and autonomous game
     * @return
     */
    private Guerrier generateUnit(){
        switch (Logger.rand(1,4)){
            case 1:
                return new Nain(this);
            case 2:
                return new Elfe(this);
            case 3:
                return new ChefElfe(this);
            case 4:
            default:
                return new ChefNain(this);
        }
    }


    public int getSide(){
        return this.side;
    }

    /**
     * play team's next turn (eg castle's one)
     * @see Castle::nextTurn()
     * @return
     */
    public Event nextTurn(){
        //this.castle.enlist(this.generateUnit());
        return this.castle.nextTurn();
    }

    /**
     * does this team is allied with the other (known by name)
     * @param t
     * @return
     */
    public boolean isAlly(Team t){
        return this.toString().equals(t.toString());
    }

    /**
     * increment ressources by n for this team's Castle
     * @see Castle::ressources
     * @param n
     */
    public void addRessources(int n){
        this.castle.setRessources(this.castle.getRessources() + n);
    }

    /**
     * not used actually, maybe for future
     */

    public void removeUnit(Guerrier unit){
        this.units.remove(unit);
    }

    public LinkedList<Guerrier> getUnits(){
        return this.units;
    }



}
