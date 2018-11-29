package com.example.myfirstapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class BankWebView extends AppCompatActivity {

    /* An instance of this class will be registered as a JavaScript interface */
    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html)
        {
            // process the html as needed by the app
            Log.i("NICKWEB", html);
            loadHtmlIntoTransactionViewData(html);

        }
    }

    private WebView mBankWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("NICKWEB", "made it to class 0");
        setContentView(R.layout.web);
        //Log.i("NICKWEB", "made it to class 1");
        mBankWebView = findViewById(R.id.web);
        //Log.i("NICKWEB", "made it to class 2");
        WebSettings webSettings = mBankWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mBankWebView.getSettings().setAppCacheEnabled(false);
        /* JavaScript must be enabled if you want it to work, obviously */
        mBankWebView.getSettings().setJavaScriptEnabled(true);

        /* Register a new JavaScript interface called HTMLOUT */
        //mBankWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        //Log.i("NICKWEB", "made it to class 3");

        mBankWebView.loadUrl("http://chase.com");

        //Log.i("NICKWEB", "made it to class 4");
        mBankWebView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                Log.i("NICKWEB", "made it onPageFinished()");
                //isWebView = true;
                /* This call inject JavaScript into the page which just finished loading. */
                mBankWebView.loadUrl("javascript:window.HTMLOUT.processHTML('<body>'+document.getElementsByTagName('html')[0].innerHTML+'</body>');");
            }
        });

        Log.i("NICKWEB", "made it to class 5");

    }

    private void loadHtmlIntoTransactionViewData(String html){

        Log.i("NICKWEB", "loadHtmlIntoTransactionViewData()");

        Context mContext;
        setContentView(R.layout.activity_transactions_view);
        // Get the application context
        mContext = getApplicationContext();

        Resources resources = mContext.getResources();
        final int resourceId = resources.getIdentifier("rounded", "drawable",
                mContext.getPackageName());
        Drawable rounded = resources.getDrawable(resourceId);

        CardView cardsTable = (CardView) findViewById(R.id.card_view);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setBackground(rounded);

        Log.i("NICK", "made it here2");

        //TODO: figure out how to get path generally - fix where this path comes from.
        Utils util = new Utils(null);

        Log.i("NICK", "made it here3");


        List<Account> accounts = util.retrieveCurrentMonthsTransactions();

        Log.i("NICK", "made it here4");



        //linearLayout.addView(cardsTable);
        int i = 0;
        for(Account account: accounts){
            CardView card = new CardView(mContext);
            card.setBackground(rounded);
            Log.i("NICK1", account.getNum());
            TextView textView = new TextView(mContext);
            textView.setId(i);
            textView.setTextColor(Color.BLACK);
            textView.setText(account.getNum());
            textView.setBackground(rounded);
            textView.setPadding(5, 10, 5, 0);
            textView.setLayoutParams(new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            card.addView(textView);
            linearLayout.addView(card, i);

            ++i;

            for(Transaction tran: account.getTransactions()){
                CardView card1 = new CardView(mContext);
                card1.setBackground(rounded);
                TextView textView1 = new TextView(mContext);
                textView1.setId(i);
                textView1.setTextColor(Color.BLACK);
                textView1.setText(tran.getDescription() + " : " + tran.getAmount());
                textView1.setBackground(rounded);
                textView1.setPadding(5, 10, 5, 0);
                textView1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                card1.addView(textView1);
                linearLayout.addView(card1, i);
            }



            ++i;

        }


    }
}
