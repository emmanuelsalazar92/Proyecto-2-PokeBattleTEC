


package battletech.com.battletech;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BattleActivity extends Activity {
    public ProgressBar _ourProgress;
    public ProgressBar _enemyProgress;
    int _enemyStatus = 100;
    int experiencia = 30;
    int _ourStatus = 100;
    int Experiecne;
    int enemigo;
    int usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Intent act = getIntent();
        TextView _enemigo = (TextView) findViewById(R.id.xTVTittle);
        _enemigo.setText(act.getStringExtra("PokemonEnemigo"));
        TextView _nuestro = (TextView) findViewById(R.id.xTVEmailSign);
        _nuestro.setText(act.getStringExtra("PokemonNuestro"));
        enemigo = Integer.parseInt(act.getStringExtra("enemigo"));
        usuario = Integer.parseInt(act.getStringExtra("usuario"));

        ImageButton _imgEnemigo = (ImageButton) findViewById(R.id.imgEnemigo);
        _imgEnemigo.setImageResource(Integer.parseInt(act.getStringExtra("ImagenPokemonEnemigo")));
        ImageButton _imgNuestro = (ImageButton) findViewById(R.id.imgNuestro);
        _imgNuestro.setImageResource(Integer.parseInt(act.getStringExtra("ImagenPokemonNuestro")));


        _ourProgress = (ProgressBar) findViewById(R.id.pbprogresoNuestro);
        _ourProgress.setProgress(_ourStatus);
        _enemyProgress = (ProgressBar) findViewById(R.id.pbprogresoEnemigo);
        _enemyProgress.setProgress(_enemyStatus);

        new GetExperience().execute();


        Log.d("ACTIVIDAD",Integer.toString(Experiecne));

    }
    @Override
    public  void onBackPressed() {
        super.onBackPressed();
        Intent act = new Intent(this, BattleTechActivityMain.class);
        startActivity(act);

    }
    public int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void BattleAttack(View pview) {
        TextView _enemigo = (TextView) findViewById(R.id.puntosenemigo);
        TextView _nuestro = (TextView) findViewById(R.id.puntosnuestro);
        int randomEn = randInt(0, 5);
        int randomAm = randInt(0, 10);
        int ataqueen = (experiencia / 10) * randomAm; //random entre 0-10
        int ataqueam = (experiencia / 10) * randomEn; //random entre 0-10
        if (_enemyStatus > 0 && _ourStatus > 0) {
            _enemyStatus -= ataqueen;
            _ourStatus -= ataqueam;
            _enemigo.setText(Integer.toString(_enemyStatus) + "/100");
            _nuestro.setText(Integer.toString(_ourStatus) + "/100");
        } else if (_enemyStatus <= 0)
        {
            new PostPokemonBattle(this, enemigo, getString(R.string.ssap), 1,usuario).execute("1");
            Intent ac = new Intent(this, BattleTechActivityMain.class);
            ac.putExtra("usuario", "2");
            startActivity(ac);
        }
        else {new PostPokemonBattle(this,enemigo,getString(R.string.ssap),0,usuario).execute("1");
            Intent ac = new Intent(this, BattleTechActivityMain.class);
            ac.putExtra("usuario", "2");
            startActivity(ac);}
    }

    }
