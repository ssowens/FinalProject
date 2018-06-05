package com.udacity.gradle.builditbigger.AndroidTest.java;

/**
 * Created by Sheila Owens on 6/3/18.
 */

import android.app.Activity;
import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;

import com.ssowens.android.javajokeslib.JavaJokes;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskBasicTest {

    private Activity activity;

    @Test
    public void testEndpointsAsyncTask() throws Exception {
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        JavaJokes javaJokes = new JavaJokes();
        String myJoke = javaJokes.getJoke();
        task.execute(new Pair<Context, String>(activity, myJoke)).get();
        assertTrue("Error: Fetched joke: " + EndpointsAsyncTask.returnedJoke,
                EndpointsAsyncTask.returnedJoke != null);
    }
}
