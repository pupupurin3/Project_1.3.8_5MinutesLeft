/*=============================================================================
 |   Assignment:  Project 1.3.8 Choose your Path - 5 minutes left
 |       Author:  Noah Matsukuma
 |      Partner:  n/a
 |
 |  Course Name:  AP Computer Science A
 |   Instructor:  Mr. Jonathan Virak
 |     Due Date:  2024/10/31 11:59
 |
 |  Description:  The goal of this program is to develop a text-based adventure game
 |                where the player navigates through various rooms, interacts with items,
 |                and achieves different endings based on their actions. The game has a
 |                time-limit mechanic and multiple possible endings.
 |
 |     Language:  Java version 8
 | Ex. Packages:  java.util
 |                
 | Deficiencies:  Some errors in this program:
 |                - Items that have the Light Source attribute as true
 |                  still aren't recognized as a Light Source in rooms.
 |                - Issue Fixed: Currently not sure of a way to pass 
 |                  gameRunning in Ending.java.
 *===========================================================================*/

 import java.util.*;

 public class Main {
     public static void main(String[] args) {
         try (Scanner scanner = new Scanner(System.in)) {
             Player player = null;
             Game game = null;
             boolean gameRunning = true;
             
             while (gameRunning) {
                 UI.startMenu();
                 
                 int choice = UI.getValidatedInput(scanner, 1, 4);
                 
                 switch (choice) {
                     case 1 -> {
                         UI.clearScreen();
                         System.out.print("Enter your name: ");
                         String playerName = scanner.nextLine();
                         player = new Player(playerName);
                         game = new Game(player);
                         game.start();
                     }
                     case 2 -> {
                         System.out.println("This method of playing is not implemented, will add in later variations");
                         UI.promptEnterKey(scanner);
                     }
                     case 3 -> {
                         UI.clearScreen();
                         System.out.println("Welcome to 5 minutes left. This game is a multi-ending scenario with an actual time-limit of 5 minutes for completion.\n" +
                                            "To navigate through this game, I recommend checking the Act option (1. of the options in the Player Action Menu).\n" +
                                            "It's important to check the inventory after you get the items. You are able to use the items, so frequently check them.\n" +
                                            "Check all the rooms because to get the Good Ending you must go through all the rooms.\n" +
                                            "Finally, Have FUN! I'll be adding more modifications to this program.\n\n" +
                                            "From: Noah Matsukuma");
                         UI.promptEnterKey(scanner);
                     }
                     case 4 -> {
                         gameRunning = false;
                         System.out.println("Goodbye!");
                     }
                     default -> {
                         System.out.println("Invalid choice. Try again.");
                         UI.promptEnterKey(scanner);
                     }
                 }
             }
         }
     }
 }
 