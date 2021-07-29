package com.manraj.assignment4;

import com.mongodb.client.FindIterable;

import org.bson.BsonDocument;
import org.bson.Document;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Assignment4 {

    public static void main (String[] args) {
        new DeleteExistingFiles();
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
        MongoDBConnection.getInstance().close();
        //System.out.println("hello World");

    }

}
