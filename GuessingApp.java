import java.util.*;


/**
 * MAIN CLASS
 * 
 * USE CASE 6 - Game Restart & Exit
 * 
 * This class coordinates the complete game lifecycle,
 * allowing the player to replay or excit gracefully.
 * 
 * Responsibilities:
 * - Start a new game session
 * - Execute the guessing flow
 * - Persist game results
 * - Restart or exit based on user choice
 * 
 * @author Developer
 * @version 6.0
 */

public class GuessingApp{
    public static void main(String args[]) throws InvalidInputException{
        System.out.println("Welcome to Guessing App");
        Scanner u=new Scanner(System.in);
        boolean restart;
        /**
         * Outer loop controls whether
         * a new game session should start
         */
        do{
            System.out.print("Enter Player Name: ");
            String player=u.nextLine();
            GameConfig obj=new GameConfig();
            obj.showRules();

            
            int attempts=0;

            /**
             * Game loops runs untill the player 
             * exhausts the maximum attempts or successfully guessed the number.
             */
            int hintval=0;
            boolean win=false;
            while(attempts < obj.getMaxAttempts()){
                System.out.println("Enter your guess: ");
                /**
                 * User input is validated before
                 * being used in game logic.
                 */
                int guess=ValidationService.validateinput(u.nextLine());
                attempts++;
                String result = GuessValidator.validateGuess(guess,obj.getTargetNumber());
                /**
                 * A hint is only generated after an incorrect guess 
                 * and within the allowed hint limit.
                 */
                

                if(!"CORRECT".equals(result) && hintval<obj.getMaxHints()){
                    hintval++;
                    // We call the hint generation function and increment the hint everytime so that the hints are limited
                    System.out.println(HintService.generateHint(obj.getTargetNumber(),hintval));
                }
                System.out.println(result);

                if("CORRECT".equals(result)){
                    win=true;
                    break;
                }
            }
            /**
             * Final game result is persisted
             * after the current session ends
             */

            StorageService.saveResult(player,attempts,win);
            /**
             * Player decides whether to
             * restart the game or exit
             */
            restart=GameController.restartGame(u);

        } while(restart);
    }
}