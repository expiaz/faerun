package test.classes;

import jeu.Engine;
import jeu.Team;
import jeu.board.Board;
import jeu.classes.*;
import jeu.utils.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuerrierTest {

    private Guerrier guerrier;
    private ArrayList<Guerrier> guerriers;
    private Team t;

    GuerrierTest(){
        this.guerriers = new ArrayList<>();
        this.t = new Team("ABCD", 0);
        this.guerriers.add(new Elfe(t));
        this.guerriers.add(new Nain(t));
        this.guerriers.add(new ChefElfe(t));
        this.guerriers.add(new ChefNain(t));
    }

    /**
     * fight simulation between units
     */
    @Test
    void fight(){

        HashMap<Team, List<Guerrier>> base = new HashMap<>();
        Guerrier a,b;
        base.put(t = new Team("A", Board.RIGHT_SIDE), Arrays.asList(new Guerrier[]{a = new Nain(t)}));
        base.put(t = new Team("B", Board.LEFT_SIDE), Arrays.asList(new Guerrier[]{b = new Nain(t)}));

        Engine jeu = new Engine(base,1);

        jeu.training();
        while(!a.isDead() && !b.isDead())
            jeu.fight();

    }

    @BeforeEach
    void setUp() {
        this.guerrier = new Elfe(t);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isDead() {
        assertEquals(false, this.guerrier.isDead());
        ChefElfe e = new ChefElfe(t);
        while(this.guerrier.getPv() > 0)
            Logger.event(e.attack(this.guerrier));
        assertEquals(true, this.guerrier.isDead());
    }

    @Test
    void attack(){
        assertEquals(100, this.guerrier.getPv());
        Logger.event(new ChefElfe(t).attack(this.guerrier));
        assertEquals(true, this.guerrier.getPv() < 100);
    }

}