package com.example.myfirstapp;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.Calendar;
import java.util.List;

public class UtilsUnitTests {

    private final String chasePathVirtual = "C:\\Users\\Nick's Big PC\\OneDrive\\budgetData\\";
    private final String chasePathLaptop = "C:\\Users\\Darth Bane\\OneDrive\\budgetData\\";

    @Test
    public void canReadFile()
    {

        //URL url = getClass().getResource("app/sampledata/2018-11_transactions.xml");
        //TODO: create relative path to sampledata/2018-11_transactions.xml
        Utils util = new Utils(chasePathLaptop);//"F:\\AndriodApp\\app\\sampledata\\");//url.toString());
        List<Account> accounts = util.retrieveCurrentMonthsTransactions();

        for(Account acct : accounts){

            Assert.assertEquals(acct.getNum(), "7848");
            Assert.assertEquals(acct.getTransactions().size(), 60);
            List<Transaction> trans = acct.getTransactions();

            Transaction tran1 = new Transaction("wendy's", "12.10", "n", "food", Calendar.getInstance());
            Transaction tran2 = new Transaction("Ulta", "35.00", "m", "beauty", Calendar.getInstance());
            Transaction tran3 = new Transaction("Scotty P's", "21.38", "b", "food", Calendar.getInstance());
            Transaction tran4 = new Transaction("pets mart", "106.00", "b", "dogs", Calendar.getInstance());
            Transaction tran5 = new Transaction("Coppell water", "56.20", "b", "utilities", Calendar.getInstance());

            //TODO: create comparision method for transactions.
            /*Assert.assertTrue(trans.contains(tran1));
            Assert.assertTrue(trans.contains(tran2));
            Assert.assertTrue(trans.contains(tran3));
            Assert.assertTrue(trans.contains(tran4));
            Assert.assertTrue(trans.contains(tran5));
            */


        }

    }
}
