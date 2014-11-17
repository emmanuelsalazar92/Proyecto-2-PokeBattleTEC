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
package battletech.com.battletech.activity;
//******************************************************************
//****IMPORTS*******************************************************
//******************************************************************
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.util.Log;
import com.facebook.AppEventsLogger;

import battletech.com.battletech.BattleTechActivityRegister;
import battletech.com.battletech.BattleTechActivitySignIn;
import battletech.com.battletech.fragment.BattleTechFragmentFacebook;

//******************************************************************
//****CLASS*********************************************************
//******************************************************************
public class BattleTechActivityLogin extends FragmentActivity
    {
        private BattleTechFragmentFacebook _MFFacebook;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreate
//++++DESCRIPCION....................................................
//..........METODO Inicial del Splash Activity
//++++PARAMETROS.....................................................
//..........Bundle(pSavedInstanceState)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        public void onCreate(Bundle pSavedInstanceState)
            {
                super.onCreate(pSavedInstanceState);
                if (pSavedInstanceState == null)
                    {
                        _MFFacebook = new BattleTechFragmentFacebook();
                        getSupportFragmentManager()
                        .beginTransaction()
                        .add(android.R.id.content, _MFFacebook)
                        .commit();
                    }
                else
                    {
                        _MFFacebook = (BattleTechFragmentFacebook) getSupportFragmentManager()
                        .findFragmentById(android.R.id.content);
                        Log.d("BattleTech", "LOGIN--On Create");
                    }
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........OpenRegister
//++++DESCRIPCION....................................................
//..........METODO Es la respuesta OnClick de Crear Cuenta
//++++PARAMETROS.....................................................
//..........View (pView)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public void OpenRegister(View pView)
            {
                Intent _IRegisterActivity = new Intent(this, BattleTechActivityRegister.class);
                startActivity(_IRegisterActivity);
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........SingInBattleTech
//++++DESCRIPCION....................................................
//..........METODO Es la respuesta OnClick para "logear" al Sistema
//++++PARAMETROS.....................................................
//..........View (pView)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public  void SingInBattleTech(View pView)
            {
                Intent _ISignInActivity = new Intent(this, BattleTechActivitySignIn.class);
                startActivity(_ISignInActivity);
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........OpenTwitter
//++++DESCRIPCION....................................................
//..........METODO Es la respuesta OnClick para registro por Twitter
//++++PARAMETROS.....................................................
//..........View (pView)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public  void OpenTwitter(View pView)
            {
                Intent _ITwitterActivity = new Intent(this, BattleTechActivityTwitter.class);
                startActivity(_ITwitterActivity);
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onResume
//++++DESCRIPCION....................................................
//..........METODO Para manejar las Reanudaciones del Login Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected void onResume()
            {
                super.onResume();
                AppEventsLogger.activateApp(this);
                Log.d("BattleTech", "LOGIN--On Resume");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onPause
//++++DESCRIPCION....................................................
//..........METODO Para manejar Pausas del Login Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected void onPause()
            {
                super.onPause();
                AppEventsLogger.deactivateApp(this);
                Log.d("BattleTech", "LOGIN --On Pause");
            }
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStop
//++++DESCRIPCION....................................................
//..........METODO Para manejar deteciones del Login Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStop()
            {
                super.onStop();
                Log.d("BattleTech", "LOGIN--On Stop");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el inicio del Login Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStart()
            {
                super.onStart();
                Log.d("BattleTech", "LOGIN--On Start");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onRestart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el reinicio del Login Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onRestart()
            {
                super.onRestart();
                Log.d("BattleTech", "LOGIN--On Restart");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onDestroy
//++++DESCRIPCION....................................................
//..........METODO Para manejar la destrucción del Login Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onDestroy()
            {
                super.onDestroy();
                Log.d("BattleTech", "LOGIN--On Destroy");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onBackPressed
//++++DESCRIPCION....................................................
//..........METODO Para manejar el boton de regreso en Login Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        public void onBackPressed()
            {
                //super.onBackPressed();
                Log.d("BattleTech", "LOGIN--On Back Pressed");
                new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        BattleTechActivityLogin.super.onBackPressed();
                    }
                }).create().show();
            }
    }
