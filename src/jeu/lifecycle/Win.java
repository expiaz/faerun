package jeu.lifecycle;

import jeu.Team;

public class Win extends Event{

    private Team team;

    public Win(Team t){
        this.team = t;
    }

    public String toString(){
        return "[" + this.team + "]" + super.toString();
    }

}
