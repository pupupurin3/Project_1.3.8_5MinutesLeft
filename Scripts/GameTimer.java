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
    
        public static void stopTimer() {
            if (timer != null) {
            timer.cancel();
        }
    }
}
