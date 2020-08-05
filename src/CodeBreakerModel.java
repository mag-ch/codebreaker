import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;

/** model for the game, code breakers
 * There is a randomly generated 4 digit code, comprised of digits 0-9
 * The user will input their guess into the console
 * The game will return the number of digits that are correctly placed (2),
 * the number of digits incorrectly placed (1) and the incorrect digits (0)
 *    Example:
 *    Code {1, 4, 5, 2}
 *    User Input: {1, 2, 9, 9}
 *    Return: {2, 1, 0, 0}
 * The user can try as many times before getting the correct code
 */
public class CodeBreakerModel implements Strategy{
  int codeLength;
  int[] code;
  int[] attempt;

  public CodeBreakerModel(int codeLength) {
    this.codeLength = codeLength;
    code = new int[codeLength];
    Random rd = new Random();
    for (int i = 0; i < code.length; i++) {
      code[i] = rd.nextInt(10); // storing random integers in an array
    }
  }

  public int[] readAttempt(int[] attempt) {
    int[] result = new int[codeLength];
    //right place, right number
    int black = 0;
    //wrong place
    int white = 0;
    ArrayList<Integer> temp = new ArrayList<>( );
    for (int i = 0; i < codeLength; i++) {
      if (attempt[i] == this.code[i]) {
        result[i] = 1;
      }
      else {
        result[i] = 0;
        temp.add( code[i] );
      }
    }
    for (int i = 0; i < codeLength; i++) {
      if (result[i] == 0) {
        if (temp.contains( attempt[i] )) {
          white ++;
        }
      }
      else if (result[i] == 1) {
        black ++;
      }
    }
    return new int[]{black, white};
  }

  public boolean isGameOver() {
    int[] result = this.readAttempt( attempt );
    return result[0] == codeLength;
  }

}
