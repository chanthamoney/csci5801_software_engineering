import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class TestIRV {
  ArrayList<ArrayList<Integer>> testBallots;
  String[] candidates = {"Naruto", "Sasuke", "Sakura", "Kakashi"};

  private IRV intitializeTestIRV() {
    ArrayList<Integer> firstBallot = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    ArrayList<Integer> secondBallot = new ArrayList<>(Arrays.asList(3, 1, 2, 4));
    ArrayList<Integer> thirdBallot = new ArrayList<>(Arrays.asList(2, 1, 3, 4));
    ArrayList<Integer> fourthBallot = new ArrayList<>(Arrays.asList(4, 3, 2, 1));
    ArrayList<Integer> fifthBallot = new ArrayList<>(Arrays.asList(4, 3, 1, 2));
    ArrayList<Integer> sixthBallot = new ArrayList<>(Arrays.asList(1, 2, 4, 3));


    testBallots.add(firstBallot);
    testBallots.add(secondBallot);
    testBallots.add(thirdBallot);
    testBallots.add(fourthBallot);
    testBallots.add(fifthBallot);
    testBallots.add(sixthBallot);

    return new IRV(testBallots.size(), candidates.length, candidates, testBallots);
  }

  // _QUESTION: Do we test private functions?? - Maybe not bc they are used in the public methods?

  // Testing runElection() only public method
  @Test
  public void testRunElection(){
    // Initialize IRV Election
    IRV hokageElection = intitializeTestIRV();
    String hokage = "not a winner";

    // Run election on irvElection
    try {
      hokage = hokageElection.runElection();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    // String hokage = hokageElection.runElection();

    // check if winner is as expected
    assertEquals("Sasuke", hokage);
  }
}
