package com.udacity.gradle.builditbigger.AndroidTest.java;

import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.MyBean;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import timber.log.Timber;

/**
 * Created by Sheila Owens on 5/31/18.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest extends InstrumentationTestCase {
    @Mock
    MyApi myApiService;

    @Mock
    MyApi.SayHi getJoke;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAsyncTask() throws Throwable {
        MyBean myBean = new MyBean();
        myBean.setData("ThisIsMyTestJoke");
        Mockito.when(getJoke.execute()).thenReturn(myBean);
        Mockito.when(myApiService.sayHi(myBean.getData())).thenReturn(getJoke);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                EndpointsAsyncTask task = new EndpointsAsyncTask() {
                    @Override
                    protected void onPostExecute(String result) {
                        super.onPostExecute(result);
                        Assert.assertEquals("MyTestJoke", result);
                        Timber.d("Test worked");
                    }
                };
            }
        });

    }
}
