package com.manraj.assignment4.problem3;

import com.manraj.assignment4.problem3.FrequencyNode;
import com.manraj.assignment4.utils.WriteToFileWithPath;

import java.io.IOException;
import java.util.List;

public class PrintToConsole {

    private static int counter = 0;
    private static PrintToConsole instance = null;
    private String path = "./Output/Assignment4/Problem3/output.txt";
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
        stringBuilder.append("Search Query");
        stringBuilder.append("\t || \t\t");
        stringBuilder.append("Document with keyword");
        stringBuilder.append("\t || \t");
        stringBuilder.append("\"total documents/appeared documents\"");
        stringBuilder.append("\t || \t");
        stringBuilder.append("\"log_10(N)\"");
        stringBuilder.append("\n\n");

        double wordCount = (double) FrequencyNode.getCanadaCount();
        double totalCount = (double) FrequencyNode.getTotalArticles();
        double log = Math.log10(totalCount/wordCount);
        stringBuilder.append("Canada");
        stringBuilder.append("\t\t\t || \t\t\t");
        stringBuilder.append(wordCount);
        stringBuilder.append("\t\t\t\t || \t\t\t");
        stringBuilder.append(totalCount/wordCount);
        stringBuilder.append("\t\t\t\t || \t");
        stringBuilder.append(log);
        stringBuilder.append("\n");

        wordCount = (double) FrequencyNode.getMonctonCount();
        log = Math.log10(totalCount/wordCount);
        stringBuilder.append("Moncton");
        stringBuilder.append("\t\t\t || \t\t\t");
        stringBuilder.append(wordCount);
        stringBuilder.append("\t\t\t\t || \t\t\t");
        stringBuilder.append(totalCount/wordCount);
        stringBuilder.append("\t\t\t\t || \t");
        stringBuilder.append(log);
        stringBuilder.append("\n");

        wordCount = (double) FrequencyNode.getTorontoCount();
        log = Math.log10(totalCount/wordCount);
        stringBuilder.append("Toronto");
        stringBuilder.append("\t\t\t || \t\t\t");
        stringBuilder.append(wordCount);
        stringBuilder.append("\t\t\t\t || \t\t\t");
        stringBuilder.append(totalCount/wordCount);
        stringBuilder.append("\t\t\t\t\t\t\t || \t");
        stringBuilder.append(log);
        stringBuilder.append("\n\n\n");

        stringBuilder.append("Canada Appeared in "+ FrequencyNode.canadaCount+" Documents");
        stringBuilder.append("\t || \t");
        stringBuilder.append("Total Words");
        stringBuilder.append("\t\t || \t\t");
        stringBuilder.append("Frequency");
        stringBuilder.append("\n\n");

        System.out.print(stringBuilder.toString());
        writeToFile(stringBuilder.toString());


    }

    private void writeToFile(String val) throws IOException {
        write.write(path, val);
    }

    public void Print(FrequencyNode frequencyNode) throws IOException {
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append("ARTICLE#"+(++counter));
        stringBuilder.append("\t\t\t\t\t\t || \t\t\t");
        stringBuilder.append(frequencyNode.articleWordCount);
        stringBuilder.append("\t\t || \t\t\t");
        stringBuilder.append(frequencyNode.canadaCountPerArticle);
        stringBuilder.append('\n');

        System.out.print(stringBuilder.toString());
        writeToFile(stringBuilder.toString());

    }

    public void iterateAllDocs(List<FrequencyNode> allNodes) throws IOException {
        for(FrequencyNode doc : allNodes){
            Print(doc);
        }
    }

    public void close(){
        write.close();
        counter = 0;
        instance = null;
    }

}
