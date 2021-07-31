package com.manraj.assignment4.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    public MongoClient MongoClient;
    public MongoCollection mongoDBCollection;
    public MongoDatabase database;

    private static MongoDBConnection instance = null;

    private MongoDBConnection(){
        MongoClient= connectToMongoDB();
        System.out.println("Connection Created");
        //List<Document> MongoEntryList = getDocumentsList();
        database = MongoClient.getDatabase("myMongoNews");
        //database.getCollection("articles").drop();
        mongoDBCollection = database.getCollection("articles");
        //mongoDBCollection.insertMany(MongoEntryList);

    }

    public static MongoDBConnection getInstance(){

        if(instance == null){
            instance = new MongoDBConnection();
        }
        return instance;

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

    public void close(){
        MongoClient.close();
        instance = null;
    }
    /*private List<Document> getDocumentsList(){
        List<Document> MongoEntryList = new ArrayList<>();

        for(CleanNode node : CleanNode.getCleanNodesList()){
            Document document = new Document();
            document.append("title", node.getTitle());
            document.append("content", node.getContent());
            MongoEntryList.add(document);
        }

        return MongoEntryList;
    }*/

}
