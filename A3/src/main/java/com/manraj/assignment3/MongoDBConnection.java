package com.manraj.assignment3;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBConnection {

    public MongoDBConnection(){

        MongoClient MongoClient= connectToMongoDB();
        System.out.println("Connection Created");
        List<Document> MongoEntryList = getDocumentsList();
        MongoDatabase database = MongoClient.getDatabase("myMongoNews");
        //database.getCollection("articles").drop();
        MongoCollection mongoDBCollection = database.getCollection("articles");
        mongoDBCollection.insertMany(MongoEntryList);
        MongoClient.close();

    }

    private MongoClient connectToMongoDB(){
        System.out.println("Creating Connection");
        ConnectionString connectionString = new ConnectionString("mongodb+srv://mn697903:manu1234@cluster0.zqzz1.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        return mongoClient;


    }

    private List<Document> getDocumentsList(){
        List<Document> MongoEntryList = new ArrayList<>();

        for(CleanNode node : CleanNode.getCleanNodesList()){
            Document document = new Document();
            document.append("title", node.getTitle());
            document.append("content", node.getContent());
            MongoEntryList.add(document);
        }

        return MongoEntryList;
    }

}
