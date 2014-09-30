package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class TweetListModel
{
	private ArrayList<LonelyTweetModel> tweets;
	
	public TweetListModel() {
		super();
		tweets = new ArrayList<LonelyTweetModel>();
	}
	
	public void add(LonelyTweetModel ltm) {
		if (tweets.contains(ltm)) {
			throw new IllegalArgumentException("Already existing tweet.");
		}
		tweets.add(ltm);
	}
	
	public int getCount() {
		return tweets.size();
	}
	public LonelyTweetModel getItem(int i) {
		return tweets.get(i-1);
	}
	
	public boolean hasTweet(LonelyTweetModel ltm) {
		for (int i = 0; i < tweets.size(); i++) {
			if (ltm == tweets.get(i)) {
				return(true);
			}
		}
		return(false);
	}
	
	public void remove(LonelyTweetModel ltm) {
		tweets.remove(ltm);
	}

	public ArrayList<LonelyTweetModel> getTweets()
	{
		Collections.sort(tweets, new Comparator<LonelyTweetModel>() {
		public int compare(LonelyTweetModel a, LonelyTweetModel b) {
			Date d1 = a.getTimestamp();
			Date d2 = b.getTimestamp();
			if (d1.before(d2)) {
				return -1;
			}
			else if (d1.equals(d2)) {
				return 0;
			}
			else
				return 1;
		}
	});
		return tweets;
	}
}
