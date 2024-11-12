import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Player player = null;
            Game game = null;
            boolean gameRunning = true;
            
            while (gameRunning) {
                UI.startMenu();
                
                int choice = UI.getValidatedInput(scanner, 1, 4);
                
                switch (choice) {
                    case 1 -> {
                        UI.clearScreen();
                        System.out.print("Enter your name: ");
                        String playerName = scanner.nextLine();
                        player = new Player(playerName);
                        game = new Game(player);
                        game.start();
                    }
                    case 2 -> {
                        System.out.println("This method of playing is not implemented, will add in later variations");
                        UI.promptEnterKey(scanner);
                    }
                    case 3 -> {
                        UI.clearScreen();
                        System.out.println("Welcome to 5 minutes left. This game is a multi-ending senario with an actual time-limit of 5-minutes of completing\n"+
                                           "For you to navigate through this game, I really recommend checking this for your completion of the game.\n\n" +
                                           "First, you should do the following when you check the Act option. (1. of the options in the Player Action Menu)\n" +
                                           "Next, its important to check the inventory after you get the items. You are able to use the items, to frequently check them\n\n" +
                                           "Then, Check all the rooms because for getting the Good Ending you must go through all the rooms.\n" +
                                           "Finally, Have FUN!. I'll be adding more modifications to this program\n\n" +
                                           "From: Noah Matsukuma");
                        UI.promptEnterKey(scanner);
                    }
                    case 4 -> {
                        gameRunning = false;
                        System.out.println("Goodbye!");
                    }
                    default -> {
                        System.out.println("Invalid choice. Try again.");
                        UI.promptEnterKey(scanner);
                    }
                }
            }
        }
    }
}
