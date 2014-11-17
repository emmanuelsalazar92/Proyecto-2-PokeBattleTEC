
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
package battletech.com.battletech.fragment;
//******************************************************************
//****IMPORTS*******************************************************
//******************************************************************
import android.content.Intent;
import android.support.v4.app.*;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import java.util.Arrays;

import battletech.com.battletech.BattleTechActivityMain;
import battletech.com.battletech.R;

//******************************************************************
//****CLASS*********************************************************
//******************************************************************
public class BattleTechFragmentFacebook extends Fragment
    {
        private static final String _STag = "BattleTechFragmentFacebook";
        private UiLifecycleHelper _ULHCycleLife;
        private String _SUserData;
        private Session.StatusCallback _SSCStatusCallback = new SessionStatusCallback();
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreateView
//++++DESCRIPCION....................................................
//..........METODO Inicial del Facebook Fragment
//++++PARAMETROS.....................................................
//..........LayoutInflater pInflater, ViewGroup pContainer, Bundle pSavedInstanceState
//++++RETORNO........................................................
//.........._VFacebookView
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer, Bundle pSavedInstanceState)
            {
                View _VFacebookView = pInflater.inflate(R.layout.battletech_activity_login, pContainer, false);
                LoginButton _LBFacebook = (LoginButton) _VFacebookView.findViewById(R.id.authButton);
                _LBFacebook.setFragment(this);
                _LBFacebook.setReadPermissions(Arrays.asList("email", "public_profile"));
                return _VFacebookView;
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onClickLogin
//++++DESCRIPCION....................................................
//..........METODO Login Facebook
//++++PARAMETROS.....................................................
//..........Sin parametros
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        private void onClickLogin()
            {
                Session session = Session.getActiveSession();
                if (!session.isOpened() && !session.isClosed())
                    {
                        session.openForRead(new Session.OpenRequest(this)
                        .setPermissions(Arrays.asList("public_profile"))
                        .setCallback(_SSCStatusCallback));
                        Session.NewPermissionsRequest newPermissionsRequest = new Session
                        .NewPermissionsRequest(this, Arrays.asList("user_checkins"));
                        session.requestNewReadPermissions(newPermissionsRequest);
                    }
                else
                    {
                        Session.openActiveSession(getActivity(), this, true, _SSCStatusCallback);
                    }
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........SessionStatusCallback
//++++DESCRIPCION....................................................
//..........CLASE SessionStatusCallback
//++++PARAMETROS.....................................................
//..........NO APLICA
//++++RETORNO........................................................
//..........NO APLICA
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
private class SessionStatusCallback implements Session.StatusCallback
    {
        @Override
        public void call(Session session, SessionState state, Exception exception)
            {
            }
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onCreate
//++++DESCRIPCION....................................................
//..........METODO Inicial del Facebook Fragment
//++++PARAMETROS.....................................................
//..........Bundle(pSavedInstanceState)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        @Override
        public void onCreate(Bundle pSavedInstanceState)
            {
                super.onCreate(pSavedInstanceState);
                _ULHCycleLife = new UiLifecycleHelper(getActivity(), callback);
                _ULHCycleLife.onCreate(pSavedInstanceState);
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++NOMBRE.........................................................
//..........onSessionStateChange
//++++DESCRIPCION....................................................
//..........METODO Inicial del Splash Activity
//++++PARAMETROS.....................................................
//..........Bundle(pSavedInstanceState)
//++++RETORNO........................................................
//..........Sin retorno
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        private void onSessionStateChange(Session session, SessionState state, Exception exception)
            {
                if (session != null && session.isOpened())
                    {
                        if (state.equals(SessionState.OPENED_TOKEN_UPDATED))
                            {
                                //tokenUpdated();
                            }
                        else
                            {
                                makeMeRequest(session);
                            }
                    }
                else
                    {
                        //profilePictureView.setProfileId(null);
                        Log.i(_STag, "Logged out...");
                    }
            }
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
        private void makeMeRequest(final Session session)
            {
                Request request = Request.newMeRequest(session, new Request.GraphUserCallback()
                    {
                        @Override
                        public void onCompleted(GraphUser user, Response response)
                            {
                                if (session == Session.getActiveSession())
                                    {
                                        if (user != null)
                                        {
                                            Intent _IMainActivity = new Intent(getActivity(), BattleTechActivityMain.class);
                                            _SUserData = user.getProperty("email").toString();
                                            Log.d("BattleTech", _SUserData);
                                            _IMainActivity.putExtra("EMAIL", _SUserData);
                                            _SUserData = user.getId();
                                            Log.d("BattleTech", _SUserData);
                                            _IMainActivity.putExtra("ID", _SUserData);
                                            _IMainActivity.putExtra("usuario", "1");
                                            startActivity(_IMainActivity);
                                        }
                                    }
                                if (response.getError() != null)
                                    {
                                    }
                            }
                    });
                request.executeAsync();
            }
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
        private Session.StatusCallback callback = new Session.StatusCallback()
            {
                @Override
                public void call(Session session, SessionState state, Exception exception)
                    {
                        onSessionStateChange(session, state, exception);
                    }
            };
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
        public void onResume()
            {
                super.onResume();
                Session session = Session.getActiveSession();
                if (session != null && (session.isOpened() || session.isClosed()) )
                    {
                        onSessionStateChange(session, session.getState(), null);
                    }
                _ULHCycleLife.onResume();
            }
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
        public void onActivityResult(int requestCode, int resultCode, Intent data)
            {
                super.onActivityResult(requestCode, resultCode, data);
                _ULHCycleLife.onActivityResult(requestCode, resultCode, data);
            }
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
        public void onPause()
            {
                super.onPause();
                _ULHCycleLife.onPause();
            }
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
        public void onDestroy()
            {
                super.onDestroy();
                _ULHCycleLife.onDestroy();
            }
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
        public void onSaveInstanceState(Bundle outState)
            {
                super.onSaveInstanceState(outState);
                _ULHCycleLife.onSaveInstanceState(outState);
            }
    }
