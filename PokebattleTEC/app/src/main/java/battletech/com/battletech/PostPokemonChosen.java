package battletech.com.battletech;

import android.os.AsyncTask;
import android.app.Activity;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by Emmanuel on 16/11/2014.
 */
public class PostPokemonChosen extends AsyncTask<String, String, String>{

    String _SPokemonIDChosen;
    String _SPokemonUserEmail;
    public PostPokemonChosen(ChoosePokemonActivity activity,String id,String email)
    {
        Log.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "SIGNIN--On Pause");
        this._SPokemonIDChosen = id;
        this._SPokemonUserEmail = email;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("doInBackgrounddoInBackgrounddoInBackgrounddoInBackgrounddoInBackground", "SIGNIN--On Pause");
        InputStream inputStream = null;
        String result = "";

        try {
            Log.d("trytrytrytrytrytrytrytrytrytrytrytrytrytry", "SIGNIN--On Pause"+_SPokemonIDChosen+_SPokemonUserEmail);
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL
            HttpPut httpPUT = new
                    HttpPut("http://poke-battle.herokuapp.com/api/users/3");
            String json = "";
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pokemon",_SPokemonIDChosen); //numero del pokemon
            jsonObject.put("user_id", "3");
            jsonObject.put("email", _SPokemonUserEmail);




            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
            // 6. set httpPost Entity
            httpPUT.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            //httpPUT.setHeader("Accept", "application/json");
            httpPUT.setHeader("Content-type", "application/json");
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPUT);
            // 9. receive response as inputStream
            //                  inputStream = httpResponse.getEntity().getContent();
            //                  // 10. convert inputstream to string
            //                  if(inputStream != null)
            //                      result = convertInputStreamToString(inputStream);
            //                  else
            //                      result = "Did not work!";
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
            Log.d("catchcatchcatchcatchcatchcatchcatchcatchcatch", "SIGNIN--On Pause");
        }
        Log.d("afterafterafterafterafterafterafterafterafterafterafter", "SIGNIN--On Pause");
        return "EXITO!";
    }
        protected void onPostExecute(String result) {

        }


}
