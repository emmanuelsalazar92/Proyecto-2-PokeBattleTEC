
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
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import battletech.com.battletech.R;
//******************************************************************
//****CLASS*********************************************************
//******************************************************************
public class BattleTechActivitySplash extends Activity
    {
        public long _LDuracion = 3000;
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
        protected void onCreate(Bundle pSavedInstanceState)
            {
                super.onCreate(pSavedInstanceState);
                setContentView(R.layout.battletech_activity_splash);
                methodStartAnimation();
                Log.d("BattleTech", "SPLASH--On Create");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onPause
//++++DESCRIPCION....................................................
//..........METODO Para manejar Pausas del Splash Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected void onPause()
            {
                super.onPause();
                Log.d("BattleTech", "SPLASH--On Pause");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onResume
//++++DESCRIPCION....................................................
//..........METODO Para manejar las Reanudaciones del Splash Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onResume()
            {
                super.onResume();
                Log.d("BattleTech", "SPLASH--On Resume");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStop
//++++DESCRIPCION....................................................
//..........METODO Para manejar deteciones del Splash Activity
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
//..........METODO Para manejar el inicio del Splash Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStart()
            {
                super.onStart();
                Log.d("BattleTech", "SPLASH--On Start");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onRestart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el reinicio del Splash Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onRestart()
            {
                super.onRestart();
                Log.d("BattleTech", "SPLASH--On Restart");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onDestroy
//++++DESCRIPCION....................................................
//..........METODO Para manejar la destrucción del Splash Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onDestroy()
            {
                super.onDestroy();
                Log.d("BattleTech", "SPLASH--On Destroy");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........methodStartAnimation
//++++DESCRIPCION....................................................
//..........Hace una cuenta regresiva y modifica la barra de progreso
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public void methodStartAnimation()
            {
                new CountDownTimer(_LDuracion,500)
                    {
                        @Override
                        public void onTick(long millisUntilFinished)
                            {
                            }
                        @Override
                        public void onFinish()
                            {
                                Intent _ILoginActivity = new Intent(BattleTechActivitySplash.this,BattleTechActivityLogin.class);
                                startActivity(_ILoginActivity);
                                finish();
                            }
                    }.start();
            }
    }
