package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.ssowens.android.androidjokelibrary.AndroidJokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import static com.udacity.gradle.builditbigger.MainActivityFragment.EXTRA_JOKE;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    protected Context context;
    private GetJokeListener listener = null;
    private Exception exceptionError = null;


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public EndpointsAsyncTask setListener(GetJokeListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    protected void onPostExecute(String result) {

        if (this.listener != null) {
            this.listener.onComplete(result, exceptionError);
        }
        if (context != null) {
            // Launch the Android Library Activity
            Intent androidLibIntent = new Intent(context, AndroidJokeActivity.class);
            androidLibIntent.putExtra(EXTRA_JOKE, result);
            context.startActivity(androidLibIntent);
        }
    }

    @Override
    protected void onCancelled() {
        if (this.listener != null) {
            exceptionError = new InterruptedException("EndpointsAsyncTask cancelled");
            this.listener.onComplete(null, exceptionError);
        }
    }

    public interface GetJokeListener {
        void onComplete(String joke, Exception e);
    }
}
