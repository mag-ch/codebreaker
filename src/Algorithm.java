import java.util.ArrayList;
import java.util.List;

public class Algorithm implements Strategy {

  int[] code;
  int[] attempt;
  //saves the last number checked
  int temp;

  public Algorithm() {
    attempt = new int[code.length];
    temp = code.length;
  }

  public int[] readAttempt(int[] attempt) {
    int[] result = new int[code.length];
    //right place, right number
    int black = 0;
    //wrong place
    int white = 0;
    ArrayList<Integer> temp = new ArrayList<>( );
    for (int i = 0; i < code.length; i++) {
      if (attempt[i] == this.code[i]) {
        result[i] = 1;
      }
      else {
        result[i] = 0;
        temp.add( code[i] );
      }
    }
    for (int i = 0; i < code.length; i++) {
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

  public int[] generateAttempt(int[] result, int[] prevAttempt) {
    if (prevAttempt.length != code.length) {
      throw new IllegalArgumentException( "attempt length does not match code length" );
    }
    for (int i = code.length/2 - 1; i < code.length; i++) {
      prevAttempt[i] = temp++ % 10;
    }
    return prevAttempt;
  }

  public boolean isGameOver() {
    boolean match = true;
    for (int i = 0; i < code.length; i++) {
      match = (attempt[i] == code[i]) && match;
    }
    return match;
  }
}
