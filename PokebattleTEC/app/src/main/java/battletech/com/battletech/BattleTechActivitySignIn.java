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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//******************************************************************
//****CLASS*********************************************************
//******************************************************************
public class BattleTechActivitySignIn extends Activity
    {
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreate
//++++DESCRIPCION....................................................
//..........METODO Inicial del SignIn Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected void onCreate(Bundle pSavedInstanceState)
            {
                super.onCreate(pSavedInstanceState);
                setContentView(R.layout.activity_sign_in);
                Log.d("BattleTech", "SIGNIN--On Create");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........SingInBattle
//++++DESCRIPCION....................................................
//..........METODO Es la respuesta OnClick para loggear
//++++PARAMETROS.....................................................
//..........View (pView)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public  void SingInBattle(View pView)
            {
                EditText _ETEmailSign = (EditText) findViewById(R.id.xETEmailSign);
                EditText _ETPasswordSign = (EditText) findViewById(R.id.xETPasswordSign);
                String _SEmailSign = _ETEmailSign.getText().toString();
                String _SPasswordSign = _ETPasswordSign.getText().toString();
                if(_SEmailSign.equals("")!=true && _SEmailSign != null && _SPasswordSign.equals("")!=true && _SPasswordSign != null)
                    {
                        if(_SEmailSign.equals(getString(R.string.ssap))==true && _SPasswordSign.equals(getString(R.string.liame))==true)
                            {
                                Intent _IMainActivity = new Intent(this, BattleTechActivityMain.class);
                                _IMainActivity.putExtra("usuario", "3");
                                startActivity(_IMainActivity);
                            }
                        else
                            {
                                Toast _TWrongData = Toast.makeText(getApplicationContext(), "The password or the email is wrong. Please check it", Toast.LENGTH_SHORT);
                                _TWrongData.show();
                            }
                    }
                else
                    {
                        Toast _TEmptyBox = Toast.makeText(getApplicationContext(), "Box field is empty", Toast.LENGTH_SHORT);
                        _TEmptyBox.show();
                    }
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
                Log.d("BattleTech", "SIGNIN--On Pause");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onResume
//++++DESCRIPCION....................................................
//..........METODO Para manejar las Reanudaciones del SignIn Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onResume()
            {
                super.onResume();
                Log.d("BattleTech", "SIGNIN--On Resume");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStop
//++++DESCRIPCION....................................................
//..........METODO Para manejar deteciones del SignIn Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStop()
            {
                super.onStop();
                Log.d("BattleTech", "SIGNIN--On Stop");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el inicio del SignIn Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStart()
            {
                super.onStart();
                Log.d("BattleTech", "SIGNIN--On Start");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onRestart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el reinicio del SignIn Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onRestart()
            {
                super.onRestart();
                Log.d("BattleTech", "SIGNIN--On Restart");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onDestroy
//++++DESCRIPCION....................................................
//..........METODO Para manejar la destrucción del SignIn Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onDestroy()
            {
                super.onDestroy();
                Log.d("BattleTech", "SIGNIN--On Destroy");
            }
    }
