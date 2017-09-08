package jeu.classes;

import jeu.Team;

public class ChefElfe extends Elfe {

    public ChefElfe(Team team){
        super(team);
    }

    @Override
    public int getForce() {
        return super.getForce() * 2;
    }

    @Override
    public int getCost() {
        return super.getCost() + 2;
    }
}
