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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
//******************************************************************
//****CLASS*********************************************************
//******************************************************************
public class SplashActivity extends Activity
{
    public static final int _Seconds=3;
    public static final int _MiliSeconds =_Seconds*1000;
    public static final int _Delay =1;
    public ProgressBar _Progress;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        _Progress = (ProgressBar) findViewById(R.id.pbprogreso);
        _Progress.setMax(MaximProgress());
        StartAnimation();
        Log.d("BattleTech", "SPLASH--On Create");
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("BattleTech", "SPLASH--On Pause");
    }
    @Override
    protected  void  onResume()
    {
        super.onResume();
        Log.d("BattleTech", "SPLASH--On Resume");
    }
    @Override
    protected  void  onStop()
    {
        super.onStop();
        Log.d("BattleTech", "SPLASH--On Stop");
    }
    @Override
    protected  void  onStart()
    {
        super.onStart();
        Log.d("BattleTech", "SPLASH--On Start");
    }
    @Override
    protected  void  onRestart()
    {
        super.onRestart();
        Log.d("BattleTech", "SPLASH--On Restart");
    }
    @Override
    protected  void  onDestroy()
    {
        super.onDestroy();
        Log.d("BattleTech", "SPLASH--On Destroy");
    }

    public void StartAnimation()
    {
        new CountDownTimer(_MiliSeconds,500)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                _Progress.setProgress(SetProgress(millisUntilFinished));
            }

            @Override
            public void onFinish()
            {
                Intent _New = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(_New);
                finish();
            }
        }.start();
    }
    public int SetProgress(long pMiliSecond)
    {
        return (int)((_MiliSeconds-pMiliSecond)/1000);
    }

    public int MaximProgress()
    {
        return _Seconds-_Delay;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
}
