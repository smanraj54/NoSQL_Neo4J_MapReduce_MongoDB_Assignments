package com.manraj.assignment4.problem3;

import com.manraj.assignment4.utils.DeleteExistingFiles;
import com.manraj.assignment4.utils.MongoDBConnection;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Assignment4_P3 {

    public static void main (String[] args) {
        new DeleteExistingFiles("./Output/Assignment4/Problem3/");

        FindIterable<Document> iter = MongoDBConnection.getInstance().mongoDBCollection.find();
        Iterator it = iter.iterator();
        //List<String> articles = new ArrayList<>();
        int t=0;
        while (it.hasNext()) {
            t++;
            Object object =  it.next();
            Document document = (Document)object;
            new FrequencyNode((String) document.get("content"), document);
            //articles.add();
            //System.out.println(++t+": "+document.get("content"));
        }

        FrequencyNode.setTotalArticles(t);
        List<FrequencyNode> articles = FrequencyNode.getArticles();
        try {
            PrintToConsole print = PrintToConsole.getInstance();
            print.iterateAllDocs(articles);
            print.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        HighestFrequencyArticle.printHighestFreqArticle(articles);
        MongoDBConnection.getInstance().close();
        //System.out.println("hello World");

    }

}
