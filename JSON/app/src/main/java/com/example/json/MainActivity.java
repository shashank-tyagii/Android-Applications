package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class Downloader extends AsyncTask<String,Void,String> {
        // This class will only fetch the data, it will not process it into  meaningful text.
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
               // connection.connect();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data!=-1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
       @Override
        protected void onPostExecute (String s) {  // To get meaningful data from Json
           super.onPostExecute(s);
            try {
                JSONObject JSONobject = new
                        JSONObject(s);
                String weatherInfo = JSONobject.getString("weather");      // Data referring weather key in JSON file
                JSONArray arr = new JSONArray(weatherInfo);                     // Very precise data, to extract data from weather
                Log.i("Weather Information", weatherInfo);
               for (int i = 0; i < arr.length() ; i++){
                    JSONObject jsonPart = arr.getJSONObject(i);
                    String output = jsonPart.getString("id");           // Data referring to "main" in "weather"
                   Log.i("Specific Data", output);
                }
            } catch (JSONException e) { e.printStackTrace(); } }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Downloader downloader = new Downloader(); // This will only fetch the data in text format
        try {
            String result = downloader.execute("https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=5efc61026b9ddd72a4547cd979981fd1").get();
            Log.i("JSON File Result" , result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}