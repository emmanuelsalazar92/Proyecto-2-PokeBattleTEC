package battletech.com.battletech;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by Emmanuel on 16/11/2014.
 */  public class GetExperience extends AsyncTask<Void, Void, Boolean>
{
    private String URL = "http://poke-battle.herokuapp.com/api/users/1";
    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response;
    int prueba;
    @Override
    protected Boolean doInBackground(Void... parameters) {
        String url_api = URL;
        try {
            response = httpclient.execute(new HttpGet(url_api));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                JSONObject jsonObj = new JSONObject(EntityUtils.toString(response.getEntity()));
                JSONArray jArray = jsonObj.getJSONArray("user");
                for(int i=0; i < jArray.length(); i++) {
                    JSONObject jObject = jArray.getJSONObject(i);
                    String experiencia = jObject.getString("experience");
                    int exp = Integer.parseInt(experiencia);
                    this.prueba = exp;

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
    protected void onPostExecute(Boolean result)
    {
    }


}
