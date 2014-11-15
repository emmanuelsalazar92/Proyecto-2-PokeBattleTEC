package battletech.com.battletech;


import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
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

public class LoadPokedex extends AsyncTask<Integer, Void, Boolean> {

    private Activity activity;
    private PokemonListAdapter adapter;
    private List<Pokemon> movies = new ArrayList<Pokemon>();
    private String URL = "http://poke-battle.herokuapp.com/api/users/1/pokedexes";
    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response;

    public LoadPokedex(MainActivity activity, PokemonListAdapter adapter){
        this.activity = activity;
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Boolean doInBackground(Integer... parameters) {
        int npagina = parameters[0];
        String url_api = URL;
        try {
            response = httpclient.execute(new HttpGet(url_api));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                JSONObject jsonObj = new JSONObject(EntityUtils.toString(response.getEntity()));
                JSONArray jArray = jsonObj.getJSONArray("pokedex");
                for(int i=0; i < jArray.length(); i++) {
                    JSONObject jObject = jArray.getJSONObject(i);
                    String name = jObject.getString("name");
                    //String image = jObject.getString("CoverImage");
                    Pokemon p = new Pokemon();
                    p.set_NamePokemon(name);
                    // p.setImage(image);
                    this.movies.add(p);
                }
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            adapter.setData(movies);
        }
    }
}