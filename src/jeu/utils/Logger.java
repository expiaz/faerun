package jeu.utils;

import jeu.Team;
import jeu.classes.*;
import jeu.lifecycle.Event;

import java.io.IOException;
import java.util.*;

public class Logger {

    private static Scanner input = new Scanner(System.in);

    private static final Random random = new Random();

    public static final String NEXT_TURN = "TOUR SUIVANT";

    private static List<String> history = new ArrayList<>();


    public static void clear() {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void waitPlayerInput(String msg){
        System.out.print(msg);
        input.nextLine();
        System.out.println();
    }

    public static void announce(String s){
        String[] msg = new String[5];
        msg[1] = msg[3] = "*" + String.join("", Collections.nCopies(78, " ")) + "*";
        msg[0] = msg[4] = String.join("", Collections.nCopies(80, "*"));
        msg[2] = "*" + String.join("", Collections.nCopies((78 - s.length()) / 2, " ")) + s + String.join("", Collections.nCopies((78 - (s.length()%2 == 0 ? s.length() : (s.length() - 1)) ) / 2, " ")) + "*";
        Logger.log("\n\n" + String.join("\n", msg) + "\n\n");
    }

    public static void log(String s){
        Logger.history.add(s);
        System.out.println(s);
    }

    public static void event(Event event){
        String s = event.log();
        if(s != null) Logger.log(s);
    }

    public static List<Guerrier> getRdmUnits(Team t, int l){
        List<Guerrier> g = new ArrayList<>(l);
        for(int i = 0; i < l; i++)
            g.add(getRdmUnit(t));
        return g;
    }

    public static Guerrier getRdmUnit(Team t){
        return new Guerrier[]{new Nain(t), new Elfe(t), new ChefElfe(t), new ChefNain(t)}[rand(0,3)];
    }

    public static int rand(int min, int max){
        return random.nextInt(max) + min;
    }

}
