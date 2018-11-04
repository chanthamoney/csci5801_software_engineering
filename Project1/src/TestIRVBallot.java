import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse; 
import java.util.ArrayList;

public class TestIRVBallot {
  // initialize an arraylist of votes and an id
  ArrayList<Integer> testVotes = new ArrayList<Integer>();
  int testId = 4;
  IRVBallot ballot = new IRVBallot(testVotes, testId);

  // Testing getNextVote()
  @Test
  public void testGetNextVote() {
    // add votes to testVotes
    testVotes.add(1);
    testVotes.add(2);
    testVotes.add(3);
    testVotes.add(4);

    // test get next vote is as expected
    int nextVote = ballot.getNextVote();
    assertEquals(testVotes.get(0), nextVote, 0.0);
    assertEquals(1, nextVote, 0.0);
  }

  // Testing is exhausted method
  public void testIsExhausted() {
    assertFalse(ballot.isExhausted());
  }


}
