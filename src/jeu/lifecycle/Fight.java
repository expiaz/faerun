package jeu.lifecycle;

import java.util.ArrayList;
import java.util.List;

public class Fight extends Event{

    private List<Round> rounds;

    public Fight(){
        this.rounds = new ArrayList<>();
    }

    public void addRound(Round r){
        this.rounds.add(r);
    }

    public void addRounds(List<Round> rds){
        this.rounds.addAll(rds);
    }

    public List<Round> getRounds(){
        return this.rounds;
    }

    public String log(){
        if(this.rounds.size() == 0){
            return null;
        }
        StringBuilder s = new StringBuilder("\n" + this.toString());
        for(Round r : this.rounds)
            s.append(r.log());
        return s.toString();
    }

}
