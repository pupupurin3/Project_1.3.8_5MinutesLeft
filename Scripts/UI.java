/*+----------------------------------------------------------------------
 ||
 ||  Class: UI 
 ||
 ||         Author:  Noah Matsukuma
 ||
 ||        Purpose:  This class provides user interface methods for the game,
 ||                  including displaying menus, validating input, and managing
 ||                  screen updates.
 ||
 ||  Inherits From:  None
 ||
 ||     Interfaces:  None
 ||
 |+-----------------------------------------------------------------------
 ||
 ||      Constants:  None
 ||
 |+-----------------------------------------------------------------------
 ||
 ||   Constructors:  None
 ||
 ||  Class Methods:  None
 ||
 ||  Inst. Methods:  
 ||                  static void clearScreen()
 ||                      - Clears the console screen.
 ||
 ||                  static int getValidatedInput(Scanner scanner, int min, int max)
 ||                      - Prompts the user for input and validates that it is within the specified range.
 ||
 ||                  static void promptEnterKey(Scanner scanner)
 ||                      - Prompts the user to press Enter to continue.
 ||
 ||                  static void displayMenu(Player player)
 ||                      - Displays the main game menu with the player's current status.
 ||
 ||                  static void startMenu()
 ||                      - Displays the start menu with game options.
 ||
 ||                  private static String generateHpBar(int hp, int maxHp)
 ||                      - Generates a visual representation of the player's HP bar.
 ||
 ++-----------------------------------------------------------------------*/


import java.util.Scanner;

public class UI {

    /*---------------------------------------------------------------------
    |  Method clearScreen
    |
    |  Purpose:  Clears the console screen to provide a clean slate for the
    |            next set of outputs.
    |
    |  Pre-condition:  The console must be capable of interpreting the clear
    |                  screen command.
    |
    |  Post-condition: The console screen is cleared of previous outputs.
    |
    |  Parameters:  None
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /*---------------------------------------------------------------------
    |  Method getValidatedInput
    |
    |  Purpose:  Prompts the user for input and validates that it is within the
    |            specified range. If the input is invalid, the user is prompted
    |            to try again until a valid input is provided.
    |
    |  Pre-condition:  The Scanner object must be initialized. The min and max
    |                  parameters define the valid range for input.
    |
    |  Post-condition: A valid input within the specified range is returned.
    |
    |  Parameters:  
    |      scanner -- Scanner object for reading user input.
    |      min -- The minimum valid value for the input.
    |      max -- The maximum valid value for the input.
    |
    |  Returns:  int -- The validated input value within the specified range.
    *-------------------------------------------------------------------*/
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

    /*---------------------------------------------------------------------
    |  Method promptEnterKey
    |
    |  Purpose:  Prompts the user to press Enter to continue, effectively pausing
    |            the program until the user is ready to proceed.
    |
    |  Pre-condition:  The Scanner object must be initialized.
    |
    |  Post-condition: The program waits for the user to press Enter before continuing.
    |
    |  Parameters:  
    |      scanner -- Scanner object for reading user input.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/    
    public static void promptEnterKey(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
        clearScreen();
    }

    /*---------------------------------------------------------------------
    |  Method displayMenu
    |
    |  Purpose:  Displays the main game menu with the player's current status, including
    |            the player's name, HP, and available actions.
    |
    |  Pre-condition:  The player object must be initialized with a name and HP.
    |
    |  Post-condition: The main game menu is displayed to the user.
    |
    |  Parameters:  
    |      player -- The player object containing the player's current status.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/    
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

    /*---------------------------------------------------------------------
    |  Method startMenu
    |
    |  Purpose:  Displays the start menu with game options, such as starting a new game,
    |            continuing a game, viewing the how-to-play instructions, or quitting.
    |
    |  Pre-condition:  None
    |
    |  Post-condition: The start menu is displayed to the user.
    |
    |  Parameters:  None
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/    
    public static void startMenu() {
        clearScreen();
        System.out.println("________________________________________________________________");
        System.out.println("\n" +
            ",-----.              ,--.                  ,--.                      \r\n" +
            "|  .--'    ,--,--,--.`--',--,--, ,--.,--.,-'  '-. ,---.  ,---.       \r\n" +
            "'--'` |    |        |,--.|      ||  ||  |'-.  .-'| .-. :(  .-'       \r\n" +
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

    /*---------------------------------------------------------------------
    |  Method generateHpBar
    |
    |  Purpose:  Generates a visual representation of the player's HP bar, indicating
    |            the current HP relative to the maximum HP.
    |
    |  Pre-condition:  The hp and maxHp parameters must be positive integers.
    |
    |  Post-condition: A string representing the HP bar is returned.
    |
    |  Parameters:  
    |      hp -- The player's current health points.
    |      maxHp -- The player's maximum health points.
    |
    |  Returns:  String -- The visual representation of the player's HP bar.
    *-------------------------------------------------------------------*/    
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
