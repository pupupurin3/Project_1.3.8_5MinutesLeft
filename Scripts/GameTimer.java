/*+----------------------------------------------------------------------
 ||
 ||  Class GameTimer 
 ||
 ||         Author:  Noah Matsukuma
 ||
 ||        Purpose:  Simply a timer that is run when the game starts. It periodically 
 ||                  decreases the player's HP and logs the changes, ultimately triggering 
 ||                  a neutral ending if the player's HP reaches zero.
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
 ||                  GameTimer(Player player, Game game)
 ||                      - Initializes the GameTimer with a player and game instance.
 ||
 ||  Class Methods:  None
 ||
 ||  Inst. Methods:  
 ||                  void startTimer()
 ||                      - Starts the timer that decreases the player's HP every 30 seconds.
 ||
 ||                  static void stopTimer()
 ||                      - Stops the timer.
 ||
 ++-----------------------------------------------------------------------*/

 import java.util.Timer;
 import java.util.TimerTask;
 
 public class GameTimer {
     private static Timer timer;
     private Player player;
     private Game game;
 
     public GameTimer(Player player, Game game) {
         this.timer = new Timer();
         this.player = player;
         this.game = game;
     }
 
     /*---------------------------------------------------------------------
     |  Method startTimer
     |
     |  Purpose:  Starts the timer that decreases the player's HP by 1 every 30 seconds,
     |            and logs the HP decrease. If the player's HP reaches zero, it triggers
     |            the neutral ending of the game.
     |
     |  Pre-condition:  The player and game objects must be initialized. The player's HP
     |                  must be greater than zero.
     |
     |  Post-condition: The player's HP is decreased by 1 every 30 seconds. If HP reaches zero,
     |                  the neutral ending is triggered.
     |
     |  Parameters:  None
     |
     |  Returns:  None
     *-------------------------------------------------------------------*/
     public void startTimer() {
         TimerTask task = new TimerTask() {
             @Override
             public void run() {
                 player.addHp(-1);  // Reduce HP by 1 every 30 seconds
                 player.addToLog("HP decreased by 1. Current HP: " + player.getHp());  // Log the HP decrease
                 System.out.println("Your HP decreased by 1. Current HP: " + player.getHp());
                 if (player.getHp() <= 0) {
                     game.triggerNeutralEnding();  // Trigger neutral ending when HP is 0
                 }
             }
         };
         timer.scheduleAtFixedRate(task, 30000, 30000);  // Schedule the task to run every 30 seconds
     }
 
     /*---------------------------------------------------------------------
     |  Method stopTimer
     |
     |  Purpose:  Stops the timer that was previously started, halting the periodic
     |            decrease of the player's HP.
     |
     |  Pre-condition:  The timer must have been started.
     |
     |  Post-condition: The timer is stopped, and the player's HP is no longer decreased
     |                  periodically.
     |
     |  Parameters:  None
     |
     |  Returns:  None
     *-------------------------------------------------------------------*/
     public static void stopTimer() {
         if (timer != null) {
             timer.cancel();
         }
     }
 }
 