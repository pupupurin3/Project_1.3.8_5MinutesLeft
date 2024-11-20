/*+----------------------------------------------------------------------
 ||
 ||  Class: Player 
 ||
 ||         Author:  Noah Matsukuma
 ||
 ||        Purpose:  This class represents the player in the game. It manages
 ||                  the player's name, health points (HP), inventory of items,
 ||                  and activity log. The class provides methods to interact
 ||                  with items, display inventory and log, and manage player's HP.
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
 ||   Constructors:  
 ||                  Player(String name)
 ||                      - Initializes the player with a name, default HP of 10, 
 ||                        and empty lists for items and log.
 ||
 ||  Class Methods:  None
 ||
 ||  Inst. Methods:  
 ||                  String getName()
 ||                      - Returns the player's name.
 ||
 ||                  int getHp()
 ||                      - Returns the player's current HP.
 ||
 ||                  void addHp(int amount)
 ||                      - Adds the specified amount to the player's HP.
 ||
 ||                  void addItem(Item item)
 ||                      - Adds an item to the player's inventory and logs the action.
 ||
 ||                  List<Item> getItems()
 ||                      - Returns the list of items in the player's inventory.
 ||
 ||                  List<String> getLog()
 ||                      - Returns the player's activity log.
 ||
 ||                  void addToLog(String entry)
 ||                      - Adds an entry to the player's activity log.
 ||
 ||                  void displayItems(Scanner scanner, Room currentRoom, Game game)
 ||                      - Displays the player's inventory, allowing the player to select and use items.
 ||
 ||                  void displayLog(Scanner scanner)
 ||                      - Displays the player's activity log.
 ||
 ||                  String toString()
 ||                      - Returns a string representation of the player.
 ||
 ||                  private void displayItemDetails(Scanner scanner, Item item, Room currentRoom, Game game)
 ||                      - Displays the details of a selected item and provides options to use the item.
 ||
 ||                  private void useItem(Scanner scanner, Item item, Room currentRoom, Game game)
 ||                      - Uses the selected item and performs the corresponding action.
 ||
 ++-----------------------------------------------------------------------*/   

import java.util.*;

public class Player {
    private String name;
    private int hp;
    private List<Item> items;
    private List<String> log;

    public Player(String name) {
        this.name = name;
        this.hp = 10;
        this.items = new ArrayList<>();
        this.log = new ArrayList<>();
    }

    /*---------------------------------------------------------------------
    |  Method getName
    |
    |  Purpose:  Returns the player's name.
    |
    |  Pre-condition:  The player's name must be initialized.
    |
    |  Post-condition: The player's name is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  String -- The player's name.
    *-------------------------------------------------------------------*/
    public String getName() {
        return name;
    }

    /*---------------------------------------------------------------------
    |  Method getHp
    |
    |  Purpose:  Returns the player's current HP.
    |
    |  Pre-condition:  The player's HP must be initialized.
    |
    |  Post-condition: The player's current HP is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  int -- The player's current HP.
    *-------------------------------------------------------------------*/
    public int getHp() {
        return hp;
    }

    /*---------------------------------------------------------------------
    |  Method addHp
    |
    |  Purpose:  Adds the specified amount to the player's HP.
    |
    |  Pre-condition:  The player's HP must be initialized.
    |
    |  Post-condition: The specified amount is added to the player's HP.
    |
    |  Parameters:  
    |      amount -- The amount to be added to the player's HP.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    public void addHp(int amount) {
        this.hp += amount;
    }

    /*---------------------------------------------------------------------
    |  Method addItem
    |
    |  Purpose:  Adds an item to the player's inventory and logs the action.
    |
    |  Pre-condition:  The player's inventory must be initialized.
    |
    |  Post-condition: The item is added to the player's inventory and logged.
    |
    |  Parameters:  
    |      item -- The item to be added to the player's inventory.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    public void addItem(Item item) {
        this.items.add(item);
        addToLog("Item added: " + item.getName());
    }

    /*---------------------------------------------------------------------
    |  Method getItems
    |
    |  Purpose:  Returns the list of items in the player's inventory.
    |
    |  Pre-condition:  The player's inventory must be initialized.
    |
    |  Post-condition: The list of items in the player's inventory is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  List<Item> -- The list of items in the player's inventory.
    *-------------------------------------------------------------------*/
    public List<Item> getItems() {
        return items;
    }

    /*---------------------------------------------------------------------
    |  Method getLog
    |
    |  Purpose:  Returns the player's activity log.
    |
    |  Pre-condition:  The player's log must be initialized.
    |
    |  Post-condition: The player's activity log is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  List<String> -- The player's activity log.
    *-------------------------------------------------------------------*/
    public List<String> getLog() {
        return log;
    }

