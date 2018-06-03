package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        return root;
    }

    @OnClick(R.id.jokebutton)
    public void displayJoke() {
        Timber.d("displayJoke");

        JavaJokes javaJokes = new JavaJokes();
        String myJoke = javaJokes.getJoke();
        new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), myJoke));

    }
}
