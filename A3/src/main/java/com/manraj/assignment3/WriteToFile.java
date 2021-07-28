package com.manraj.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.nio.file.Path;

public class WriteToFile {

    public static int countOfArticlesPerFile = 0;
    public static int fileNumber = 0;
    public static boolean fileClosed = false;
    private static WriteToFile instance= null;

    public static String rawDataPath = "./Output/RawArticlesData/RawArticlesData.json";
    public static String mongoDataPath = "./Output/MongoArticlesProcessed/MongoArticlesProcessed.json";

    private FileWriter fileWriter = null;
    private BufferedWriter bufferedWriter = null;

    private WriteToFile(){
        File file = new File(rawDataPath);
        try {
            Path path1 = Paths.get("./Output/RawArticlesData/");
            Files.createDirectories(path1);
            path1 = Paths.get("./Output/MongoArticlesProcessed/");
            Files.createDirectories(path1);

            this.fileWriter = new FileWriter(file, true);
            this.bufferedWriter = new BufferedWriter(fileWriter);
            writeArticleToFile("{\n\"articles\": [", false);
            fileClosed = false;
            countOfArticlesPerFile--;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static WriteToFile getInstance(){

        if(instance==null){
            instance = new WriteToFile();
        }
        return instance;
    }



    public void processArticles(String value){
        while(true){
            int startIndex = value.indexOf("{");
            int endIndex = value.indexOf("chars]\"}");
            if(startIndex<0 || endIndex<=0 || endIndex<=startIndex){
                break;
            }
            String tempVal = value.substring(startIndex, endIndex+8);

            getNodeData(tempVal);

            writeArticleToFile(tempVal, true);
            fileClosed = false;
            value = value.substring(endIndex+8, value.length());
        }


    }

    private void writeArticleToFile(String value, boolean needChecks){

        try {
            if(WriteToFile.countOfArticlesPerFile>=5 && needChecks){
                WriteToFile.fileNumber++;
                WriteToFile.countOfArticlesPerFile=0;
                createNewFile();
            }
            if(WriteToFile.countOfArticlesPerFile++>0 && needChecks){
                this.bufferedWriter.write(",\n");
            }
            this.bufferedWriter.write(value);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void getNodeData(String value){

        int startTitleIndex = getIndexFromEndWord(value, "\"title\":\"");
        value = value.substring(startTitleIndex, value.length());
        int endTitleIndex = getIndexFromStartWord(value,"\",\"");
        String title = value.substring(0, endTitleIndex);

        int startContentIndex = getIndexFromEndWord(value, ",\"content\":\"");
        value = value.substring(startContentIndex, value.length());
        int endContentIndex = getIndexFromStartWord(value," chars]\"}");
        String content = value.substring(0, endContentIndex);

        content = content.substring(0, content.lastIndexOf("["));
        title = cleanData(title);
        content = cleanData(content);

        CleanNode cleanNode = new CleanNode(title, content);
    }

    private String cleanData(String data){
        data = data.replaceAll("(\r\n|\n)", ". ");
        data = data.replaceAll("[^0-9a-zA-Z:,\\s?!()\\/\\.]+", "");
        data = data.replaceAll("\\<.*?\\>", "");
        return data;
    }

    private int getIndexFromEndWord(String value, String comparator){
        return (value.indexOf(comparator)+comparator.length());
    }
    private int getIndexFromStartWord(String value, String comparator){
        return (value.indexOf(comparator));
    }


    public void close(){
        closeWriters();
        instance = null;
    }

    public void closeWriters(){
        try {
            if(!fileClosed){
                writeArticleToFile("\n]\n}", false);
                fileClosed = true;
            }
            countOfArticlesPerFile--;
            this.bufferedWriter.close();
            this.fileWriter.close();
        } catch (IOException ioException) {
            //ioException.printStackTrace();
        }
    }

    private void createNewFile(){
        closeWriters();
        String pathTemp = rawDataPath.replace(".json", "_"+WriteToFile.fileNumber+"_.json");
        File file = new File(pathTemp);
        try {
            this.fileWriter = new FileWriter(file, true);
            this.bufferedWriter = new BufferedWriter(fileWriter);
            writeArticleToFile("{\n\"articles\": [", false);
            fileClosed = false;
            countOfArticlesPerFile--;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}
