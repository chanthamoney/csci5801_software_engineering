import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;

public class TestIRVBallot {
  // initialize an arraylist of votes and an id
  ArrayList<Integer> testVotes = new ArrayList<Integer>();
  int testId = 4;

  private IRVBallot testBallotInitialize() {
    // initialize testVotes
    testVotes.add(1);
    testVotes.add(2);
    testVotes.add(3);
    testVotes.add(4);

    return new IRVBallot(testVotes, testId);

  }

  // Testing getNextVote()
  @Test
  public void testGetNextVote() {
    // initialize test IRVBallot
    IRVBallot testBallot = testBallotInitialize();

    // test get next vote is as expected
    int nextVote = testBallot.getNextVote();
    assertEquals(testVotes.get(0), nextVote, 0.0);
    assertEquals(1, nextVote, 0.0);
  }

  // Testing is exhausted method
  @Test
  public void testIsExhausted()
  {
    // initalize testBallot
    IRVBallot testBallot = testBallotInitialize();

    assertFalse(testBallot.isExhausted());
  }

  // Testing getID() method of ballot class that irv ballot extends
  @Test
  public void testGetID() {
    // initialize Test Ballot
    IRVBallot testBallot = testBallotInitialize();

    assertEquals(4, testBallot.getID());
  }


}
