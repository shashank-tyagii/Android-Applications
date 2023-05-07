package com.example.webviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());           // This make sure it doesn't open Chrome, directly open the website

       // webView.loadUrl("https://www.google.com/");            // Website link
        webView.loadData("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Shashank Tyagi</h1>\n" +
                "<p>Associate Project Manager</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>" , "text/html" , "UTF-8");
    }
}