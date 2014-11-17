package battletech.com.battletech;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class ChoosePokemonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pokemon);
    }
    public void BulbasaurOnClick(View pView)
    {
        Log.d("BulbasaurOnClickBulbasaurOnClickBulbasaurOnClickBulbasaurOnClickBulbasaurOnClick", "SIGNIN--On Pause");
        new PostPokemonChosen(this, "1",getString(R.string.ssap) ).execute("1");
    }
    public void CharmanderOnClick(View pView)
    {
        Log.d("CharmanderOnClickCharmanderOnClickCharmanderOnClickCharmanderOnClickCharmanderOnClick", "SIGNIN--On Pause");
        new PostPokemonChosen(this, "4",getString(R.string.ssap) ).execute("1");
    }
    public void SquirtleOnClick(View pView)
    {
        Log.d("SquirtleOnClickSquirtleOnClickSquirtleOnClickSquirtleOnClickSquirtleOnClick", "SIGNIN--On Pause");
        new PostPokemonChosen(this, "7",getString(R.string.ssap) ).execute("1");
    }


}
