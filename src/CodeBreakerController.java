import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CodeBreakerController {
  private Readable in;
  private final Scanner scan;
  private CodeBreakerModel model;

  public CodeBreakerController(Readable in) {
    this.in = in;
    this.scan = new Scanner( in );
  }

  // user first selects length of code to play with,
  // then inputs attempts as an array of digits
  public void playGame() {
    System.out.println( "Enter number of digits: " );

    int digits = scan.nextInt();  // Read user input
    System.out.println( "Code length: " + digits + "\nAttempt:" );  // Output user input

    this.model = new CodeBreakerModel( digits );

    this.readInput();

    while (!model.isGameOver()) {
      readInput();
    }
    System.out.println( "YES YOU'RE A GENIUS" );
    if (playAgain()) {
      this.playGame();
    } else {
      scan.close();
      System.exit( 1 );
    }
  }

  public int[] readInput() {
    int[] attempt = new int[model.codeLength];
    for (int i = 0; i < model.codeLength; i++) {
      try {
        int userInput = scan.nextInt();
        attempt[i] = userInput;
        model.attempt = attempt;
      } catch (InputMismatchException e) {
        //must allow users to retry
        if (scan.hasNext( "q" )) {
          System.out.println( Arrays.toString( model.code ) );
          scan.next();
          if (playAgain()) {
            this.playGame();
          } else {
            scan.close();
            System.exit( 2 );
          }
        } else {
          System.out.println( "Incorrect input, try again" );
          scan.next();
        }
      }
    }

    int[] result = model.readAttempt( attempt );
    System.out.println( Arrays.toString( result ) );
    return result;
  }

  public boolean playAgain() {
    System.out.println( "Play Again? (y/n): " );
    String v = scan.next();
    return (v.equals( "y" ));
  }
}
