package ca.ualberta.cs.lonelytwitter.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import ca.ualberta.cs.lonelytwitter.IntentReaderActivity;


public class IntentReaderActivityTest1 extends
		ActivityInstrumentationTestCase2<IntentReaderActivity>
{

	public IntentReaderActivityTest1(){
		super(IntentReaderActivity.class);
	}
	
	public void testSendText()
	{
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "stringaling");
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.NORMAL);
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals("stringaling", activity.getText());
	}
	
	public void testDoubleText() {
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "stringaling");
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.DOUBLE);
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals("stringalingstringaling", activity.getText());
	}
	
	public void testDisplayText() {
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "stringaling");
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.NORMAL);
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals("stringaling", activity.getView().getText());
	}
	
	public void testReverseText() {
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "stringaling");
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.REVERSE);
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals("gnilagnirts", activity.getText());
	}
	
	public void testDisplatText2(){
		final IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		try
		{
			runTestOnUiThread(new Runnable() {
				@Override
				public void run() {
					activity.getView().setText("some string");
				}
			});
		} catch (Throwable e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("some string", activity.getView().getText());
	}
	
	public void testDefaultText() {
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.NORMAL);
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals("default", activity.getView().getText());	
	}

	public void actuallyOnScreen() {
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.NORMAL);
		setActivityIntent(intent);
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), (View)activity.getView());
	}
	
}
