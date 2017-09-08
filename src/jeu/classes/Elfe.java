package jeu.classes;

import jeu.Team;

public class Elfe extends Guerrier {

    private final int COST = 2;

    public Elfe(Team team){
        super(team);
    }

    @Override
    public int getForce() {
        return super.getForce() * 2;
    }

    @Override
    public int getCost() {
        return COST;
    }
}