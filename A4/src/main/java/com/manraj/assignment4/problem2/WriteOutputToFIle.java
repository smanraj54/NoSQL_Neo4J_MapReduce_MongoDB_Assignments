package com.manraj.assignment4.problem2;

import com.manraj.assignment4.utils.WriteToFileWithPath;

import java.io.IOException;
import java.util.List;

public class WriteOutputToFIle {

    private static String path = null;
    private static WriteOutputToFIle instance = null;

    private WriteOutputToFIle(){
        writeHeader();
    }

    public void write(DocumentNode documentNode){
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append(documentNode.getArticleNumber());
        stringBuilder.append(',');
        stringBuilder.append(documentNode.getPolarityStatus());
        stringBuilder.append(',');
        stringBuilder.append("\""+documentNode.getMatch()+"\"");
        stringBuilder.append(',');
        stringBuilder.append("\""+documentNode.getContent()+"\"");
        stringBuilder.append('\n');
        try {
            WriteToFileWithPath.getInstance().write(path, stringBuilder.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        //System.out.println(stringBuilder.toString());
    }
    private void writeHeader(){
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append("News Article");
        stringBuilder.append(',');
        stringBuilder.append("polarity");
        stringBuilder.append(',');
        stringBuilder.append("\"match\"");
        stringBuilder.append(',');
        stringBuilder.append("\"News Description/Content\"");
        stringBuilder.append("\n\n");
        //System.out.println(stringBuilder.toString());
        try {
            WriteToFileWithPath.getInstance().write(path, stringBuilder.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public static WriteOutputToFIle getInstance(String pathString){
        if(instance == null){
            path = pathString;
            instance = new WriteOutputToFIle();
        }
        return instance;
    }

    public void iterateAllDocs(List<DocumentNode> allNodes){
        for(DocumentNode doc : allNodes){
            write(doc);
        }
    }

    public void close(){
        WriteToFileWithPath.getInstance().close();
        instance = null;
        path = null;
    }
}

