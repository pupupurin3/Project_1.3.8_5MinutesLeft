import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber;
    private String initialDescription;
    private String detailedDescription;
    private boolean light;
    private List<Item> items;
    private Player player;  // Reference to the Player object

    public Room(int roomNumber, String initialDescription, String detailedDescription, boolean light, List<Item> items, Player player) {
        this.roomNumber = roomNumber;
        this.initialDescription = initialDescription;
        this.detailedDescription = detailedDescription;
        this.light = light;
        this.items = new ArrayList<>(items);  // Ensure items is a mutable list
        this.player = player;  // Initialize the player reference
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getInitialDescription() {
        return initialDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public boolean isLight() {
        return light;
    }

    public List<Item> getItems() {

        return items;
    }

    public void inspect() {
        if (light) {
            System.out.println(detailedDescription);
            if (!items.isEmpty()) {
                System.out.println("\nYou found the following items:");
                for (Item item : items) {
                    System.out.println("- " + item.getName() + ": " + item.getDescription());
                }
                pickUpItems();  // Automatically pick up items after inspection
            }
            else{
                System.out.println("\nNo items in this area... ");
            }
        } else {
            System.out.println("It's too dark to see anything! You'll need to get a Light Source");
        }
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", initialDescription=" + initialDescription + ", detailedDescription=" + detailedDescription + ", light=" + light + "]";
    }

    private void pickUpItems() {
        for (Item item : items) {
            player.addItem(item);  // Add items to the player's inventory
        }
        items.clear();
        System.out.println("Items have been added to your inventory.");
    }
}
