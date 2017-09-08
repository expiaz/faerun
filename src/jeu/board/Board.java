package jeu.board;

import jeu.Team;
import jeu.lifecycle.*;
import jeu.utils.Logger;

import java.util.*;

public class Board {

    public static final int LEFT_SIDE = 0;
    public static final int RIGHT_SIDE = 1;

    private List<Team> teams;

    private Cell firstCell;
    private Cell lastCell;
    private int nbCells;

    /**
     *
     * @param teams teams for this game
     */
    public Board(List<Team> teams){
        this.teams = teams;
        this.nbCells = 0;

        int size = Logger.rand(1, 3) * 5;
        for(int i = 0; i < size; i++)
            this.addCell();
    }

    /**
     *
     * @param teams teams for this game
     */
    public Board(int length, List<Team> teams){
        this.teams = teams;
        this.nbCells = 0;

        for(int i = 0; i < length; i++)
            this.addCell();
    }

    /**
     * return a list of the cells of the board
     * @return
     */
    public List<Cell> getCells(){
        List<Cell> cells = new ArrayList<>();

        Cell actual = this.firstCell;
        do{
            cells.add(actual);
        }while((actual = actual.getNext()) != null);

        return cells;
    }

    /**
     * add a cell at the beginning of the list
     */
    public void addCellFirst(){
        if(this.firstCell == null){
            this.firstCell = this.lastCell = new Cell(null, null, ++nbCells, this.teams);
        } else {
            this.firstCell = new Cell(null, this.firstCell, ++nbCells, this.teams);
        }
    }

    /**
     * add a cell at the end of the list
     */
    public void addCellLast(){
        if(this.firstCell == null){
            this.firstCell = this.lastCell = new Cell(null, null, ++nbCells, this.teams);
        } else {
            this.lastCell = new Cell(this.lastCell, null, ++nbCells, this.teams);
        }
    }

    /**
     * basic method to add a cell (calls addCellLast under the hood)
     */
    public void addCell(){
        this.addCellLast();
    }


    /**
     * make this team train and add new units to the first cell of each team side
     * @param t
     * @return
     */
    public Training handleTraining(Team t){
        Training training = (Training) t.nextTurn();

        (training.getTeam().getSide() == RIGHT_SIDE
                ? this.lastCell
                : this.firstCell
        ).addTeamUnits(training.getTeam(), training.getTrained());

        return training;
    }

    /**
     * Make this team units move forward according to their goal cell
     * @param t
     * @return
     */
    public Move handleMove(Team t){

        (t.getSide() == RIGHT_SIDE
                ? this.lastCell
                : this.firstCell
        ).moveForward(t);

        return new Move();

    }

    /**
     * make this team fight every other on the cells needed
     * @param t
     * @return
     */
    public List<Event> handleFight(Team t){
        List<Event> fights = new ArrayList<>();
        for(Cell cell : this.getCells()){
            fights.add(cell.fight(t));
        }
        return fights;
    }

    /**
     * does this team won ? (public API)
     * @param t
     * @return
     */
    public Win handleWin(Team t){
        return this.hasWon(t) ? new Win(t) : null;
    }

    /**
     * does this team won ? (eg is the only one present on her goal cell)
     * @param t
     * @return
     */
    private boolean hasWon(Team t){
        Cell goal = t.getSide() == RIGHT_SIDE
                ? this.firstCell
                : this.lastCell;
        //if this team is the only one to have units on the goal cell
        return goal.getTeamUnits(t).size() > 0 && goal.getPresentTeams().size() == 1;
    }

    /**
     * draw each cell of the board
     */
    public void draw(){
        for(Cell c : this.getCells()) {
            Logger.log(c.toString());
        }
    }



}
