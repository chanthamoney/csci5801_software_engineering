import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.*;

public class TestIRV {
  ArrayList<ArrayList<Integer>> testBallots = new ArrayList<ArrayList<Integer>>();
  String[] candidates = {"Naruto", "Sasuke", "Sakura", "Kakashi"};

  private IRV intitializeTestIRV() {
    ArrayList<Integer> firstBallot = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
    ArrayList<Integer> secondBallot = new ArrayList<>(Arrays.asList(1, 0, 2, 4));
    ArrayList<Integer> thirdBallot = new ArrayList<>(Arrays.asList(3, 2, 1, 0));
    ArrayList<Integer> fourthBallot = new ArrayList<>(Arrays.asList(1, 0, 2, 3));
    ArrayList<Integer> fifthBallot = new ArrayList<>(Arrays.asList(2, 1, 0, 3));
    ArrayList<Integer> sixthBallot = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

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
  // _QUESTION: what is printed to console? Election winner: name or just name?

  @Test
  public void testRunElection(){
    // Initialize IRV Election
    IRV hokageElection = intitializeTestIRV();

    // Keep current System.out
    PrintStream oldOut = System.out;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    // Change so System.out saved in baos
    System.setOut(new PrintStream(baos));

    // Run election on irvElection
    try {
      hokageElection.runElection();
    }
    catch(IOException e) {
      e.printStackTrace();
    }

    // Reset the System.out to console
    System.setOut(oldOut);

    // baos contains winner printed from the runElection function
    String output = new String(baos.toByteArray());

    // check if winner is as expected
    assertEquals("Sasuke", output);

    System.out.println(output); // prints winner from function 
  }
}