    /*---------------------------------------------------------------------
    |  Method addToLog
    |
    |  Purpose:  Adds an entry to the player's activity log.
    |
    |  Pre-condition:  The player's log must be initialized.
    |
    |  Post-condition: The entry is added to the player's activity log.
    |
    |  Parameters:  
    |      entry -- The log entry to be added.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    public void addToLog(String entry) {
        this.log.add(entry);
    }

    /*---------------------------------------------------------------------
    |  Method displayItems
    |
    |  Purpose:  Displays the player's inventory, allowing the player to select
    |            and use items.
    |
    |  Pre-condition:  The player's inventory must be initialized.
    |
    |  Post-condition: The player's inventory is displayed, and items can be
    |                  selected and used.
    |
    |  Parameters:  
    |      scanner -- Scanner object for reading user input.
    |      currentRoom -- The current room object where the player is located.
    |      game -- The game object to interact with the player's actions.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    public void displayItems(Scanner scanner, Room currentRoom, Game game) {
        UI.clearScreen();
        System.out.println("Inventory:");

        List<Item> items = new ArrayList<>(getItems());
        items.sort(Comparator.comparing(Item::getName));  // Sort items alphabetically

        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getName());
            }
            System.out.println((items.size() + 1) + ". Go back");

            System.out.print("Select an item by number: ");
            int choice = UI.getValidatedInput(scanner, 1, items.size() + 1);

            if (choice <= items.size()) {
                displayItemDetails(scanner, items.get(choice - 1), currentRoom, game);
            }
        }
        UI.promptEnterKey(scanner);
    }
    
    /*---------------------------------------------------------------------
    |  Method displayItemDetails
    |
    |  Purpose:  Displays the details of a selected item and provides options
    |            to use the item.
    |
    |  Pre-condition:  The player's inventory must be initialized and contain items.
    |
    |  Post-condition: The details of the selected item are displayed, and the player
    |                  is given options to use the item.
    |
    |  Parameters:  
    |      scanner -- Scanner object for reading user input.
    |      item -- The selected item whose details will be displayed.
    |      currentRoom -- The current room object where the player is located.
    |      game -- The game object to interact with the player's actions.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    private void displayItemDetails(Scanner scanner, Item item, Room currentRoom, Game game) {
        UI.clearScreen();
        System.out.println("Item: " + item.getName());
        System.out.println("Description: " + item.getDescription());

        System.out.println("1. Use item");
        System.out.println("2. Go back");

        System.out.print("Select an option: ");
        int choice = UI.getValidatedInput(scanner, 1, 2);

        switch (choice) {
            case 1:
                useItem(scanner, item, currentRoom, game);
                break;
            case 2:
                displayItems(scanner, currentRoom, game);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                UI.promptEnterKey(scanner);
                displayItemDetails(scanner, item, currentRoom, game);
                break;
        }
    }

    /*---------------------------------------------------------------------
    |  Method useItem
    |
    |  Purpose:  Uses the selected item and performs the corresponding action.
    |
    |  Pre-condition:  The player's inventory must be initialized and contain the selected item.
    |
    |  Post-condition: The selected item is used, and the corresponding action is performed.
    |
    |  Parameters:  
    |      scanner -- Scanner object for reading user input.
    |      item -- The selected item to be used.
    |      currentRoom -- The current room object where the player is located.
    |      game -- The game object to interact with the player's actions.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    private void useItem(Scanner scanner, Item item, Room currentRoom, Game game) {
        UI.clearScreen();
        System.out.println("Using item: " + item.getName());

        switch (item.getName().toLowerCase()) {
            case "map":
                item.displayMap();
                break;
            case "blanket":
                item.useBlanket(this);
                break;
            case "lamp":
                item.useLamp(this);
                break;
            case "rusty key":
                item.useRustyKey(this, currentRoom, game);
                break;
            default:
                System.out.println("Nothing special happens.");
                break;
        }

        UI.promptEnterKey(scanner);
        displayItems(scanner, currentRoom, game);
    }

    /*---------------------------------------------------------------------
    |  Method displayLog
    |
    |  Purpose:  Displays the player's activity log.
    |
    |  Pre-condition:  The player's log must be initialized.
    |
    |  Post-condition: The player's activity log is displayed.
    |
    |  Parameters:  
    |      scanner -- Scanner object for reading user input.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    public void displayLog(Scanner scanner) {
        UI.clearScreen();
        System.out.println("Log:");
        for (String entry : getLog()) {
            System.out.println("- " + entry);
        }
        UI.promptEnterKey(scanner);
    }

    /*---------------------------------------------------------------------
    |  Method toString
    |
    |  Purpose:  Returns a string representation of the player.
    |
    |  Pre-condition:  The player must be initialized with its attributes.
    |
    |  Post-condition: A string representation of the player is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  String -- A string representation of the player.
    *-------------------------------------------------------------------*/
    @Override
    public String toString() {
        return "Player [name=" + name + ", hp=" + hp + ", items=" + items + "]";
    }
}
