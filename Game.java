import java.util.*;

public class Game {
    private Player player;
    private Room[] rooms;
    private int currentRoom;
    private Timer timer;

    public Game(Player player) {
        this.player = player;
        this.rooms = new Room[9];
        this.currentRoom = 4;  // Room 5 (index 4)
        this.timer = new Timer();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms[0] = new Room(1, "You are in a dark room, and you barely can see a foot into the room. \nBut as soon as you enter the room you feel crushed up paper all over the floor",
                            "The room is damp and filled with cobwebs. You can barely see anything without a light source.",
                            false, new ArrayList<>(Arrays.asList(new Item("Blank Note", "A note that has no scripture, but smells a bit of citrus. \nMaybe if you can get a source of fire...", false))), player);
    
        rooms[1] = new Room(2, "You see a flickering light bulb.",
                            "The light bulb hangs precariously from the ceiling, illuminating the scattered papers on the floor.",
                            true, new ArrayList<>(Arrays.asList(new Item("Blanket", "A warm blanket that might be useful.", false))), player);
    
        rooms[2] = new Room(3, "You hear a distant beating sound.",
                            "The room echoes beats at a stable rhythm.",
                            true, new ArrayList<>(Arrays.asList(new Item("Lamp", "A lamp that can illuminate dark areas and decode notes.", true))), player);
    
        rooms[3] = new Room(4, "There seems to be a key.",
                            "The floor is covered in dust, and a rusty key lies among the debris.",
                            true, new ArrayList<>(Arrays.asList(new Item("Rusty Key", "An old, rusty key. May need to take off the rust in order to use it.", false))), player);
    
        rooms[4] = new Room(5, "You find yourself in a dim room. But you feel a weird feeling \nthat makes you feel like you are deep in water, with a light feeling.",
                            "The room is quiet and serene, and any sound made feels far from you.",
                            true, new ArrayList<>(Arrays.asList(new Item("Map", "A map of the area", false))), player);
    
        rooms[5] = new Room(6, "You see a door that is slightly ajar. The door creaks as it opens, revealing a room filled with old furniture.",
                            "A nice smell of citrus fills the air.",
                            true, new ArrayList<>(), player);
    
        rooms[6] = new Room(7, "",
                            "",
                            true, new ArrayList<>(), player);
    
        rooms[7] = new Room(8, "The room is empty and cold.",
                            "If you focus a bit, you start to hear a distant sound, with a chill in the air.",
                            false, new ArrayList<>(), player);
    
        rooms[8] = new Room(9, "You see a window, but it's barred.",
                            "The barred window offers a glimpse of the outside, but the room is dark.",
                            false, new ArrayList<>(Arrays.asList(new Item("Written Note", "A note, it reads the following\n\"Humans when drowned only has 5 minutes of time to spare before someone does CPR and save them.\"", false))), player);
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;
    
        startTimer();  // Start the timer to reduce HP
    
        while (gameRunning) {
            Main.clearScreen();
            displayMenu();
    
            int choice = Main.getValidatedInput(scanner, 1, 5);
            Main.clearScreen();
            switch (choice) {
                case 1 -> act(scanner);
                case 2 -> displayItems(scanner);
                case 3 -> displayLog(scanner);
                case 4 -> {
                    System.out.println("Settings are not implemented yet.");
                    Main.promptEnterKey(scanner);
                }
                case 5 -> {
                    System.out.println("Are you sure you want to give up? (y/n)");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    if (confirm.equals("y")) {
                        gameRunning = false;
                        triggerBadEnding();  // Pass the game instance to the ending
                    } else {
                        System.out.println("Continuing the game...");
                        Main.promptEnterKey(scanner);
                    }
                }
                default -> {
                    System.out.println("Invalid choice. Try again.");
                    Main.promptEnterKey(scanner);
                }
            }
        }
    }
    
    private void triggerGoodEnding() {
        Ending.displayGoodEnding(this);  // Pass the game instance to the ending
    }
    
    private void triggerNeutralEnding() {
        Ending.displayNeutralEnding(this);  // Pass the game instance to the ending
    }
    
