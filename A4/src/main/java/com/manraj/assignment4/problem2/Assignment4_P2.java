package com.manraj.assignment4.problem2;

import com.manraj.assignment4.utils.DeleteExistingFiles;
import com.manraj.assignment4.utils.MongoDBConnection;
import com.mongodb.client.FindIterable;

import org.bson.Document;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Assignment4_P2 {

    public static void main (String[] args) throws IOException {
        new DeleteExistingFiles("./Output/Assignment4/Problem2/");
        FindIterable<Document> iter = MongoDBConnection.getInstance().mongoDBCollection.find();
        Iterator it = iter.iterator();
        int t=0;
        while (it.hasNext()) {
            Object object =  it.next();
            Document document = (Document)object;
            try {
                new DocumentNode((String) document.get("content"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //System.out.println(++t+": "+document.get("content"));
        }
        List<DocumentNode> docs = DocumentNode.articles;
        WriteOutputToFIle instance = WriteOutputToFIle.getInstance("./Output/Assignment4/Problem2/output.csv");
        instance.iterateAllDocs(docs);
        instance.close();
        PrintToConsole.getInstance().iterateAllDocs(docs);
        PrintToConsole.getInstance().close();
        MongoDBConnection.getInstance().close();
        //System.out.println("hello World");

    }

}
