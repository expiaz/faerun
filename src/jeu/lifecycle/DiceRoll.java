package jeu.lifecycle;

import jeu.classes.Guerrier;
import jeu.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceRoll extends Event {

    private List<Integer> rolls;
    private int total;

    public DiceRoll(int nbRoll){
        this.rolls = new ArrayList<>(nbRoll);
        this.total = 0;

        int i = 0, current;

        for( ; i < nbRoll; i++){
            current = this.diceRoll();
            rolls.add(current);
            this.total += current;
        }
    }

    private int diceRoll(){
        return Logger.rand(1,3);
    }

    public String toString(){
        StringBuilder s = new StringBuilder("Jet de " + this.rolls.size() + " dés pour " + this.total + " :\n");
        for (int i : this.rolls){
            s.append(" " + i);
        }
        s.append("\n");
        return s.toString();
    }

    public List<Integer> getRolls(){
        return this.rolls;
    }

    public int getTotal(){
        return this.total;
    }

}
