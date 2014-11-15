package battletech.com.battletech;
import android.content.Intent;
import android.support.v4.app.*;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import android.view.View;
import android.content.Intent;
import java.util.Arrays;

/**
 * Created by Emmanuel on 24/10/2014.
 */
public class MainFragment extends Fragment{


    private static final String TAG = "MainFragment";
    private UiLifecycleHelper uiHelper;
    private String casa;
    private Session.StatusCallback statusCallback = new SessionStatusCallback();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_login_activity, container, false);
        LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
        authButton.setFragment(this);
        authButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        return view;
    }
    private void onClickLogin() {
        Session session = Session.getActiveSession();
        if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this)
                    .setPermissions(Arrays.asList("public_profile"))
                    .setCallback(statusCallback));

            Session.NewPermissionsRequest newPermissionsRequest = new Session
                    .NewPermissionsRequest(this, Arrays.asList("user_checkins"));
            session.requestNewReadPermissions(newPermissionsRequest);

        } else {
            Session.openActiveSession(getActivity(), this, true, statusCallback);
        }
    }
    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            // Respond to session state changes, ex: updating the view

        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }




    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (session != null && session.isOpened()) {
            if (state.equals(SessionState.OPENED_TOKEN_UPDATED)) {
                //tokenUpdated();
            } else {
                makeMeRequest(session);
            }
        } else {
            //profilePictureView.setProfileId(null);
            Log.i(TAG, "Logged out...");
        }


    }
    private void makeMeRequest(final Session session) {
        Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser user, Response response) {
                if (session == Session.getActiveSession()) {
                    if (user != null) {
                        casa = user.getProperty("email").toString();
                        Log.d("Food Style", casa);
                        casa = user.getId();
                        Log.d("Food Style", casa);
                        Intent act = new Intent(getActivity(), MainActivity.class);
                        act.putExtra("usuario","2");
                        startActivity(act);
                    }
                }
                if (response.getError() != null) {
                    casa = "TRISTE";
                    Log.d("Food Style", casa);
                    // handleError(response.getError());
                }
            }
        });
        request.executeAsync();

    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }
}
