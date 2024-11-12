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

    public void displayLog(Scanner scanner) {
        UI.clearScreen();
        System.out.println("Log:");
        for (String entry : getLog()) {
            System.out.println("- " + entry);
        }
        UI.promptEnterKey(scanner);
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", hp=" + hp + ", items=" + items + "]";
    }
}
