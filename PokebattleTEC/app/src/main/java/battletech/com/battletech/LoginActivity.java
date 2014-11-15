package battletech.com.battletech;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.common.*;
import com.google.android.gms.common.GooglePlayServicesClient.*;
import com.google.android.gms.plus.PlusClient;


public class LoginActivity extends FragmentActivity {


    private MainFragment mainFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BattleTech", "LOGIN--On Create");
        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mainFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }


    }

    public void OpenRegister(View pView){
        Intent act = new Intent(this, RegisterActivity.class);
        startActivity(act);
    }
    public  void SingInBattleTech(View pview)
    {

        Intent act = new Intent(this, SignInActivity.class);
        startActivity(act);
    }
    public  void OpenTwitter(View pview)
    {

        Intent act = new Intent(this, TwitterActivity.class);
        startActivity(act);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        Log.d("BattleTech", "LOGIN--On Resume");
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.

        Log.d("BattleTech", "LOGIN --On Pause");
        AppEventsLogger.deactivateApp(this);
    }
    @Override
    protected  void  onStop()
    {
        super.onStop();
        Log.d("BattleTech", "LOGIN--On Stop");
    }
    @Override
    protected  void  onStart()
    {
        super.onStart();
        Log.d("BattleTech", "LOGIN--On Start");
    }
    @Override
    protected  void  onRestart()
    {
        super.onRestart();
        Log.d("BattleTech", "LOGIN--On Restart");
    }
    @Override
    protected  void  onDestroy()
    {
        super.onDestroy();
        Log.d("BattleTech", "LOGIN--On Destroy");
    }
    @Override
    public  void onBackPressed()
    {
        //super.onBackPressed();
        Log.d("BattleTech", "LOGIN--On Back Pressed");
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        LoginActivity.super.onBackPressed();
                    }
                }).create().show();
    }



}
