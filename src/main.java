import java.io.InputStreamReader;

public class main {
  public static void main(String args[]) {
    CodeBreakerController controller = new CodeBreakerController( new InputStreamReader(System.in) );
    controller.playGame( );
  }
}
