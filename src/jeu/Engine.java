package jeu;

import jeu.board.Board;
import jeu.board.Cell;
import jeu.classes.*;
import jeu.lifecycle.Win;
import jeu.utils.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class Engine {

    private List<Team> players;
    private Board game;

    /**
     * a little game ?
     * @param args no need
     */
    public static void main(String[] args){

        HashMap<Team, List<Guerrier>> base = new HashMap<>();
        Team t;
        base.put(t = new Team("JOHN", Board.RIGHT_SIDE), Logger.getRdmUnits(t, Logger.rand(3,6)));
        base.put(t = new Team("BABAR", Board.LEFT_SIDE), Arrays.asList(new Guerrier[]{new Nain(t), new Nain(t), new Nain(t), new Nain(t)}));
        //base.put(t = new Team("BOB", Board.LEFT_SIDE), Arrays.asList(new Guerrier[]{new ChefNain(t), new ChefElfe(t), new Elfe(t)}));
        Engine jeu = new Engine(base, 5);

        while(! jeu.win()){
            jeu.nextTurn();
        }
    }

    /**
     *
     * @param numberOfPlayers
     */
    public Engine(int numberOfPlayers){

        this.players = new ArrayList<>(numberOfPlayers);
        Team t;

        for(int i = 0; i < numberOfPlayers; ++i) {
            this.players.add(t = new Team(i%2 == 0 ? Team.BLUE : Team.RED, i%2));
            t.setBaseUnits(Logger.getRdmUnits(t, Logger.rand(3,6)));
        }

        this.game = new Board(this.players);
    }

    /**
     *
     * @param numberOfPlayers
     */
    public Engine(int numberOfPlayers, int lengthOfBoard){
        this(numberOfPlayers);

        this.game = new Board(lengthOfBoard, this.players);
    }

    /**
     *
     * @param players the teams and their base army
     */
    public Engine(Map<Team, List<Guerrier>> players) {
        this.players = players.keySet().stream().collect(Collectors.toList());
        for(Team t : this.players){
            t.setBaseUnits(players.get(t));
        }
        this.game = new Board(this.players);
    }

    /**
     *
     * @param players the teams and their base army
     */
    public Engine(Map<Team, List<Guerrier>> players, int lengthOfBoard) {
        this(players);

        this.game = new Board(lengthOfBoard, this.players);
    }

    /**
     * draw the board
     */
    public void draw(){
        this.game.draw();
    }

    /**
     * play game's next Turn (eg train -> move -> fight -> win ?)
     */
    public void nextTurn(){
        //Logger.clear();
        Logger.announce("NEXT TURN");
        Logger.waitPlayerInput("Enter for continue ...");
        Logger.announce("train");
        this.training();
        this.move();
        Logger.announce("fight");
        Logger.waitPlayerInput("Enter for continue ...");
        this.fight();
        Logger.announce("board");
        Logger.waitPlayerInput("Enter for continue ...");
        this.draw();
    }

    /**
     * play train phase for every team
     */
    public void training(){
        for(Team t : this.players) {
            Logger.event(game.handleTraining(t));
            t.addRessources(1);
        }
    }

    /**
     * play move phase for every team
     */
    public void move(){
        for(Team t : this.players)
            game.handleMove(t);
    }

    /**
     * play fight phase for every team
     */
    public void fight(){
        List<Cell> cells = game.getCells();

        for(Cell cell : cells)
            Logger.event(cell.fight());
    }

    /**
     * does a team won ?
     * @return
     */
    public boolean win(){
        Win w;
        for(Team t : this.players)
            if((w = game.handleWin(t)) != null) {
                Logger.event(w);
                return true;
            }
        return false;
    }

}
