package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class TransactionsView extends AppCompatActivity {
    private Context mContext;

    private final String chasePathVirtual = "C:\\Users\\Nick's Big PC\\OneDrive\\budgetData\\";

    private final String chasePathLaptop = "C:\\Users\\Darth Bane\\OneDrive\\budgetData\\";

    private final String phonePath = "/storage/emulated/0/Download/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("NICK", "made it here1");
        setContentView(R.layout.activity_transactions_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transactions_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean isWebView = false;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.i("NICKWEB", "menuSelect");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.i("NICKWEB", "menuSelect1");
            Log.i("NICKWEB", "menuSelect2");
            Intent webIntent = new Intent(TransactionsView.this, BankWebView.class);
            startActivity(webIntent);
            Log.i("NICKWEB", "menuSelect3");
            return true;
        }

        Log.i("NICKWEB", "menuSelect4");
        return super.onOptionsItemSelected(item);
    }

    public void populateScreen(String html){

    }
}
