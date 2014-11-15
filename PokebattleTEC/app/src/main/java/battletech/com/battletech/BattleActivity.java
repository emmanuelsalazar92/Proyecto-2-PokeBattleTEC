package battletech.com.battletech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;



public class BattleActivity extends Activity {

    public ProgressBar _ourProgress;
    public ProgressBar _enemyProgress;
    int _enemyStatus = 100;
    int _ourStatus = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Intent act = getIntent();
        TextView _enemigo = (TextView) findViewById(R.id.tvEnemigo);
        _enemigo.setText(act.getStringExtra("PokemonEnemigo"));
        TextView _nuestro = (TextView) findViewById(R.id.tvNuestro);
        _nuestro.setText(act.getStringExtra("PokemonNuestro"));

        ImageButton _imgEnemigo = (ImageButton) findViewById(R.id.imgEnemigo);
        _imgEnemigo.setImageResource(Integer.parseInt(act.getStringExtra("ImagenPokemonEnemigo")));
        ImageButton _imgNuestro = (ImageButton) findViewById(R.id.imgNuestro);
        _imgNuestro.setImageResource(Integer.parseInt(act.getStringExtra("ImagenPokemonNuestro")));


        _ourProgress = (ProgressBar) findViewById(R.id.pbprogresoNuestro);
//        _ourProgress.setMax(100);
        _ourProgress.setProgress(_ourStatus);

        _enemyProgress = (ProgressBar) findViewById(R.id.pbprogresoEnemigo);
  //      _enemyProgress.setMax(100);
        _enemyProgress.setProgress(_enemyStatus);

    }
    @Override
    public  void onBackPressed() {
        super.onBackPressed();
        Intent act = new Intent(this, MainActivity.class);
        startActivity(act);

    }
    public void BattleAttack(View pview)
    {
        /////

        _enemyStatus -= 10;
        _enemyProgress.setProgress(_enemyStatus);


        /////

        _ourStatus -= 7;
        _ourProgress.setProgress(_ourStatus);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.battle, menu);
        return true;
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
}
