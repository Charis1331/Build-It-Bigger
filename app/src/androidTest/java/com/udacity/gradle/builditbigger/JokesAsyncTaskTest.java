package com.udacity.gradle.builditbigger;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class JokesAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

//    @UiThreadTest
    @Test
    public void verifyJoke() throws InterruptedException {
        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        JokesAsyncTask jokesTask = new JokesAsyncTask(mActivityTestRule.getActivity()) {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                if (!TextUtils.isEmpty(result)) {
                    assertTrue(result.length() > 0);
                    latch.countDown();
                }
            }
        };
        jokesTask.execute();
        latch.await();
    }
}