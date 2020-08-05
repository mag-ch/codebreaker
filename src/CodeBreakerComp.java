import java.util.ArrayList;
import java.util.Random;

public class CodeBreakerComp extends CodeBreakerModel {

  Strategy computer = new Algorithm();
  int[] code;
  int[] attempt;

  public CodeBreakerComp(int codeLength, Algorithm computer) {
    super( codeLength );
    this.computer = computer;
    code = new int[codeLength];
    Random rd = new Random();
    for (int i = 0; i < code.length; i++) {
      code[i] = rd.nextInt(10); // storing random integers in an array
    }
  }

  public int[] readAttempt(int[] attempt) {
    computer.readAttempt( attempt );
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
