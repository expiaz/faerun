package jeu.lifecycle;

import java.util.ArrayList;
import java.util.List;

public class Round extends Event {

    private List<Attack> attacks;
    private List<Death> deaths;

    public Round(){
        this.attacks = new ArrayList<>();
        this.deaths = new ArrayList<>();
    }

    public void addAttack(Attack a){
        this.attacks.add(a);
    }

    public void addDeath(Death d){
        this.deaths.add(d);
    }

    public String toString(){
        return super.toString() + " [" + this.attacks.size() + " atqs]" + "[" + this.deaths.size() + " deaths]";
    }

    public String log(){
        StringBuilder s = new StringBuilder("\n" + this.toString());
        for(Attack a : this.attacks)
            s.append(a.log());
        for(Death d : this.deaths)
            s.append(d.log());
        return s.toString();
    }
}
