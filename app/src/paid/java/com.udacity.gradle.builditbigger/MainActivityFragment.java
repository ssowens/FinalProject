package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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

        new EndpointsAsyncTask().execute(getContext());

    }
}
