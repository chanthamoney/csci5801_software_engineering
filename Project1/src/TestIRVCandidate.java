import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

public class TestIRVCandidate {
  IRVCandidate testCandidate = new IRVCandidate("Jenny");
  ArrayList<Integer> testVotes = new ArrayList<Integer>(); // to intitialize the IRVBallot needed for testing
  int testId = 4;
  IRVBallot testBallot;

  private void initializeTestBallot() {
    // add votes to testVotes
    testVotes.add(1);
    testVotes.add(2);
    testVotes.add(3);

    testBallot = new IRVBallot(testVotes, testId);
  }

  // Testing AddBallot
  @Test
  public void testAddBallot() {
    // initializeTestBallot
    initializeTestBallot();

    // test AddBallot() with the testBallot
    testCandidate.addBallot(testBallot);

    // Check if elim ballots(0)/elim ballots(numBallots-1) == testBallot
    assertEquals(testBallot, testCandidate._elimBallots.get(1-1));
  }

  // Testing eliminate method
  @Test
  public void testEliminate() {
    // initializeTestBallot
    initializeTestBallot();

    // add ballot to test candidate
    testCandidate.addBallot(testBallot);

    // eliminate candidate and get their ballot
    IRVBallot[] eliminatedBallot = testCandidate.eliminate();

    // expected value an array list of irv ballots
    IRVBallot[] expectedEliminatedBallot = new IRVBallot[1];
    expectedEliminatedBallot[0] = testBallot;

    // check if eliminated Ballot == testBallot
    assertArrayEquals(expectedEliminatedBallot, eliminatedBallot);
  }

  // Testing isEliminated method
  @Test
  public void testIsEliminated() {
    // initialize test ballot
    initializeTestBallot();

    // add test ballots
    testCandidate.addBallot(testBallot);

    // eliminate candidate
    testCandidate.eliminate();

    // Check if candidate returns true to isEliminated
    assertTrue(testCandidate.isEliminated());
  }

  // Testing getName() method of candidate class that IRVCandidate extends
  @Test
  public void testGetName() {
    assertEquals("Jenny", testCandidate.getName());
  }

  // Testing getNumVotes() method of candidate class that IRVCandidate extends
  @Test
  public void testGetNumVotes() {
    // initialize testBallot
    initializeTestBallot();

    // add ballot to test candidate
    testCandidate.addBallot(testBallot);

    // check num votes is as expected
    assertEquals(1, testCandidate.getNumVotes());
  }
}
