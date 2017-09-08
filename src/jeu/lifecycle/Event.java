package jeu.lifecycle;

import jeu.utils.Loggable;

public abstract class Event implements Loggable {

    public String toString(){
        return "[" + this.getClass().getSimpleName().toUpperCase() + "]";
    }

    @Override
    public String log() {
        return "\n" + this.toString();
    }

}
