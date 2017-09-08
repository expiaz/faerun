package jeu.classes;

import jeu.Team;

public class Nain extends Guerrier {

    public Nain(Team team){
        super(team);
    }

    @Override
    public int getProtection() {
        return super.getProtection() * 2;
    }
}