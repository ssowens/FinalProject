package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ssowens.android.androidjokelibrary.AndroidJokeActivity;
import com.ssowens.android.javajokeslib.JavaJokes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String EXTRA_JOKE = "joke";
    @BindView(R.id.jokebutton) Button jokeButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Bind the views
        ButterKnife.bind(this, root);

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @OnClick(R.id.jokebutton)
    public void displayJoke() {
        Timber.d("displayJoke");
        JavaJokes javaJokes = new JavaJokes();
        String myJoke = javaJokes.getJoke();
        Toast.makeText(getContext(), myJoke, Toast.LENGTH_SHORT).show();

        // Launch the Android Library Activity
        Intent androidLibIntent = new Intent(getActivity(), AndroidJokeActivity.class);
        androidLibIntent.putExtra(EXTRA_JOKE, myJoke);
        startActivity(androidLibIntent);
    }
}