    private void triggerBadEnding() {
        Ending.displayBadEnding(this);  // Pass the game instance to the ending
    }
       
    private void act(Scanner scanner) {
        System.out.println("Choose an action:");
        System.out.println("1. Move");
        System.out.println("2. Inspect");
        int choice = Main.getValidatedInput(scanner, 1, 2);
    
        Main.clearScreen();
        switch (choice) {
            case 1 -> move(scanner);
            case 2 -> {
                if (rooms[currentRoom].isLight() || playerHasLightSource()) {
                    rooms[currentRoom].inspect();
                } else {
                    System.out.println("It's too dark to see anything. You need a light source.");
                }
                Main.promptEnterKey(scanner);
            }
            default -> {
                System.out.println("Invalid choice. Try again.");
                Main.promptEnterKey(scanner);
            }
        }
    }    
    
    private boolean playerHasLightSource() {
        return player.getItems().stream().anyMatch(Item::isLightSource);
    }
     

    private void move(Scanner scanner) {
        System.out.println("Choose a direction to move:");
        System.out.println("1. Up");
        System.out.println("2. Down");
        System.out.println("3. Left");
        System.out.println("4. Right");
        int direction = Main.getValidatedInput(scanner, 1, 4);

        Main.clearScreen();
        int newRoom = -1;
        switch (direction) {
            case 1 -> newRoom = currentRoom - 3;
            case 2 -> newRoom = currentRoom + 3;
            case 3 -> newRoom = currentRoom - 1;
            case 4 -> newRoom = currentRoom + 1;
        }

        if (newRoom >= 0 && newRoom < rooms.length && (direction == 3 || direction == 4 ? newRoom / 3 == currentRoom / 3 : true)) {
            currentRoom = newRoom;
            System.out.println("You moved to room " + (currentRoom + 1) + ".");
            System.out.println(rooms[currentRoom].getInitialDescription());
            player.addToLog("Moved to room " + (currentRoom + 1));
        } else {
            System.out.println("You can't move in that direction.");
        }
        Main.promptEnterKey(scanner);
    }

    private void displayMenu() {
        int maxHp = 10;  
    
        System.out.println("________________________________________________________________________");
        System.out.println("Player :  " + player.getName());
        System.out.println("HP :  " + player.getHp() + " / " + maxHp + " " + generateHpBar(player.getHp(), maxHp));
        System.out.println("\n\n\n________________________________________________________________________");
        System.out.println("Select Option :");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "1.)  Act", "2.)  Items", "3.)  Log", "4.)  Settings", "5.)  Give Up");
        System.out.println("________________________________________________________________________");
    }    
    
    private void displayItems(Scanner scanner) {
        Main.clearScreen();
        System.out.println("Inventory:");
    
        List<Item> items = new ArrayList<>(player.getItems());
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
            player.addHp(2);
            System.out.println("You used the Blanket and gained 2 HP.");
            player.getItems().remove(item);  // Remove the blanket after use
        } else if (item.getName().equalsIgnoreCase("Lamp")) {
            if (player.getItems().stream().anyMatch(i -> i.getName().equalsIgnoreCase("Note"))) {
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
    
    private void displayLog(Scanner scanner) {
        Main.clearScreen();
        System.out.println("Log:");
        for (String entry : player.getLog()) {
            System.out.println("- " + entry);
        }
        Main.promptEnterKey(scanner);
    }

    private String generateHpBar(int hp, int maxHp) {
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
    
    private void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                player.addHp(-1);  // Reduce HP by 1 every 30 seconds
                player.addToLog("HP decreased by 1. Current HP: " + player.getHp());  // Log the HP decrease
                System.out.println("Your HP decreased by 1. Current HP: " + player.getHp());
                if (player.getHp() <= 0) {
                    triggerNeutralEnding();;  // Trigger bad ending when HP is 0
                }
            }
        };
        timer.scheduleAtFixedRate(task, 30000, 30000);  // Schedule the task to run every 30 seconds
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

}
