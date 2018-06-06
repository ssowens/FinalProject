package com.udacity.gradle.builditbigger.AndroidTest.java;

/**
 * Created by Sheila Owens on 6/3/18.
 */

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskBasicTest {

    private Activity activity;

    @Test
    public void testEndpointsAsyncTask() throws Exception {
        new EndpointsAsyncTask().execute(InstrumentationRegistry.getTargetContext()).get();
        assertTrue("Error: Fetched joke: " + EndpointsAsyncTask.returnedJoke,
                EndpointsAsyncTask.returnedJoke != null);
    }
}
