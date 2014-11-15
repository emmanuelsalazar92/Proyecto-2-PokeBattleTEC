package battletech.com.battletech;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;


public class TwitterActivity extends Activity {
    SharedPreferences pref;
    private static String CONSUMER_KEY = "CZuRhHze4Yi3CVGHz9xLoVGVW";
    private static String CONSUMER_SECRET = "wrIxJlEEiEnAV9Zh9hQa0XPSQYhj2brLWwrZBXxmZ6MMo0kUFt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        pref = getPreferences(0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("CONSUMER_KEY", CONSUMER_KEY);
        edit.putString("CONSUMER_SECRET", CONSUMER_SECRET);
        edit.commit();
        Fragment login = new FragTwitterLogin();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, login);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }
}