import java.util.*;


/**
 * MAIN CLASS
 * 
 * USE CASE 5 - Game Result Storage
 * 
 * This class coordinates the complete game flow
 * and persists the final result after completion.
 * 
 * Responsibilities:
 * - Initialize game configuration
 * - Accept and validate user guesses
 * - Generate hints when applicable
 * - Store game result at the end
 * 
 * @author Developer
 * @version 5.0
 */

public class GuessingApp{
    public static void main(String args[]) throws InvalidInputException{
        System.out.println("Welcome to Guessing App");
        Scanner u=new Scanner(System.in);
        System.out.print("Enter Player Name: ");
        String player=u.nextLine();
        GameConfig obj=new GameConfig();
        obj.showRules();

        
        int attempts=0;

        /**
         * Game loops runs untill the player 
         * exhausts the maximum attempts.
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

        StorageService.saveResult(player,attempts,win);
    }
}