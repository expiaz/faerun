package jeu.classes;

import jeu.Team;

public class ChefNain extends Nain {

    public ChefNain(Team team){
        super(team);
    }

    @Override
    public int getProtection() {
        return super.getProtection() * 2;
    }

    @Override
    public int getCost() {
        return super.getCost() + 2;
    }
}
