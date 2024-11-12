import java.util.*;

public class Game {
    private Player player;
    private Room[] rooms;
    private int currentRoom;
    private GameTimer gameTimer;

    public Game(Player player) {
        this.player = player;
        this.rooms = new Room[9];
        this.currentRoom = 4;  // Room 5 (index 4)
        this.gameTimer = new GameTimer(player, this);
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
    
        gameTimer.startTimer();  // Start the timer to reduce HP
    
        while (gameRunning) {
            UI.clearScreen();
            UI.displayMenu(player);
    
            int choice = UI.getValidatedInput(scanner, 1, 5);
            UI.clearScreen();
            switch (choice) {
                case 1:
                    act(scanner);
                    break;
                case 2:
                    player.displayItems(scanner, rooms[currentRoom], this);
                    break;
                case 3:
                    player.displayLog(scanner);
                    break;
                case 4:
                    System.out.println("Settings are not implemented yet.");
                    UI.promptEnterKey(scanner);
                    break;
                case 5:
                    System.out.println("Are you sure you want to give up? (y/n)");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    if (confirm.equals("y")) {
                        gameRunning = false;
                        triggerBadEnding();  // Pass the game instance to the ending
                    } else {
                        System.out.println("Continuing the game...");
                        UI.promptEnterKey(scanner);
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    UI.promptEnterKey(scanner);
                    break;
            }
        }
    }
    
    public void triggerGoodEnding() {
        Ending.displayGoodEnding(this);  // Pass the game instance to the ending
    }
    
    public void triggerNeutralEnding() {
        Ending.displayNeutralEnding(this);  // Pass the game instance to the ending
    }
    
    public void triggerBadEnding() {
        Ending.displayBadEnding(this);  // Pass the game instance to the ending
    }
    
       
    private void act(Scanner scanner) {
        System.out.println("Choose an action:");
        System.out.println("1. Move");
        System.out.println("2. Inspect");
        int choice = UI.getValidatedInput(scanner, 1, 2);
    
        UI.clearScreen();
        switch (choice) {
            case 1:
                move(scanner);
                break;
            case 2:
                if (rooms[currentRoom].isLight() || playerHasLightSource()) {
                    rooms[currentRoom].inspect();
                } else {
                    System.out.println("It's too dark to see anything. You need a light source.");
                }
                UI.promptEnterKey(scanner);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                UI.promptEnterKey(scanner);
                break;
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
        int direction = UI.getValidatedInput(scanner, 1, 4);

        UI.clearScreen();
        int newRoom = -1;
        switch (direction) {
            case 1:
                newRoom = currentRoom - 3;
                break;
            case 2:
                newRoom = currentRoom + 3;
                break;
            case 3:
                newRoom = currentRoom - 1;
                break;
            case 4:
                newRoom = currentRoom + 1;
                break;
        }

        if (newRoom >= 0 && newRoom < rooms.length && (direction == 3 || direction == 4 ? newRoom / 3 == currentRoom / 3 : true)) {
            currentRoom = newRoom;
            System.out.println("You moved to room " + (currentRoom + 1) + ".");
            System.out.println(rooms[currentRoom].getInitialDescription());
            player.addToLog("Moved to room " + (currentRoom + 1));
        } else {
            System.out.println("You can't move in that direction.");
        }
        UI.promptEnterKey(scanner);
    }

    public Player getPlayer() {
        return player;
    }
    
    public Room getCurrentRoom() {
        return rooms[currentRoom];
    }
}
