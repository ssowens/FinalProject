package com.udacity.gradle.builditbigger.AndroidTest.java;

/**
 * Created by Sheila Owens on 6/3/18.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v4.util.Pair;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import com.ssowens.android.javajokeslib.JavaJokes;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.util.concurrent.CountDownLatch;

import timber.log.Timber;

public class AsyncTaskBasicTest extends ApplicationTestCase {

    private String jokeString = null;
    private CountDownLatch signal = null;
    private Exception exceptionError = null;
    private Activity activity;
    Context context = getContext();
    private static MyApi myApiService = null;

    public AsyncTaskBasicTest(Class applicationClass) {
        super(applicationClass);
    }

    public AsyncTaskBasicTest() {
        super(AsyncTaskBasicTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        signal.countDown();
    }

    public void testEndpointsAsycTask() throws InterruptedException {
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        JavaJokes javaJokes = new JavaJokes();
        String myJoke = javaJokes.getJoke();
        Timber.i("Sheila myjoke %s", myJoke);
        task.setListener(new EndpointsAsyncTask.GetJokeListener() {
            @Override
            public void onComplete(String jokeText, Exception e) {
                jokeString = jokeText;
                exceptionError = e;
                signal.countDown();
            }
        }).execute(new Pair<Context, String>(activity, myJoke));

        signal.await();

        assertNull(exceptionError);
        assertFalse(TextUtils.isEmpty(jokeString));
        assertTrue(jokeString.startsWith("Hi"));
    }

}
