package com.example.sourceview.utilities;

import android.os.AsyncTask;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import com.example.sourceview.MainActivity;
import com.example.sourceview.interfaces.OnTaskFinished;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RetrieveSiteData extends AsyncTask<String, Void, String> {
    String HTML_response = "";
    OnTaskFinished onOurTaskFinished;


    public RetrieveSiteData(OnTaskFinished onTaskFinished)
    {
        onOurTaskFinished = onTaskFinished;
    }

    @Override
    protected String doInBackground(String... urls) {

        try {
            URL url = new URL(urls[0]); // enter your url here which to download

            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                // System.out.println(inputLine);
                HTML_response += inputLine;
            }
            br.close();

            System.out.println("Done");

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return HTML_response;
    }

    @Override
    protected void onPostExecute(String feed)
    {
        onOurTaskFinished.onFeedRetrieved(feed);
    }
}
