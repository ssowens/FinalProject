package com.udacity.gradle.builditbigger.AndroidTest.java;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.backend.myApi.model.MyBean;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Sheila Owens on 5/31/18.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Rule


    @Test
    public void testAsyncTask() throws Throwable {
        MyBean myBean = new MyBean();
        myBean.setData("ThisIsMyTestJoke");
       // onData(getData.execute()).thenReturn(bean);
    }

}
