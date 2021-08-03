package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.os.PersistableBundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidnetworkinglab.Todo.HomeFragment;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.analytics.FirebaseAnalytics;

public class TodoActivity extends AppCompatActivity {
    private FirebaseAuth fAuth;
    private Fragment mContent;
    public final static String FRAGMENT_TAG = "MyFragment";
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //get firebase auth instance
        fAuth = FirebaseAuth.getInstance();
        //firebase messaging instance
        FirebaseMessaging fMsg = FirebaseMessaging.getInstance();
        //firebase instance to subscribe topics
        fMsg.subscribeToTopic("todos");
        //firebase analytics instance
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

                mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);

        mFirebaseAnalytics.setSessionTimeoutDuration(600000);
        if (savedInstanceState == null) {

            Bundle bundle = new Bundle();

            if (fAuth.getCurrentUser() != null) {
                mContent = HomeFragment.newInstance();
                bundle.putString(FirebaseAnalytics.Param.DESTINATION, "Home Fragment");
            } else {
//                mContent = LoginSignUpFragment.newInstance();
//                bundle.putString(FirebaseAnalytics.Param.DESTINATION, "Login
//                        Fragment");
            }
            FragmentManager fm = getSupportFragmentManager();

            bundle.putString(FirebaseAnalytics.Param.ORIGIN, "Main Activity");
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,
                            bundle);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.content, mContent, FRAGMENT_TAG);
            ft.commit();
        } else {
            mContent = getSupportFragmentManager().getFragment(
                    savedInstanceState, FRAGMENT_TAG);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle
            outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mContent =
                getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (mContent != null)
            getSupportFragmentManager().putFragment(outState, FRAGMENT_TAG,
                    mContent);
    }
}