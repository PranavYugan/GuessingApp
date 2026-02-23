import java.util.*;


/**
 * MAIN CLASS
 * 
 * USE CASE 4 - Error Handling & Validation
 * 
 * This class coordinates the game execution while ensuring 
 * all user inputs are safely validated before processing.
 * 
 * Responsibilities:
 * - Initialize game configuration
 * - Accept user input
 * - Validate input using ValidationService
 * - Handle the game flow without crashing on invalid input
 * 
 * @author Developer
 * @version 4.0
 */

public class GuessingApp{
    public static void main(String args[]) throws InvalidInputException{
        System.out.println("Welcome to Guessing App");      
        GameConfig obj=new GameConfig();
        obj.showRules();

        Scanner u=new Scanner(System.in);
        int attempts=0;

        /**
         * Game loops runs untill the player 
         * exhausts the maximum attempts.
         */
        int hintval=0;
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
                break;
            }
        }
    }
}