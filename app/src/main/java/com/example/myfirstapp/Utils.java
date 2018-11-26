package com.example.myfirstapp;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Utils {

    private String path;

    public Utils(String path){
        this.path = path;
    }

    public List<Account> retrieveCurrentMonthsTransactions(){

        String fileName = "_transactions.xml";
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("CST"));
        List<Account> accounts = new ArrayList<>();

        //TODO: setup current month file read fully. Why calendar.year becomes 1 instead of 2018
        File inputTrans = new File(path + "2018-11" + fileName);//path + calendar.YEAR + "-" + calendar.MONTH + fileName);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try{
            documentBuilder = dbFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(inputTrans);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Account");


            for(int i = 0; i < nodeList.getLength(); ++i){
                //accounts.add(getAccount(nodeList.item(i)));
            }
        }
        catch( SAXException | ParserConfigurationException | IOException e){
            Logs logs = new Logs();
            logs.write("file exception - bad date is the likely cause: Message:" + e.getMessage());
            Log.i("NICK", e.getMessage());
            //Possibly create that file try read again and continue
            // Could also push event to read and create this months file.

        }

        accounts.addAll(getChaseTransactionsFromHtmlFile());

        return accounts;
    }

    private static Account getAccount(Node node){
        Account acct = new Account("", null);
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            acct.setNum(getTagValue("number", element));
            acct.setTransactions(getTransactions("transaction", element));

        }

        return acct;
    }

    private static String getTagValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag);
        if(nodeList == null) return "";

        Node node = (Node) nodeList.item(0);
        if(node == null) return "";

        return node.getTextContent();
    }

    private static List<Transaction> getTransactions(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag);
        List<Transaction> trans = new ArrayList<>();

        for(int i = 0; i<nodeList.getLength(); ++i){
            Element el = (Element) nodeList.item(i);
            Transaction tran = new Transaction("", "", "", "", null);
            tran.setAmount(getTagValue("amount", el));
            tran.setAssigned(getTagValue("assigned", el));
            tran.setDate(Calendar.getInstance());
            tran.setDescription(getTagValue("description", el));
            tran.setType(getTagValue("type", el));

            trans.add(tran);
        }

        return trans;

    }

    private List<Account> getChaseTransactionsFromHtmlFile(){
        String fileName = "example_chase.xml";
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("CST"));
        List<Account> accounts = new ArrayList<>();

        //TODO: setup current month file read fully. Why calendar.year becomes 1 instead of 2018
        File inputTrans = new File(path + fileName);//path + calendar.YEAR + "-" + calendar.MONTH + fileName);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        //DocumentBuilder documentBuilder;
        try{
            //documentBuilder = dbFactory.newDocumentBuilder();
            org.jsoup.nodes.Document doc = Jsoup.parse(inputTrans, null);
            //oc.getDocumentElement().normalize();
            //doc.getEl
            Elements chaseTrans = doc.getElementsByTag("tr");

            Account acct = new Account();
            acct.setNum("7848");//getTagValue("number", element));
            acct.setTransactions(getChaseTransactions(chaseTrans));

            accounts.add(acct);


        }
        catch(IOException e){
            Logs logs = new Logs();
            logs.write("file exception - bad date is the likely cause: Message:" + e.getMessage());
            Log.i("NICK", e.getMessage());
            //Possibly create that file try read again and continue
            // Could also push event to read and create this months file.

        }


        return accounts;
    }

    private static List<Transaction> getChaseTransactions(Elements elements){
        //NodeList nodeList = element.getElementsByTagName(tag);
        List<Transaction> trans = new ArrayList<>();

        //Got through each <tr>
        for(int i = 0; i<elements.size(); ++i){
            org.jsoup.nodes.Element el = elements.get(i);

            //make sure it's now column-header
            if(el.attr("class").equals("column-headers"))
                continue;

            //get transaction details amount - date - description
            Elements tranValues = el.getElementsByTag("td");
            Transaction tran = new Transaction("", "", "", "", null);
            for(int j = 0; j<tranValues.size(); ++j){

                org.jsoup.nodes.Element transactionAttribute = tranValues.get(j);
                String classAtt = transactionAttribute.attr("class");
                if(classAtt.contains("date")){
                    tran.setDate(Calendar.getInstance());
                }
                else if(classAtt.contains("amount"))
                {
                    tran.setAmount(getHtmlTagValue("span", transactionAttribute));
                }
                else if(classAtt.contains("description")){

                    tran.setDescription(getHtmlTagValue("span", transactionAttribute));
                }
                tran.setType("default");
                tran.setAssigned("b");
            }

            trans.add(tran);
        }

        return trans;

    }

    private static String getHtmlTagValue(String tag, org.jsoup.nodes.Element element){

        Elements elements = element.getElementsByTag(tag);
        if(elements == null) return "";

        org.jsoup.nodes.Node node = elements.get(0);
        if(node == null) return "";

        return ((org.jsoup.nodes.Element) node).text();
    }
}
