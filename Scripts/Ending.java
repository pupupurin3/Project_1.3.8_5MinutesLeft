import java.util.Scanner;

public class Ending {

    public static void displayGoodEnding(Game game) {
        GameTimer.stopTimer();
        UI.clearScreen();
        System.out.println("GOOD ENDING: Redemption");
        System.out.println("You managed to overcome all obstacles and escaped the room successfully.");
        System.out.println("You feel a sense of accomplishment and relief as you step into the light.");
    }

    public static void displayBadEnding(Game game) {
        GameTimer.stopTimer();
        UI.clearScreen();
        System.out.println("...");
        System.out.println("... ...");
        System.out.println("... ... ... ...");
        UI.promptEnterKey(new Scanner(System.in));
        System.out.println("As you stop trying to escape, you feel the world around you fade to darkness.");
        System.out.println("You succumb to the overwhelming sense of despair as you close your eyes.");
        System.out.println("...");
        System.out.println("\n You hear sobbing in your last moments...");
        System.out.println("\nBAD END 1");
        UI.promptEnterKey(new Scanner(System.in));
    }

    public static void displayNeutralEnding(Game game) {
        GameTimer.stopTimer();
        UI.clearScreen();
        System.out.println("...");
        System.out.println("... ...");
        System.out.println("... ... ... ...");
        UI.promptEnterKey(new Scanner(System.in));
        System.out.println("You try your best to find the exit, but it was too late");
        System.out.println("You lose your breath, and your vision fades away");
        System.out.println("...");
        System.out.println("\nYou hear sobbing in your last moments...");
        System.out.println("\nBAD END 2");
        UI.promptEnterKey(new Scanner(System.in));
    }
}
