/*+----------------------------------------------------------------------
 ||
 ||  Class: Item 
 ||
 ||         Author:  Noah Matsukuma
 ||
 ||        Purpose:  This class represents an item in the game. Each item
 ||                  has a name, description, and a flag indicating whether
 ||                  it is a light source. The class provides methods to 
 ||                  use the item and interact with the player.
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
 ||                  Item(String name, String description, boolean lightSource)
 ||                      - Initializes the item with its name, description, 
 ||                        and light source status.
 ||
 ||  Class Methods:  None
 ||
 ||  Inst. Methods:  
 ||                  String getName()
 ||                      - Returns the name of the item.
 ||
 ||                  String getDescription()
 ||                      - Returns the description of the item.
 ||
 ||                  boolean isLightSource()
 ||                      - Returns whether the item is a light source.
 ||
 ||                  void useBlanket(Player player)
 ||                      - Uses the blanket to increase the player's HP and removes 
 ||                        the blanket from the player's inventory.
 ||
 ||                  void useLamp(Player player)
 ||                      - Uses the lamp to decode a blank note if present in the 
 ||                        player's inventory, and removes the note after decoding.
 ||
 ||                  void displayMap()
 ||                      - Displays a map of the game area.
 ||
 ||                  void useRustyKey(Player player, Room currentRoom, Game game)
 ||                      - Uses the rusty key to trigger the good ending if the player 
 ||                        is in the correct room.
 ||
 ||                  String toString()
 ||                      - Returns a string representation of the item.
 ||
 ||                  private void decodeNoteWithLamp(Player player)
 ||                      - Decodes the blank note with the lamp, adds a decoded note 
 ||                        to the player's inventory, and removes the blank note.
 ||
 ++-----------------------------------------------------------------------*/

    



    

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

    /*---------------------------------------------------------------------
    |  Method getName
    |
    |  Purpose:  Returns the name of the item.
    |
    |  Pre-condition:  The item's name must be initialized.
    |
    |  Post-condition: The name of the item is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  String -- The name of the item.
    *-------------------------------------------------------------------*/
    public String getName() {
        return name;
    }

    /*---------------------------------------------------------------------
    |  Method getDescription
    |
    |  Purpose:  Returns the description of the item.
    |
    |  Pre-condition:  The item's description must be initialized.
    |
    |  Post-condition: The description of the item is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  String -- The description of the item.
    *-------------------------------------------------------------------*/
    public String getDescription() {
        return description;
    }

    /*---------------------------------------------------------------------
    |  Method isLightSource
    |
    |  Purpose:  Returns whether the item is a light source.
    |
    |  Pre-condition:  The light source status must be initialized.
    |
    |  Post-condition: The light source status is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  boolean -- True if the item is a light source, false otherwise.
    *-------------------------------------------------------------------*/
    public boolean isLightSource() {
        return lightSource;
    }

    /*---------------------------------------------------------------------
    |  Method useBlanket
    |
    |  Purpose:  Uses the blanket to increase the player's HP by 2 and removes
    |            the blanket from the player's inventory.
    |
    |  Pre-condition:  The player object must be initialized, and the item must be
    |                  a blanket.
    |
    |  Post-condition: The player's HP is increased by 2, and the blanket is removed
    |                  from the player's inventory.
    |
    |  Parameters:  
    |      player -- The player object whose HP will be increased.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    public void useBlanket(Player player) {
        player.addHp(2);
        System.out.println("You used the Blanket and gained 2 HP.");
        player.getItems().remove(this);  // Remove the blanket after use
    }

    /*---------------------------------------------------------------------
    |  Method useLamp
    |
    |  Purpose:  Uses the lamp to decode a blank note if present in the player's
    |            inventory, and removes the note after decoding.
    |
    |  Pre-condition:  The player object must be initialized, and the item must be
    |                  a lamp. The player's inventory should contain a blank note.
    |
    |  Post-condition: The blank note is decoded, and a new item with the decoded
    |                  message is added to the player's inventory. The blank note
    |                  is removed from the inventory.
    |
    |  Parameters:  
    |      player -- The player object whose inventory will be checked and updated.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
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

    /*---------------------------------------------------------------------
    |  Method decodeNoteWithLamp
    |
    |  Purpose:  Decodes the blank note with the lamp, adds a decoded note with
    |            the hidden message to the player's inventory, and removes the
    |            blank note from the inventory.
    |
    |  Pre-condition:  The player object must be initialized, and the player's
    |                  inventory must contain a blank note.
    |
    |  Post-condition: The blank note is decoded, and a new item with the decoded
    |                  message is added to the player's inventory. The blank note
    |                  is removed from the inventory.
    |
    |  Parameters:  
    |      player -- The player object whose inventory will be updated.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    private void decodeNoteWithLamp(Player player) {
        System.out.println("Decoding the blank note with the Lamp...");
        System.out.println("The hidden message reads: 'Follow the light to find the truth.'");

        // Create a new item and add it to the player's inventory
        Item decodedNote = new Item("Decoded Note", "A note that reveals the hidden truth. 'Follow the light to find the truth.'", false);
        player.addItem(decodedNote);
    }

    /*---------------------------------------------------------------------
    |  Method displayMap
    |
    |  Purpose:  Displays a map of the game area, showing the layout of the rooms.
    |
    |  Pre-condition:  The player must have a map item in their inventory.
    |
    |  Post-condition: The map of the game area is displayed.
    |
    |  Parameters:  None
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
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
        System.out.println(" - == & H <- Door");
        System.out.println(" - Exit at Room 8");
    }

    /*---------------------------------------------------------------------
    |  Method useRustyKey
    |
    |  Purpose:  Uses the rusty key to trigger the good ending if the player is
    |            in the correct room (room number 8).
    |
    |  Pre-condition:  The player object, current room, and game object must be
    |                  initialized.
    |
    |  Post-condition: The good ending is triggered if the player is in room number 8.
    |
    |  Parameters:  
    |      player -- The player object whose current room will be checked.
    |      currentRoom -- The current room object where the player is located.
    |      game -- The game object to trigger the ending.
    |
    |  Returns:  None
    *-------------------------------------------------------------------*/
    public void useRustyKey(Player player, Room currentRoom, Game game) {
        if (currentRoom.getRoomNumber() == 8) {
            game.triggerGoodEnding();
        } else {
            System.out.println("The Rusty Key doesn't seem to fit any lock here.");
        }
    }

    /*---------------------------------------------------------------------
    |  Method toString
    |
    |  Purpose:  Returns a string representation of the item.
    |
    |  Pre-condition:  The item must be initialized with its attributes.
    |
    |  Post-condition: A string representation of the item is returned.
    |
    |  Parameters:  None
    |
    |  Returns:  String -- A string representation of the item.
    *-------------------------------------------------------------------*/
    @Override
    public String toString() {
        return "Item [name=" + name + ", description=" + description + ", lightSource=" + lightSource + "]";
    }
}
