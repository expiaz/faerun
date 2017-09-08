package jeu;

import jeu.classes.Guerrier;
import jeu.lifecycle.Event;
import jeu.lifecycle.Training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Castle {

    private Team team;
    private int ressources;
    private Queue<Guerrier> queue;

    public Castle(Team team){
        this.team = team;
        this.ressources = 3;
        this.queue = new LinkedList<>();
    }

    /**
     * add the guerrier to training
     * @param guerrier
     * @return
     */
    public boolean enlist(Guerrier guerrier){
        return queue.add(guerrier);
    }

    /**
     * play Castle's next actions for the turn (eg train)
     * @return
     */
    public Event nextTurn(){
        return this.train();
    }

    /**
     * does the castle have units to train
     * @return
     */
    public boolean haveEnlisted(){
        return ! this.queue.isEmpty();
    }

    /**
     * train units according to ressources
     * @return
     */
    private Training train(){

        List<Guerrier> trained = new ArrayList<>();

        Guerrier toTrain;
        while(this.haveEnlisted() && (toTrain = queue.peek()).getCost() <= this.getRessources()){
            ressources -= toTrain.getCost();
            trained.add(queue.poll());
        }

        Training training = new Training(this.team, trained, this.queue, this.ressources);

        return training;
    }

    public int getRessources() {
        return ressources;
    }

    public void setRessources(int ressources) {
        this.ressources = ressources;
    }
}
