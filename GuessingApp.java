import java.util.*;


/**
 * MAIN CLASS
 * 
 * Coordinates the game flow:
 * 1.Initialize game
 * 2.Accept user guesses
 * 3.Validate guesses
 * 4.Stop when game ends
 * 
 * @author Developer
 * @version 2.0
 */

public class GuessingApp{
    public static void main(String args[]){
        System.out.println("Welcome to Guessing App");      
        GameConfig obj=new GameConfig();
        obj.showRules();

        Scanner u=new Scanner(System.in);
        int attempts=0;

        /**
         * Game loops runs untill the player 
         * exhausts the maximum attempts.
         */
        int hintval=1;
        while(attempts < obj.getMaxAttempts()){
            System.out.println("Enter your guess: ");
            int guess=u.nextInt();
            attempts++;
            // We call the hint generation function and increment the hint everytime so that the hints are limited
            System.out.println(HintService.generateHint(obj.getTargetNumber(),hintval++));
            String result = GuessValidator.validateGuess(guess,obj.getTargetNumber());
            System.out.println(result);
            /**
             * Stop loop immediately
             * if the correct number is guessed.
             */

            if("CORRECT".equals(result)){
                break;
            }
        }
    }
}