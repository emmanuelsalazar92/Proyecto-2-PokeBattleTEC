/*
/////////////////////////////////////////////////////////////////////
///INSTITUTO TENOLÓGICO DE COSTA RICA////////////////////////////////
///ESCUELA DE INGENIERÍA EN COMPUTACIÓN//////////////////////////////
///DESARROLLO DE APLICACIÓNES MÓVILES////////////////////////////////
///PROFESOR: ANDREI FUENTES//////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
///ALUMNOS://////////////////////////////////////////////////////////
///////////GABRIEL MADRIZ MASIS//////////////////////////////////////
///////////JAVIER SAENZ ROJAS////////////////////////////////////////
///////////EMMANUEL SALAZAR AGUERO///////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
*/
//******************************************************************
//****PACKAGE*******************************************************
//******************************************************************
package battletech.com.battletech;
//******************************************************************
//****IMPORTS*******************************************************
//******************************************************************
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
//******************************************************************
//****CLASS*********************************************************
//******************************************************************
public class BattleTechActivityMain extends Activity implements TextWatcher
    {
        ListView _LVPokedexList;
        ListView _LVBattledexList;
        TextView _TVPkemonSearch;
        AutoCompleteTextView _ACTVPokemonSearch;
        PokemonListAdapter _PLAPokedexList;
        BattledexListAdapter _PLABattledexList;
        List<Pokemon> _LPokemonList;
        List<Battledex> _LBattledexList;
        int _IPokemonUserID;
        int _IStartPOkemonID = 0;
        private TabHost _THTittleTab;
        int[] _data = {1,4,0};
        String[] _SPokemonNames =
        {"Bulbasaur","Ivysaur","Venusaur","Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise",
        "Caterpie","Metapod","Butterfree","Weedle","Kakuna","Beedrill","Pidgey","Pidgeotto","Pidgeot",
        "Rattata","Raticate","Spearow","Fearow","Ekans","Arbok","Pikachu","Raichu","Sandshrew",
        "Sandslash","Nidoran","Nidorina","Nidoqueen","Nidoran","Nidorino","Nidoking","Clefairy","Clefable",
        "Vulpix","Ninetales","Jigglypuff","Wigglytuff","Zubat","Golbat","Oddish","Gloom","Vileplume",
        "Paras","Parasect","Venonat","Venomoth","Diglett","Dugtrio","Meowth","Persian","Psyduck",
        "Golduck","Mankey","Primeape","Growlithe","Arcanine","Poliwag","Poliwhirl","Poliwrath","Abra",
        "Kadabra","Alakazam","Machop","Machoke","Machamp","Bellsprout","Weepinbell","Victreebel","Tentacool",
        "Tentacruel","Geodude","Graveler","Golem","Ponyta","Rapidash","Slowpoke","Slowbro","Magnemite",
        "Magneton","Farfetch'd","Doduo","Dodrio","Seel","Dewgong","Grimer","Muk","Shellder",
        "Cloyster","Gastly","Haunter","Gengar","Onix","Drowzee","Hypno","Krabby","Kingler",
        "Voltorb","Electrode","Exeggcute","Exeggutor","Cubone","Marowak","Hitmonlee","Hitmonchan","Lickitung",
        "Koffing","Weezing","Rhyhorn","Rhydon","Chansey","Tangela","Kangaskhan","Horsea","Seadra",
        "Goldeen","Seaking","Staryu","Starmie","Mr. Mime","Scyther","Jynx","Electabuzz","Magmar",
        "Pinsir","Tauros","Magikarp","Gyarados","Lapras","Ditto","Eevee","Vaporeon","Jolteon",
        "Flareon","Porygon","Omanyte","Omastar","Kabuto","Kabutops","Aerodactyl","Snorlax","Articuno",
        "Zapdos","Moltres","Dratini","Dragonair","Dragonite","Mewtwo","Mew"};
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreate
//++++DESCRIPCION....................................................
//..........METODO Inicial del Main Activity
//++++PARAMETROS.....................................................
//..........Bundle(pSavedInstanceState)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected void onCreate(Bundle pSavedInstanceState)
            {
                super.onCreate(pSavedInstanceState);
                setContentView(R.layout.activity_main);
                Intent act = getIntent();
                _IPokemonUserID =  Integer.parseInt(act.getStringExtra("usuario"));
                int pokemon = _data[_IPokemonUserID-1];
                if(pokemon == 0)
                {
                    Intent _IMapsActivity = new Intent(this, ChoosePokemonActivity.class);
                    startActivity(_IMapsActivity);
                }
                else
                {
                    AddingTabs();
                    PokedexCall();
                    BattledexCall();
                    PokemonSearch();
                }
                Log.d("BattleTech", "MAIN--On Create");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........PokedexCall
//++++DESCRIPCION....................................................
//..........METODO Agrega el Pokedex al Main Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public void BattledexCall()
        {
            Log.d("-------------------","-");
            _LVBattledexList = (ListView) findViewById(R.id.listBattledex);
            _LBattledexList = new ArrayList<Battledex>();
            _PLABattledexList = new BattledexListAdapter(this, _LBattledexList);
            _LVBattledexList.setAdapter(_PLABattledexList);
            new LoadBattledex(this, _PLABattledexList, _IPokemonUserID).execute(1);
            Log.d("-------------------","-");
        }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........PokedexCall
//++++DESCRIPCION....................................................
//..........METODO Agrega el Pokedex al Main Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public void PokedexCall()
            {
                _LVPokedexList = (ListView) findViewById(R.id.listPokedex);
                _LPokemonList = new ArrayList<Pokemon>();
                _PLAPokedexList = new PokemonListAdapter(this, _LPokemonList);
                _LVPokedexList.setAdapter(_PLAPokedexList);
                new LoadPokedex(this, _PLAPokedexList, _IPokemonUserID).execute(1);
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........PokemonSearch
//++++DESCRIPCION....................................................
//..........METODO Agrega un listener del autocompletado Main Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public void PokemonSearch()
            {
                _TVPkemonSearch = (TextView) findViewById(R.id.tvseleccion);
                _ACTVPokemonSearch = (AutoCompleteTextView) findViewById(R.id.pokemonname);
                _ACTVPokemonSearch.addTextChangedListener(this);
                _ACTVPokemonSearch.setAdapter(new ArrayAdapter<String>(this, R.layout.abc_simple_dropdown_hint, _SPokemonNames));
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........AddingTabs
//++++DESCRIPCION....................................................
//..........METODO Para manejar agregar tabs del Main Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public void AddingTabs()
            {
                _THTittleTab =(TabHost) findViewById(R.id.TabHost01);
                _THTittleTab.setup();
                TabHost.TabSpec _THTSSpec = _THTittleTab.newTabSpec("tab_creation");
                _THTSSpec.setIndicator("Battle", getResources().getDrawable(android.R.drawable.ic_menu_add));
                _THTSSpec.setContent(R.id.tabBattle);
                _THTittleTab.addTab(_THTSSpec);
                _THTittleTab.addTab(_THTittleTab.newTabSpec("tab_inser").setIndicator("Pokedex", getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.tabPokedex));
                _THTittleTab.addTab(_THTittleTab.newTabSpec("tab_inser").setIndicator("Battledex",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.tabBattledex));
                _THTittleTab.addTab(_THTittleTab.newTabSpec("tab_inser").setIndicator("Search",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.tabSearch));
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........ShowMapOBattle
//++++DESCRIPCION....................................................
//..........METODO Es la respuesta OnClick para mostrar el mapa
//++++PARAMETROS.....................................................
//..........View (pView)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public void ShowMapOBattle(View pView)
            {
                Intent _IMapsActivity = new Intent(this, MapsActivity.class);
                _IMapsActivity.putExtra("usuario",Integer.toString(_IPokemonUserID));
                startActivity(_IMapsActivity);
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........beforeTextChanged
//++++DESCRIPCION....................................................
//..........METODO Herencia del TextWatcher
//++++PARAMETROS.....................................................
//..........CharSequence (s), int (start,count,after)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                //
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onTextChanged
//++++DESCRIPCION....................................................
//..........METODO Heredado del TextWatcher
//++++PARAMETROS.....................................................
//..........CharSequence (s), int (start,before,count)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                _TVPkemonSearch.setText(_ACTVPokemonSearch.getText());
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........afterTextChanged
//++++DESCRIPCION....................................................
//..........METODO Herencia del TextWatcher
//++++PARAMETROS.....................................................
//..........Editable (s)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        public void afterTextChanged(Editable s)
            {
                //
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onPause
//++++DESCRIPCION....................................................
//..........METODO Para manejar Pausas del Main Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected void onPause()
            {
                super.onPause();
                Log.d("BattleTech", "MAIN--On Pause");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onResume
//++++DESCRIPCION....................................................
//..........METODO Para manejar las Reanudaciones del MAIN Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onResume()
            {
                super.onResume();
                Log.d("BattleTech", "MAIN--On Resume");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStop
//++++DESCRIPCION....................................................
//..........METODO Para manejar deteciones del MAIN Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStop()
            {
                super.onStop();
                Log.d("BattleTech", "SPLASH--On Stop");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el inicio del Main Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStart()
            {
                super.onStart();
                Log.d("BattleTech", "MAIN--On Start");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onRestart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el reinicio del Main Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onRestart()
            {
                super.onRestart();
                Log.d("BattleTech", "MAIN--On Restart");
            }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onDestroy
//++++DESCRIPCION....................................................
//..........METODO Para manejar la destrucción del Main Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onDestroy()
            {
                super.onDestroy();
                Log.d("BattleTech", "MAIN--On Destroy");
            }


        public void BusquedaAd(View pview)
        {
            Intent act = new Intent(this,AdvanceSearch.class);

            startActivity(act);
        }


    }