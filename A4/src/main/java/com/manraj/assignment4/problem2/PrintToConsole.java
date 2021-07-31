package com.manraj.assignment4.problem2;

import com.manraj.assignment4.utils.WriteToFileWithPath;

import java.io.IOException;
import java.util.List;

public class PrintToConsole {

    private static PrintToConsole instance = null;
    private String path = "./Output/Assignment4/Problem2/output.txt";
    private WriteToFileWithPath write = WriteToFileWithPath.getInstance();
    private PrintToConsole() throws IOException {
        PrintHeader();
    }

    public static PrintToConsole getInstance() throws IOException {
        if(instance == null){
            instance = new PrintToConsole();
        }
        return instance;
    }

    public void PrintHeader() throws IOException {
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append("News Article");
        stringBuilder.append("\t || \t\t\t");
        stringBuilder.append("polarity");
        stringBuilder.append("\t\t\t || \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        stringBuilder.append("\"match\"");
        stringBuilder.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t || \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        stringBuilder.append("\"News Description/Content\"");
        stringBuilder.append("\n\n");
        System.out.print(stringBuilder.toString());
        writeToFile(stringBuilder.toString());


    }

    private void writeToFile(String val) throws IOException {
        write.write(path, val);
    }

    public void Print(DocumentNode documentNode) throws IOException {
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append(documentNode.getArticleNumber());
        stringBuilder.append("\t\t\t\t || \t\t\t");
        stringBuilder.append(documentNode.getPolarityStatus());
        stringBuilder.append("\t\t\t || \t\t\t");
        stringBuilder.append("\""+documentNode.getMatch()+"\"");
        stringBuilder.append("\t\t\t || \t\t\t");
        stringBuilder.append("\""+documentNode.getContent()+"\"");
        stringBuilder.append('\n');

        System.out.print(stringBuilder.toString());
        writeToFile(stringBuilder.toString());

    }

    public void iterateAllDocs(List<DocumentNode> allNodes) throws IOException {
        for(DocumentNode doc : allNodes){
            Print(doc);
        }
    }

    public void close(){
        write.close();
        instance = null;
    }

}
