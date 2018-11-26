package com.example.myfirstapp;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class TransactionsView extends AppCompatActivity {
    private Context mContext;

    private final String chasePathVirtual = "C:\\Users\\Nick's Big PC\\OneDrive\\budgetData\\";

    private final String phonePath = "/storage/emulated/0/Download/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("NICK", "made it here1");
        setContentView(R.layout.activity_transactions_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the application context
        mContext = getApplicationContext();

        Resources resources = mContext.getResources();
        final int resourceId = resources.getIdentifier("rounded", "drawable",
                mContext.getPackageName());
        Drawable rounded = resources.getDrawable(resourceId);

        CardView cardsTable = (CardView) findViewById(R.id.card_view);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        Log.i("NICK", "made it here2");

        //TODO: figure out how to get path generally - fix where this path comes from.
        Utils util = new Utils(phonePath);

        Log.i("NICK", "made it here3");


        List<Account> accounts = util.retrieveCurrentMonthsTransactions();

        Log.i("NICK", "made it here4");



        //linearLayout.addView(cardsTable);
        int i = 0;
        for(Account account: accounts){
            CardView card = new CardView(mContext);
            Log.i("NICK1", account.getNum());
            TextView textView = new TextView(mContext);
            textView.setId(i);
            textView.setTextColor(Color.BLACK);
            textView.setText(account.getNum());
            textView.setBackground(rounded);
            textView.setPadding(5, 10, 5, 0);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            card.addView(textView);
            linearLayout.addView(card, i);

            ++i;

            for(Transaction tran: account.getTransactions()){
                CardView card1 = new CardView(mContext);
                TextView textView1 = new TextView(mContext);
                textView1.setId(i);
                textView1.setTextColor(Color.BLACK);
                textView1.setText(tran.toString());
                textView1.setBackground(rounded);
                textView1.setPadding(5, 10, 5, 0);
                textView1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                card1.addView(textView1);
                linearLayout.addView(card1, i);
            }



            ++i;

        }



        //cardsTable.addView(relativeLayout);



       // cardsTable.




       // InputStreamReader reader = new InputStreamReader()

        //View viewRow = new View();

        //cardsTable.addView();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transactions_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
