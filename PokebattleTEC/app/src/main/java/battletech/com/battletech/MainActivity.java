package battletech.com.battletech;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements TextWatcher{

    ListView lv;
    TextView selection;
    AutoCompleteTextView edit;
    PokemonListAdapter adapter;
    List<Pokemon> movies;

    private TabHost mytabhost;
    String[] _PokemonName={"Bulbasaur","Ivysaur","Venusaur","Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise",
            "Caterpie","Metapod","Butterfree","Weedle","Kakuna","Beedrill","Pidgey","Pidgeotto","Pidgeot",
            "Rattata","Raticate","Spearow","Fearow","Ekans","Arbok","Pikachu","Raichu","Sandshrew",
            "Sandslash","Nidoran","Nidorina","Nidoqueen","Nidoran","Nidorino","Nidoking","Clefairy",
            "Clefable","Vulpix","Ninetales","Jigglypuff","Wigglytuff","Zubat","Golbat","Oddish","Gloom","Vileplume","Paras",
            "Parasect","Venonat","Venomoth","Diglett","Dugtrio","Meowth","Persian","Psyduck","Golduck","Mankey","Primeape","Growlithe","Arcanine","Poliwag","Poliwhirl","Poliwrath","Abra",
            "Kadabra","Alakazam","Machop","Machoke","Machamp","Bellsprout","Weepinbell","Victreebel","Tentacool","Tentacruel","Geodude","Graveler","Golem","Ponyta","Rapidash",
            "Slowpoke","Slowbro","Magnemite","Magneton","Farfetch'd","Doduo","Dodrio","Seel","Dewgong","Grimer","Muk","Shellder","Cloyster","Gastly","Haunter","Gengar","Onix",
            "Drowzee","Hypno","Krabby","Kingler","Voltorb","Electrode","Exeggcute","Exeggutor","Cubone","Marowak","Hitmonlee","Hitmonchan","Lickitung","Koffing","Weezing","Rhyhorn","Rhydon","Chansey","Tangela","Kangaskhan",
            "Horsea","Seadra","Goldeen","Seaking","Staryu","Starmie","Mr. Mime","Scyther","Jynx","Electabuzz","Magmar","Pinsir","Tauros","Magikarp","Gyarados","Lapras","Ditto","Eevee",
            "Vaporeon","Jolteon","Flareon","Porygon","Omanyte","Omastar","Kabuto","Kabutops","Aerodactyl","Snorlax","Articuno","Zapdos","Moltres","Dratini","Dragonair","Dragonite","Mewtwo","Mew"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Recuperation du TabHost
        mytabhost =(TabHost) findViewById(R.id.TabHost01);
// Before adding tabs, it is imperative to call the method setup()
        mytabhost.setup();

// Adding tabs
        // tab1 settings
        TabHost.TabSpec spec = mytabhost.newTabSpec("tab_creation");
        // text and image of tab
        spec.setIndicator("Battle",getResources().getDrawable(android.R.drawable.ic_menu_add));
        // specify layout of tab
        spec.setContent(R.id.tabBattle);
        // adding tab in TabHost
        mytabhost.addTab(spec);

// otherwise :
        mytabhost.addTab(mytabhost.newTabSpec("tab_inser").setIndicator("Pokedex",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.tabPokedex));
        mytabhost.addTab(mytabhost.newTabSpec("tab_inser").setIndicator("Battledex",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.tabBattledex));
        mytabhost.addTab(mytabhost.newTabSpec("tab_inser").setIndicator("My Info",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.tabInfo));
        mytabhost.addTab(mytabhost.newTabSpec("tab_inser").setIndicator("Search",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.tabSearch));
       // mytabhost.addTab(mytabhost.newTabSpec("tab_affiche").setIndicator("Show All",getResources().getDrawable(android.R.drawable.ic_menu_view)).setContent(R.id.Onglet3));

        //ListView lv=(ListView) findViewById(R.id.listView1);
        //MyListAdapter adapter =new MyListAdapter(this);
        //lv.setAdapter(adapter);
        lv = (ListView) findViewById(R.id.list);
        movies = new ArrayList<Pokemon>();

        adapter = new PokemonListAdapter(this, movies);
        lv.setAdapter(adapter);

        new LoadPokedex(this,adapter).execute(1);
        selection = (TextView) findViewById(R.id.tvseleccion);
        edit = (AutoCompleteTextView) findViewById(R.id.pokemonname);
        edit.addTextChangedListener(this);
        edit.setAdapter(new ArrayAdapter<String>(this,R.layout.abc_simple_dropdown_hint,_PokemonName));
    }
    public void ShowMapOBattle(View view)
    {
        Intent act = new Intent(this, MapsActivity.class);
        startActivity(act);
    }
    @Override
    public  void onBackPressed()
    {
        //super.onBackPressed();
        Log.d("BattleTech", "Main--On Back Pressed");
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        selection.setText(edit.getText());

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}