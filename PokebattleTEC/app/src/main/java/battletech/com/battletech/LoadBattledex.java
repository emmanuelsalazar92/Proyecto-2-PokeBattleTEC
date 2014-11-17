package battletech.com.battletech;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

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


public class LoadBattledex extends AsyncTask<Integer, Void, Boolean> {

    private Activity activity;
    private BattledexListAdapter adapter;
    private List<Battledex> movies = new ArrayList<Battledex>();
    private String URL = "http://poke-battle.herokuapp.com/api/battle_record/";
    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response;
    String[] _result = {"Win","Lose"};
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

    int idUsuario;
    public LoadBattledex(BattleTechActivityMain activity, BattledexListAdapter adapter, int id){
        Log.d("-------------------", "LoadBattle");
        this.activity = activity;
        this.adapter = adapter;
        this.idUsuario = id;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Boolean doInBackground(Integer... parameters) {
        //int npagina = parameters[0];
        String url_api = URL+idUsuario;
        Log.d("-------------------",url_api);
        //String nuevaURL = "http://poke-battle.herokuapp.com/api/users/"+
        try {
            response = httpclient.execute(new HttpGet(url_api));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                JSONObject jsonObj = new JSONObject(EntityUtils.toString(response.getEntity()));
                JSONArray jArray = jsonObj.getJSONArray("battlerecord");
                for(int i=0; i < jArray.length(); i++) {
                    JSONObject jObject = jArray.getJSONObject(i);
                    String name = jObject.getString("pokemon");
                    Log.d("-------------------",name);
                    String result = jObject.getString("result");
                    Log.d("-------------------",result);
                    String date = jObject.getString("date");
                    Log.d("-------------------",date);
                    //String image = jObject.getString("CoverImage");
                    Battledex p = new Battledex();
                    p.set_SPokemon(_PokemonName[Integer.parseInt(name)-1]);
                    p.set_SResult(_result[Integer.parseInt(result)]);
                    p.set_SDate(date);
                    // p.setImage(image);
                    this.movies.add(p);
                }
                Log.d("-------------------","true");
                return true;
            }else{
                Log.d("-------------------","false");
                return false;
            }
        }catch (Exception e){
            Log.d("-------------------","Catch");
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            //adapter.setData();
            adapter.setData(movies);
        }
    }
}