import java.util.Scanner;

public class UI {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int getValidatedInput(Scanner scanner, int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            }
        }
        return choice;
    }

    public static void promptEnterKey(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
        clearScreen();
    }

    public static void displayMenu(Player player) {
        int maxHp = 10;
    
        System.out.println("________________________________________________________________________");
        System.out.println("Player :  " + player.getName());
        System.out.println("HP :  " + player.getHp() + " / " + maxHp + " " + generateHpBar(player.getHp(), maxHp));
        System.out.println("\n\n\n________________________________________________________________________");
        System.out.println("Select Option :");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "1.)  Act", "2.)  Items", "3.)  Log", "4.)  Settings", "5.)  Give Up");
        System.out.println("________________________________________________________________________");
    }

    public static void startMenu() {
        clearScreen();
        System.out.println("________________________________________________________________");
        System.out.println("\n" +
            ",-----.              ,--.                  ,--.                      \r\n" +
            "|  .--'    ,--,--,--.`--',--,--, ,--.,--.,-'  '-. ,---.  ,---.       \r\n" +
            "'--'`|    |        |,--.|      ||  ||  |'-.  .-'| .-. :(  .-'       \r\n" +
            ".--'  |    |  |  |  ||  ||  ||  |'  ''  '  |  |  |   --..-'  `)      \r\n" +
            "`----'     `--`--`--'`--'`--''--' `----'   `--'   `----'`----'       \r\n" +
            ",--.       ,---.  ,--.                                               \r\n" +                                        
            "|  |,---. /  .-',-'  '-.                                             \r\n" +
            "|  | .-. :|  `-,'-.  .-'                                             \r\n" +
            "|  |   --.|  .-'  |  |                                               \r\n" +
            "`--'`----'`--'    `--'                                               \r\n\n");                                            
                                                                                
        System.out.println("1.) Start Game");
        System.out.println("2.) Continue Game");
        System.out.println("3.) How to play");
        System.out.println("4.) Quit");
        System.out.println("________________________________________________________________");
    }

    private static String generateHpBar(int hp, int maxHp) {
        int barLength = maxHp;  // Length of the HP bar
        int hpBars = (int) ((double) hp / maxHp * barLength);
        StringBuilder hpBar = new StringBuilder("[");
    
        for (int i = 0; i < barLength; i++) {
            if (i < barLength - hpBars) {
                hpBar.append("-");  // Represents missing HP with a space
            } else {
                hpBar.append("#");  // Represents current HP using '='
            }
        }
        hpBar.append("]");
        return hpBar.toString();
    }
}
