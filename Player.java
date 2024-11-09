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

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void addHp(int amount) {
        this.hp += amount;
    }

    public void addItem(Item item) {
        this.items.add(item);
        addToLog("Item added: " + item.getName());
    }

    public List<Item> getItems() {
        return items;
    }

    public List<String> getLog() {
        return log;
    }

    public void addToLog(String entry) {
        this.log.add(entry);
    }

    public void displayItems(Scanner scanner) {
        Main.clearScreen();
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
            int choice = Main.getValidatedInput(scanner, 1, items.size() + 1);

            if (choice <= items.size()) {
                displayItemDetails(scanner, items.get(choice - 1));
            }
        }
        Main.promptEnterKey(scanner);
    }

    private void displayItemDetails(Scanner scanner, Item item) {
        Main.clearScreen();
        System.out.println("Item: " + item.getName());
        System.out.println("Description: " + item.getDescription());

        System.out.println("1. Use item");
        System.out.println("2. Go back");

        System.out.print("Select an option: ");
        int choice = Main.getValidatedInput(scanner, 1, 2);

        switch (choice) {
            case 1 -> useItem(scanner, item);
            case 2 -> displayItems(scanner);
            default -> {
                System.out.println("Invalid choice. Try again.");
                Main.promptEnterKey(scanner);
                displayItemDetails(scanner, item);
            }
        }
    }

    private void useItem(Scanner scanner, Item item) {
        Main.clearScreen();
        System.out.println("Using item: " + item.getName());

        if (item.getName().equalsIgnoreCase("Map")) {
            displayMap();
        } else if (item.getName().equalsIgnoreCase("Blanket")) {
            addHp(2);
            System.out.println("You used the Blanket and gained 2 HP.");
            getItems().remove(item);  // Remove the blanket after use
        } else if (item.getName().equalsIgnoreCase("Lamp")) {
            if (getItems().stream().anyMatch(i -> i.getName().equalsIgnoreCase("Note"))) {
                decodeNote();
            } else {
                System.out.println("You have no note to decode.");
            }
        }

        Main.promptEnterKey(scanner);
        displayItems(scanner);
    }

    private void decodeNote() {
        System.out.println("Decoding the note with the Lamp...");
        System.out.println("The note reads: 'Hidden message revealed!'");
        // Update the player's log or inventory as needed
    }

    public void displayLog(Scanner scanner) {
        Main.clearScreen();
        System.out.println("Log:");
        for (String entry : getLog()) {
            System.out.println("- " + entry);
        }
        Main.promptEnterKey(scanner);
    }

    private void displayMap() {
        System.out.println("Map:");
        System.out.println(" _______________________\r\n"+
                           "|       |       |       |\r\n"+
                           "|   1   H   2   H   3   |\r\n"+
                           "|__===__|__===__|__===__|\r\n"+
                           "|       |       |       |\r\n"+
                           "|   4   H   5   H   6   |\r\n"+
                           "|__===__|__===__|__===__|\r\n"+
                           "|       |       |       |\r\n"+
                           "|   7   H   8   H   9   |\r\n"+
                           "|_______|__===__|_______|\r\n");
        System.out.println("\nLegend:");
        System.out.println("== & H <- Door");
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", hp=" + hp + ", items=" + items + "]";
    }
}
