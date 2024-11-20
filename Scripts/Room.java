/*+----------------------------------------------------------------------
 ||
 ||  Class: Room
 ||
 ||         Author:  Noah Matsukuma
 ||
 ||        Purpose:  This class represents a room in the game. Each room
 ||                  has a number, description, lighting condition, items,
 ||                  and a reference to the player object. The class provides
 ||                  methods to inspect the room and interact with items.
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
 ||                  Room(int roomNumber, String initialDescription, String detailedDescription, 
 ||                       boolean light, List<Item> items, Player player)
 ||                      - Initializes the room with its number, descriptions, lighting condition, 
 ||                        items, and player reference.
 ||
 ||  Class Methods:  None
 ||
 ||  Inst. Methods:  
 ||                  int getRoomNumber()
 ||                      - Returns the room number.
 ||
 ||                  String getInitialDescription()
 ||                      - Returns the initial description of the room.
 ||
 ||                  String getDetailedDescription()
 ||                      - Returns the detailed description of the room.
 ||
 ||                  boolean isLight()
 ||                      - Returns whether the room has light.
 ||
 ||                  List<Item> getItems()
 ||                      - Returns the list of items in the room.
 ||
 ||                  void inspect()
 ||                      - Displays the detailed description of the room and the items in it, 
 ||                        allowing the player to pick up the items if there is light.
 ||
 ||                  String toString()
 ||                      - Returns a string representation of the room.
 ||
 ||                  private void pickUpItems()
 ||                      - Adds the items in the room to the player's inventory and clears the items 
 ||                        from the room.
 ||
 ++-----------------------------------------------------------------------*/

import java.util.*;

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
    /*---------------------------------------------------------------------
    |  Method getRoomNumber
    |
    |  Purpose:  Returns the room number.
    |
    |  Pre-condition:  The room number must be initialized.
    |
    |  Post-condition: The room number is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  int -- The room number.
    *-------------------------------------------------------------------*/
    public int getRoomNumber() {
        return roomNumber;
    }
 
    /*---------------------------------------------------------------------
    |  Method getInitialDescription
    |
    |  Purpose:  Returns the initial description of the room.
    |
    |  Pre-condition:  The initial description must be initialized.
    |
    |  Post-condition: The initial description is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  String -- The initial description of the room.
    *-------------------------------------------------------------------*/
    public String getInitialDescription() {
        return initialDescription;
    }

    /*---------------------------------------------------------------------
    |  Method getDetailedDescription
    |
    |  Purpose:  Returns the detailed description of the room.
    |
    |  Pre-condition:  The detailed description must be initialized.
    |
    |  Post-condition: The detailed description is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  String -- The detailed description of the room.
    *-------------------------------------------------------------------*/
    public String getDetailedDescription() {
        return detailedDescription;
    }
    /*---------------------------------------------------------------------
    |  Method isLight
    |
    |  Purpose:  Returns whether the room has light.
    |
    |  Pre-condition:  The light status must be initialized.
    |
    |  Post-condition: The light status is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  boolean -- True if the room has light, false otherwise.
    *-------------------------------------------------------------------*/
    public boolean isLight() {
        return light;
    }

    /*---------------------------------------------------------------------
    |  Method getItems
    |
    |  Purpose:  Returns the list of items in the room.
    |
    |  Pre-condition:  The items list must be initialized.
    |
    |  Post-condition: The list of items is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  List<Item> -- The list of items in the room.
    *-------------------------------------------------------------------*/
    public List<Item> getItems() {
        return items;
    }
 
    /*---------------------------------------------------------------------
    |  Method inspect
    |
    |  Purpose:  Displays the detailed description of the room and the items in it, 
    |            allowing the player to pick up the items if there is light.
    |
    |  Pre-condition:  The room must be initialized with its attributes.
    |
    |  Post-condition: The detailed description and items are displayed,
    |                  and items are picked up if there is light.
    |
    |  Parameters:  None
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
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
 
    /*---------------------------------------------------------------------
    |  Method toString
    |
    |  Purpose:  Returns a string representation of the room.
    |
    |  Pre-condition:  The room must be initialized with its attributes.
    |
    |  Post-condition: A string representation of the room is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  String -- A string representation of the room.
    *-------------------------------------------------------------------*/
    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", initialDescription=" + initialDescription + ", detailedDescription=" + detailedDescription + ", light=" + light + "]";
    }

    /*---------------------------------------------------------------------
    |  Method pickUpItems
    |
    |  Purpose:  Adds the items in the room to the player's inventory and clears the items 
    |            from the room.
    |
    |  Pre-condition:  The room and player objects must be initialized, and the room must
    |                  contain items.
    |
    |  Post-condition: The items are added to the player's inventory and cleared from the room.
    |
    |  Parameters:  None
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    private void pickUpItems() {
        for (Item item : items) {
            player.addItem(item);  // Add items to the player's inventory
        }
        items.clear();
        System.out.println("Items have been added to your inventory.");
    }
}