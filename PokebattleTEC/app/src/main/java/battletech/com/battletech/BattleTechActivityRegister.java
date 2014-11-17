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

import battletech.com.battletech.activity.BattleTechActivityLogin;

//******************************************************************
//****CLASS*********************************************************
//******************************************************************
public class BattleTechActivityRegister extends Activity
    {
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreate
//++++DESCRIPCION....................................................
//..........METODO Inicial del Register Activity
//++++PARAMETROS.....................................................
//..........Bundle(pSavedInstanceState)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected void onCreate(Bundle pSavedInstanceState)
            {
                super.onCreate(pSavedInstanceState);
                setContentView(R.layout.activity_register);
                Log.d("BattleTech", "REGISTER--On Create");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........NewUserCreate
//++++DESCRIPCION....................................................
//..........METODO Es la respuesta OnClick para crear un usuario
//++++PARAMETROS.....................................................
//..........View (pView)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public void NewUserCreate(View pView)
            {
                EditText _ETEmail = (EditText) findViewById(R.id.xETEmail);
                EditText _ETPasswordOne = (EditText) findViewById(R.id.xETPasswordOne);
                EditText _ETPasswordTwo = (EditText) findViewById(R.id.xETPasswordTwo);
                String _SEmail = _ETEmail.getText().toString();
                String _SPasswordOne = _ETPasswordOne.getText().toString();
                String _SPasswordTwo = _ETPasswordTwo.getText().toString();
                if(_SEmail != null && _SEmail.equals("") != true && _SPasswordOne != null && _SPasswordOne.equals("") != true && _SPasswordTwo != null && _SPasswordTwo.equals("") != true )
                    {
                        if(_SPasswordOne.equals(_SPasswordTwo) == true)
                            {
                                Intent _ILoginActivity = new Intent(this, BattleTechActivityLogin.class);
                                startActivity(_ILoginActivity);
                            }
                        else
                            {
                                Toast _TPasswordError = Toast.makeText(getApplicationContext(), "Passwords are different, please re-type them", Toast.LENGTH_SHORT);
                                _TPasswordError.show();
                            }
                    }
                else
                    {
                        Toast _TEmptyBoxError = Toast.makeText(getApplicationContext(),"Box field is empty", Toast.LENGTH_SHORT);
                        _TEmptyBoxError.show();
                    }
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onPause
//++++DESCRIPCION....................................................
//..........METODO Para manejar Pausas del Register Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected void onPause()
            {
                super.onPause();
                Log.d("BattleTech", "REGISTER--On Pause");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onResume
//++++DESCRIPCION....................................................
//..........METODO Para manejar las Reanudaciones del Register Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onResume()
            {
                super.onResume();
                Log.d("BattleTech", "REGISTER--On Resume");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStop
//++++DESCRIPCION....................................................
//..........METODO Para manejar deteciones del Register Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStop()
            {
                super.onStop();
                Log.d("BattleTech", "REGISTER--On Stop");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onStart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el inicio del Register Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onStart()
            {
                super.onStart();
                Log.d("BattleTech", "REGISTER--On Start");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onRestart
//++++DESCRIPCION....................................................
//..........METODO Para manejar el reinicio del Register Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onRestart()
            {
                super.onRestart();
                Log.d("BattleTech", "REGISTER--On Restart");
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onDestroy
//++++DESCRIPCION....................................................
//..........METODO Para manejar la destrucción del Register Activity
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        protected  void  onDestroy()
            {
                super.onDestroy();
                Log.d("BattleTech", "REGISTER--On Destroy");
            }
    }
