/*+----------------------------------------------------------------------
 ||
 ||  Class Ending 
 ||
 ||         Author:  Noah Matsukuma
 ||
 ||        Purpose:  This class is used for managing all the possible endings
 ||                  in the game. It is designed for organizational purposes
 ||                  for the Game.java class.
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
 ||  Class Methods:  
 ||                  static void displayGoodEnding(Game game)
 ||                      - Displays the good ending of the game.
 ||
 ||                  static void displayBadEnding(Game game)
 ||                      - Displays the bad ending of the game.
 ||
 ||                  static void displayNeutralEnding(Game game)
 ||                      - Displays the neutral ending of the game.
 ||
 ||  Inst. Methods:  None
 ||
 ++-----------------------------------------------------------------------*/

 import java.util.*;

 public class Ending {
 
     /*---------------------------------------------------------------------
     |  Method displayGoodEnding
     |
     |  Purpose:  Displays the good ending of the game by narrating a story
     |            and updating the game state.
     |
     |  Pre-condition:  The game must be running, and the player must be in
     |                  a state to trigger the good ending.
     |
     |  Post-condition: The good ending story is displayed, the game state
     |                  is updated to stop running, and the timer is stopped.
     |
     |  Parameters:  
     |      game -- The current game instance to access player and game state.
     |
     |  Returns:  None
     *-------------------------------------------------------------------*/
     public static void displayGoodEnding(Game game) {
         Player player = game.getPlayer();
         game.gameRunning = false;
         GameTimer.stopTimer();
         UI.clearScreen();
         System.out.println("You use the Rusty Key to unlock the door. Light engulfs you...");
         UI.promptEnterKey(new Scanner(System.in));
         System.out.println("...");
         System.out.println("... ...");
         UI.promptEnterKey(new Scanner(System.in));
         String obscuredName = player.getName().replaceAll("(.)(.*)(.)", "$1...$3");
         System.out.println("..." + obscuredName + "...\n");
         System.out.println(obscuredName + "!");
         UI.promptEnterKey(new Scanner(System.in));
         System.out.println(player.getName() + "!");
         System.out.println("You're awake!");
         UI.promptEnterKey(new Scanner(System.in));
 
         // Internal dialogue
         System.out.println("You were told that you drowned losing your balance while swimming, feeling the water fill your lungs. You only had 5 minutes before it was too late.");
         UI.promptEnterKey(new Scanner(System.in));
         System.out.println("The rooms... the choices you made... it's all a blur now.");
         UI.promptEnterKey(new Scanner(System.in));
         System.out.println("You try to piece together the events, but the details slip away as you move on with your day.");
     }
 
     /*---------------------------------------------------------------------
     |  Method displayBadEnding
     |
     |  Purpose:  Displays the bad ending of the game by narrating a story
     |            and updating the game state.
     |
     |  Pre-condition:  The game must be running, and the player must be in
     |                  a state to trigger the bad ending.
     |
     |  Post-condition: The bad ending story is displayed, the game state
     |                  is updated to stop running, and the timer is stopped.
     |
     |  Parameters:  
     |      game -- The current game instance to access player and game state.
     |
     |  Returns:  None
     *-------------------------------------------------------------------*/
     public static void displayBadEnding(Game game) {
         game.gameRunning = false;
         GameTimer.stopTimer();
         UI.clearScreen();
         System.out.println("...");
         System.out.println("... ...");
         System.out.println("... ... ... ...");
         UI.promptEnterKey(new Scanner(System.in));
         System.out.println("As you stop trying to escape, you feel the world around you fade to darkness.");
         System.out.println("You succumb to the overwhelming sense of despair as you close your eyes.");
         System.out.println("...");
         System.out.println("\n You hear sobbing in your last moments...");
         System.out.println("\nBAD END 1");
         UI.promptEnterKey(new Scanner(System.in));
     }
 
     /*---------------------------------------------------------------------
     |  Method displayNeutralEnding
     |
     |  Purpose:  Displays the neutral ending of the game by narrating a story
     |            and updating the game state.
     |
     |  Pre-condition:  The game must be running, and the player must be in
     |                  a state to trigger the neutral ending.
     |
     |  Post-condition: The neutral ending story is displayed, the game state
     |                  is updated to stop running, and the timer is stopped.
     |
     |  Parameters:  
     |      game -- The current game instance to access player and game state.
     |
     |  Returns:  None
     *-------------------------------------------------------------------*/
     public static void displayNeutralEnding(Game game) {
         game.gameRunning = false;
         GameTimer.stopTimer();
         UI.clearScreen();
         System.out.println("...");
         System.out.println("... ...");
         System.out.println("... ... ... ...");
         UI.promptEnterKey(new Scanner(System.in));
         System.out.println("You try your best to find the exit, but it was too late");
         System.out.println("You lose your breath, and your vision fades away");
         System.out.println("...");
         System.out.println("\nYou hear sobbing in your last moments...");
         System.out.println("\nBAD END 2");
         UI.promptEnterKey(new Scanner(System.in));
     }
 }
 