package jeu.lifecycle;

import jeu.classes.Guerrier;

public class Death extends Event {

    private Guerrier unit;

    public Death(Guerrier unit){
        this.unit = unit;
    }

    public String toString(){
        return "[" + this.unit + "]" + super.toString();
    }

}
