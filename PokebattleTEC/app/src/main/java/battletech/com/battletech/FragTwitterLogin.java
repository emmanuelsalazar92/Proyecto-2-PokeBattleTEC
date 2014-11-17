
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

//******************************************************************
//****PACKAGE*******************************************************
//******************************************************************
package battletech.com.battletech;
//******************************************************************
//****IMPORTS*******************************************************
//******************************************************************
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
//******************************************************************
//****CLASS*********************************************************
//******************************************************************
public class FragTwitterLogin extends Fragment
    {
        Button _BLogin;
        Twitter _TTwitter;
        RequestToken _RTRequestToken = null;
        AccessToken accessToken;
        String oauth_url,oauth_verifier,profile_url;
        Dialog auth_dialog;
        WebView web;
        SharedPreferences pref;
        ProgressDialog progress;
        Bitmap bitmap;
        String _Username;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreate
//++++DESCRIPCION....................................................
//..........METODO Inicial del Twitter Activity
//++++PARAMETROS.....................................................
//..........Bundle(pSavedInstanceState)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
            {
                View view = inflater.inflate(R.layout.battletech_fragment_twitter, container, false);
                _BLogin = (Button)view.findViewById(R.id.login);
                pref = getActivity().getPreferences(0);
                _TTwitter = new TwitterFactory().getInstance();
                _TTwitter.setOAuthConsumer(pref.getString("CONSUMER_KEY", ""), pref.getString("CONSUMER_SECRET", ""));
                _BLogin.setOnClickListener(new LoginProcess());
                return view;
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreate
//++++DESCRIPCION....................................................
//..........METODO Inicial del Twitter Activity
//++++PARAMETROS.....................................................
//..........Bundle(pSavedInstanceState)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        private class LoginProcess implements View.OnClickListener
            {
                @Override
                public void onClick(View v)
                    {
                        // TODO Auto-generated method stub
                        new TokenGet().execute();
                    }
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreate
//++++DESCRIPCION....................................................
//..........METODO Inicial del Twitter Activity
//++++PARAMETROS.....................................................
//..........Bundle(pSavedInstanceState)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        private class TokenGet extends AsyncTask<String, String, String>
            {
                @Override
                protected String doInBackground(String... args)
                    {
                        try
                            {
                                _RTRequestToken = _TTwitter.getOAuthRequestToken();
                                oauth_url = _RTRequestToken.getAuthorizationURL();
                            }
                        catch (TwitterException e)
                            {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        return oauth_url;
                    }
                @Override
                protected void onPostExecute(String oauth_url)
                    {
                        if(oauth_url != null)
                            {
                                Log.e("URL", oauth_url);
                                auth_dialog = new Dialog(getActivity());
                                auth_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                auth_dialog.setContentView(R.layout.fragment_auth);
                                web = (WebView)auth_dialog.findViewById(R.id.webv);
                                web.getSettings().setJavaScriptEnabled(true);
                                web.loadUrl(oauth_url);
                                web.setWebViewClient(new WebViewClient()
                                    {
                                        boolean authComplete = false;
                                        @Override
                                        public void onPageStarted(WebView view, String url, Bitmap favicon)
                                            {
                                                super.onPageStarted(view, url, favicon);
                                            }
                                        @Override
                                        public void onPageFinished(WebView view, String url)
                                            {
                                                super.onPageFinished(view, url);
                                                if (url.contains("oauth_verifier") && authComplete == false)
                                                    {
                                                        authComplete = true;
                                                        Log.e("Url",url);
                                                        Uri uri = Uri.parse(url);
                                                        oauth_verifier = uri.getQueryParameter("oauth_verifier");
                                                        auth_dialog.dismiss();
                                                        new AccessTokenGet().execute();
                                                    }
                                                else if(url.contains("denied"))
                                                    {
                                                        auth_dialog.dismiss();
                                                        Toast.makeText(getActivity(), "Sorry !, Permission Denied", Toast.LENGTH_SHORT).show();
                                                    }
                                            }
                                    });
                                auth_dialog.show();
                                auth_dialog.setCancelable(true);
                            }
                        else
                            {
                                Toast.makeText(getActivity(), "Sorry !, Network Error or Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                    }
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreate
//++++DESCRIPCION....................................................
//..........METODO Inicial del Twitter Activity
//++++PARAMETROS.....................................................
//..........Bundle(pSavedInstanceState)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
private class AccessTokenGet extends AsyncTask<String, String, Boolean>
{
@Override
protected void onPreExecute()
{
super.onPreExecute();
progress = new ProgressDialog(getActivity());
progress.setMessage("Fetching Data ...");
progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
progress.setIndeterminate(true);
progress.show();
}
@Override
protected Boolean doInBackground(String... args)
{
try
{
accessToken = _TTwitter.getOAuthAccessToken(_RTRequestToken, oauth_verifier);
SharedPreferences.Editor edit = pref.edit();
edit.putString("ACCESS_TOKEN", accessToken.getToken());
edit.putString("ACCESS_TOKEN_SECRET", accessToken.getTokenSecret());
User user = _TTwitter.showUser(accessToken.getUserId());
profile_url = user.getOriginalProfileImageURL();
edit.putString("NAME", user.getName());
_Username = user.getName();
edit.putString("IMAGE_URL", user.getOriginalProfileImageURL());
edit.commit();
} catch (TwitterException e)
{
// TODO Auto-generated catch block
e.printStackTrace();
}
return true;
}
@Override
protected void onPostExecute(Boolean response)
{
if(response)
{
//   try{
progress.hide();
//     accessToken = _TTwitter.getOAuthAccessToken(_RTRequestToken, oauth_verifier);
Intent act = new Intent(getActivity(), BattleTechActivityMain.class);
//   User user = _TTwitter.showUser(accessToken.getUserId());
act.putExtra("NAME", _Username);
act.putExtra("usuario","2");
Log.d("Name",_Username);
getActivity().startActivity(act);
// }catch (TwitterException e) {
// // TODO Auto-generated catch block
//   e.printStackTrace();
// }

/*                Fragment profile = new ProfileFragment();
FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
ft.replace(R.id.content_frame, profile);
ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
ft.addToBackStack(null);
ft.commit();*/
}
}
}
}