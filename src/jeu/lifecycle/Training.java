package jeu.lifecycle;

import jeu.Team;
import jeu.classes.Guerrier;

import java.util.List;
import java.util.Queue;

public class Training extends Event {

    private List<Guerrier> trained;
    private Queue<Guerrier> queue;
    private int ressources;
    private Team team;

    public Training(Team team, List<Guerrier> trained, Queue<Guerrier> queue, int ressources){
        this.trained = trained;
        this.queue = queue;
        this.ressources = ressources;
        this.team = team;
    }

    public List<Guerrier> getTrained() {
        return trained;
    }

    public Queue<Guerrier> getQueue() {
        return queue;
    }

    public int getRessources() {
        return ressources;
    }

    public Team getTeam(){
        return this.team;
    }

    public String toString(){
        return "[" + this.team + "]" + super.toString();
    }

    @Override
    public String log() {
        StringBuilder log = new StringBuilder(super.log());
        for(Guerrier g : trained) {
            log.append("\n" + g + " trained");
        }
        log.append(".\n" + this.queue + " left to train");
        log.append("\n" + ressources + " ressources left");

        return log.toString();
    }



}
