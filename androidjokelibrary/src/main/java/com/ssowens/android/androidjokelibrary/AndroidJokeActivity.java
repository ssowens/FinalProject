package com.ssowens.android.androidjokelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import timber.log.Timber;

public class AndroidJokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lib);

        // Set up Timber
        Timber.plant(new Timber.DebugTree());
        Timber.d("AndroidJokeActivity.onCreate");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String getJoke = (String) bundle.get(EXTRA_JOKE);
            TextView jokeTextView = findViewById(R.id.joke_text_view);
            jokeTextView.setText(getJoke);
        }

    }
}
