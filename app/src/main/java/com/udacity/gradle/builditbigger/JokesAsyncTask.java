package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.test.UiThreadTest;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jokesandroidlibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokesAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Activity activity;

    private ProgressBar progressBar;

    public JokesAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //progressBar = activity.findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.VISIBLE);
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    @Override
    protected String doInBackground(Void... voids) {
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

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        //progressBar.setVisibility(View.GONE);
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        if (s == null) return;

        Intent intent = new Intent(activity, JokeActivity.class);
        intent.putExtra(JokeActivity.EXTRA_JOKE, s);
        activity.startActivity(intent);
    }
}
