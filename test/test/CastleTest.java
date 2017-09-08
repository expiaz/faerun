package test;

import jeu.Castle;
import jeu.Team;
import jeu.classes.Elfe;
import jeu.classes.Nain;
import jeu.utils.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CastleTest {

    private Castle castle;
    private Team team;

    CastleTest(){
        this.team = new Team("JOHN", 0);
        this.castle = new Castle(team);
    }

    @Test
    void SampleTraining(){
        Team t = this.team;

        castle.enlist(new Nain(t));
        castle.enlist(new Nain(t));
        castle.enlist(new Elfe(t));
        castle.enlist(new Elfe(t));


        while(castle.haveEnlisted()){
            Logger.event(castle.nextTurn());
            castle.setRessources(castle.getRessources() + 1);
        }
    }

}