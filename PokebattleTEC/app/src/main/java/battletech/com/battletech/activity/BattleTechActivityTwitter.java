
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
package battletech.com.battletech.activity;
//******************************************************************
//****IMPORTS*******************************************************
//******************************************************************
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import battletech.com.battletech.FragTwitterLogin;
import battletech.com.battletech.R;
//******************************************************************
//****CLASS*********************************************************
//******************************************************************
public class BattleTechActivityTwitter extends Activity
    {
        SharedPreferences _SPTwitterPreferences;
        private static String _SConsumerKey = "CZuRhHze4Yi3CVGHz9xLoVGVW";
        private static String _SConsumerSecret = "wrIxJlEEiEnAV9Zh9hQa0XPSQYhj2brLWwrZBXxmZ6MMo0kUFt";
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
        protected void onCreate(Bundle pSavedInstanceState)
            {
                super.onCreate(pSavedInstanceState);
                setContentView(R.layout.battletech_activity_twitter);
                _SPTwitterPreferences = getPreferences(0);
                SharedPreferences.Editor _SPEditor = _SPTwitterPreferences.edit();
                _SPEditor.putString("CONSUMER_KEY", _SConsumerKey);
                _SPEditor.putString("CONSUMER_SECRET", _SConsumerSecret);
                _SPEditor.commit();
                Fragment _FTwitterLogin = new FragTwitterLogin();
                FragmentTransaction _FTTwitterTransaction = getFragmentManager().beginTransaction();
                _FTTwitterTransaction.replace(R.id.content_frame, _FTwitterLogin);
                _FTTwitterTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                _FTTwitterTransaction.addToBackStack(null);
                _FTTwitterTransaction.commit();
                Log.d("BattleTech", "TWITTER--On Create");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onPause
//++++DESCRIPCION....................................................
//..........METODO Para manejar Pausas del Twitter Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected void onPause()
            {
                super.onPause();
                Log.d("BattleTech", "TWITTER--On Pause");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onResume
//++++DESCRIPCION....................................................
//..........METODO Para manejar las Reanudaciones del Twitter Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onResume()
            {
                super.onResume();
                Log.d("BattleTech", "TWITTER--On Resume");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStop
//++++DESCRIPCION....................................................
//..........METODO Para manejar deteciones del Twitter Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStop()
            {
                super.onStop();
                Log.d("BattleTech", "TWITTER--On Stop");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el inicio del Twitter Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStart()
            {
                super.onStart();
                Log.d("BattleTech", "TWITTER--On Start");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onRestart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el reinicio del Twitter Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onRestart()
            {
                super.onRestart();
                Log.d("BattleTech", "TWITTER--On Restart");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onDestroy
//++++DESCRIPCION....................................................
//..........METODO Para manejar la destrucción del Twitter Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onDestroy()
            {
                super.onDestroy();
                Log.d("BattleTech", "TWITTER--On Destroy");
            }
    }