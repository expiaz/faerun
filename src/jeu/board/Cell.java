package jeu.board;

import jeu.Team;
import jeu.classes.Guerrier;
import jeu.lifecycle.Death;
import jeu.lifecycle.Fight;
import jeu.lifecycle.Round;

import java.util.*;

public class Cell {

    private Map<Team, List<Guerrier>> teamUnits;

    private Cell previous;
    private Cell next;
    private int number;

    /**
     *
     * @param previous the previous cell
     * @param next the next cell
     * @param number cell number for the board
     * @param teams teams for this game
     */
    public Cell(Cell previous, Cell next, int number, List<Team> teams){
        this.teamUnits = new HashMap<>();
        this.previous = previous;
        this.next = next;
        this.number = number;

        if(previous != null)
            this.previous.setNext(this);

        if(next != null)
            this.next.setPrevious(this);

        for(Team t : teams)
            this.setTeamUnits(t, new ArrayList<>());
    }


    /**
     * replace current team units by the ones provided
     * @param t the team to which to units will be sets
     * @param units the units to set
     */
    public void setTeamUnits(Team t, List<Guerrier> units) {
        this.teamUnits.put(t, units);
    }

    /**
     * add provided units to current team units
     * @param t the team to which to units will be sets
     * @param units the units to set
     */
    public void addTeamUnits(Team t, List<Guerrier> units){
        this.getTeamUnits(t).addAll(units);
    }

    /**
     * retrieve the units for a team
     * @param t the team to which retrieve units
     * @return
     */
    public List<Guerrier> getTeamUnits(Team t){
        return this.teamUnits.get(t);
    }

    /**
     * retrieve teams that have one or more units on the present cell
     * @return
     */
    public List<Team> getPresentTeams(){

        List<Team> teams = new ArrayList<>();

        for(Map.Entry<Team, List<Guerrier>> entry : this.teamUnits.entrySet()){
            if(entry.getValue().size() > 0){
                teams.add(entry.getKey());
            }
        }

        return teams;

    }


    /**
     * make every unit of this team fight other teams
     * @param attackerTeam
     * @return a log of the fight
     */
    public Fight fight(Team attackerTeam){

        Iterator<Guerrier> iterator;
        List<Guerrier> attackers, victims;
        Guerrier victim;
        Fight log = new Fight();
        Round round;


        //each warrior in the current team fight other teams
        for(Guerrier attacker : this.teamUnits.get(attackerTeam)){

            //each other team than the current
            for(Team victimTeam : this.getPresentTeams()){

                //other team than current
                if(! victimTeam.isAlly(attackerTeam)){

                    victims = this.getTeamUnits(victimTeam);

                    round = new Round();

                    //each warrior in victim's team
                    iterator = victims.iterator();
                    while(iterator.hasNext()){

                        victim = iterator.next();

                        round.addAttack(attacker.attack(victim));

                        if(victim.isDead()){
                            round.addDeath(new Death(victim));
                            iterator.remove();
                        }

                    }

                    log.addRound(round);

                }

            }

        }

        return log;

    }

    /**
     * make every unit of this cell fight other teams
     * @return a log of the fights
     */
    public Fight fight(){

        Fight logAll = new Fight();

        //each team fight
        for(Team attackerTeam : this.getPresentTeams()){
            logAll.addRounds(this.fight(attackerTeam).getRounds());
        }

        return logAll;

    }


    /**
     * does this cell is the first (spawn) according to the side of the team ?
     * @param side @see Board.[RIGHT|LEFT]_SIDE
     * @return
     */
    public boolean isFirstCell(int side){
        return side == Board.RIGHT_SIDE
                ? this.next == null
                : this.previous == null;
    }

    /**
     * does this cell is the last (goal) according to the side of the team ?
     * @param side
     * @return
     */
    public boolean isLastCell(int side){
        return side == Board.RIGHT_SIDE
                ? this.previous == null
                : this.next == null;
    }


    public void setPrevious(Cell previous){
        this.previous = previous;
    }

    public void setNext(Cell next){
        this.next = next;
    }


    public Cell getPrevious(){
        return this.previous;
    }

    public Cell getNext(){
        return this.next;
    }


    /**
     * every units of the team will move forward, beginning with the last cell for this team (linked calls between cells)
     * @param t
     */
    public void moveForward(Team t){
        int side = t.getSide();

        if(this.isLastCell(side)){
            return;
        }

        Cell cible = side == Board.RIGHT_SIDE
                ? this.previous
                : this.next;

        cible.moveForward(t);

        List<Guerrier> tu = this.getTeamUnits(t);
        if(this.getPresentTeams().size() < 2 && tu.size() > 0){
            //this cell won't fight so move these team units forward
            /*System.out.println("\n" + t + " FORWARD");
            System.out.println(this);
            System.out.println("to");
            System.out.println(cible);*/

            cible.addTeamUnits(t, tu);
            tu.clear();
        }




    }

    public String toString(){
        StringBuilder s = new StringBuilder(this.getClass().getSimpleName() + " N° " + this.number);
        for(Team t : this.getPresentTeams()){
            s.append("\n\t" + t);
            for(Guerrier g : this.getTeamUnits(t))
                s.append("\n\t\t" + g);
        }

        return s.toString();
    }

}
