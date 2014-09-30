import java.util.ArrayList;
import java.util.Date;

import android.test.ActivityInstrumentationTestCase2;

import ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity;
import ca.ualberta.cs.lonelytwitter.TweetListModel;
import ca.ualberta.cs.lonelytwitter.LonelyTweetModel;


public class TweetListModelTest extends ActivityInstrumentationTestCase2<LonelyTwitterActivity>
{
	public TweetListModelTest() {
		super(LonelyTwitterActivity.class);
	}

	public void testFiveIsFive() {
		assertEquals(5,5);
	}
	
	public void testAddTweets() {
		TweetListModel tlm = new TweetListModel();
		tlm.add(new LonelyTweetModel("Test"));
		assertEquals(tlm.getCount(), 1);
		
	}
	
	public void testGetItem() {
		TweetListModel tlm = new TweetListModel();
		LonelyTweetModel firstTweet = new LonelyTweetModel("test");
		tlm.add(firstTweet);
		assertSame(firstTweet, tlm.getItem(1));
	}
	
	public void testAddTweet() {
		TweetListModel tlm = new TweetListModel();
		LonelyTweetModel testTweet = new LonelyTweetModel("same");
		tlm.add(testTweet);
		boolean caught = false;
		try {
			tlm.add(testTweet);
		}
		catch (IllegalArgumentException iae) {
			caught = true;
		}
		assertSame(caught, true);
	}
	
	public void testHasTweet() {
		TweetListModel tlm = new TweetListModel();
		LonelyTweetModel testTweet = new LonelyTweetModel("test");
		tlm.add(testTweet);
		assertSame(tlm.hasTweet(testTweet), true);
		tlm.remove(testTweet);
		assertSame(tlm.hasTweet(testTweet), false);
	}
	
	public void testGetTweets() {
		ArrayList<LonelyTweetModel> compare;
		Date test1;
		Date test2;
		TweetListModel tlm = new TweetListModel();
		for (int i = 0; i < 15; i++){
			Date date = new Date(2000-i, 12-i, 29-i);
			LonelyTweetModel test = new LonelyTweetModel(Integer.toString(i), date);
			tlm.add(test);
		}
		compare = tlm.getTweets();
		for(int i = 0; i < tlm.getCount()-1; i++) {
			assertTrue(compare.get(i).getTimestamp().before(compare.get(i+1).getTimestamp()) || 
					compare.get(i).getTimestamp().equals(compare.get(i+1).getTimestamp()) );
		}
		
	}
	
	public void testGetCount() {
		TweetListModel tlm = new TweetListModel();
		LonelyTweetModel testTweet = new LonelyTweetModel("test");
		tlm.add(testTweet);
		assertSame(tlm.getCount(), 1);
		testTweet = new LonelyTweetModel("test2");
		tlm.add(testTweet);
		assertSame(tlm.getCount(), 2);
		
		
	}
	
}
