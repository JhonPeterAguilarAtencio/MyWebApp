package com.example.mywebapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static String DIRECCION = "https://github.com/JhonPeterAguilarAtencio?tab=repositories";
    ConstraintLayout constraintLayout;
    //private static String DIRECCION = "https://localhost:8080/Android/index.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = (ConstraintLayout)findViewById(R.id.idConstraintL);

        createSnackbar();

        WebView webView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new WebAppInterface(this),"Android");
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(DIRECCION);


    }

    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event){
        WebView webView = (WebView)findViewById(R.id.webView);
        if ((KeyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(KeyCode, event);
    }

    private class MyWebViewClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    private void createSnackbar(){
        Snackbar snackbar = Snackbar.make(constraintLayout, "Bienvenido a la mi pagina GITHUB", Snackbar.LENGTH_LONG)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setBackgroundTint(Color.parseColor("#006400"));
        snackbar.show();

    }
}