package com.manraj.assignment4.problem3;

import com.manraj.assignment4.utils.WriteToFileWithPath;

import java.io.IOException;
import java.util.List;

public class HighestFrequencyArticle {

    private static String path = "./Output/Assignment4/Problem3/output.txt";
    private static WriteToFileWithPath write = WriteToFileWithPath.getInstance();

    public static void printHighestFreqArticle(List<FrequencyNode> articles){

        FrequencyNode theOneAndOnly = null;
        double ratio = 0;

        for(FrequencyNode frequencyNode : articles){
            double currentRatio = ((double)frequencyNode.canadaCountPerArticle)/((double)frequencyNode.articleWordCount);
            if(theOneAndOnly == null || currentRatio > ratio){
                ratio = currentRatio;
                theOneAndOnly = frequencyNode;
            }
        }
        printOnConsole(theOneAndOnly, ratio);
    }

    private static void printOnConsole(FrequencyNode frequencyNode, double frequency){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n\n");
        stringBuilder.append("Highest Frequency Article with frequency 'f/m' ="+frequency+" :");
        stringBuilder.append("\n\t\t");
        stringBuilder.append(frequencyNode.document.toString());

        System.out.print(stringBuilder.toString());
        try {
            writeToFile(stringBuilder.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private static void writeToFile(String val) throws IOException {
        write.write(path, val);
    }
}
