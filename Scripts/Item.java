import java.util.*;

public class Item {
    private String name;
    private String description;
    private boolean lightSource;

    public Item(String name, String description, boolean lightSource) {
        this.name = name;
        this.description = description;
        this.lightSource = lightSource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLightSource() {
        return lightSource;
    }

    public void useBlanket(Player player) {
        player.addHp(2);
        System.out.println("You used the Blanket and gained 2 HP.");
        player.getItems().remove(this);  // Remove the blanket after use
    }

    public void useLamp(Player player) {
        Optional<Item> optionalNote = player.getItems().stream()
                                             .filter(i -> i.getName().equalsIgnoreCase("Blank Note"))
                                             .findFirst();

        if (optionalNote.isPresent()) {
            decodeNoteWithLamp(player);
            player.getItems().remove(optionalNote.get());  // Remove the blank note after decoding
        } else {
            System.out.println("You have no note to decode with the lamp.");
        }
    }

    private void decodeNoteWithLamp(Player player) {
        System.out.println("Decoding the blank note with the Lamp...");
        System.out.println("The hidden message reads: 'Follow the light to find the truth.'");

        // Create a new item and add it to the player's inventory
        Item decodedNote = new Item("Decoded Note", "A note that reveals the hidden truth. 'Follow the light to find the truth.'", false);
        player.addItem(decodedNote);
    }

    public void displayMap() {
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

    public void useRustyKey(Player player, Room currentRoom, Game game) {
        if (currentRoom.getRoomNumber() == 8) {
            game.triggerGoodEnding();
        } else {
            System.out.println("The Rusty Key doesn't seem to fit any lock here.");
        }
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", description=" + description + ", lightSource=" + lightSource + "]";
    }
}
