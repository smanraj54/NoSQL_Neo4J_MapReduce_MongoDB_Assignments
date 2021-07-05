package com.manraj.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MapReduce {

    private String dataWritePath = "./Output/MapReduceCount/";
    private String fileName = "Keywords_Count.json";
    private FileWriter fileWriter = null;
    private BufferedWriter bufferedWriter = null;
    private String dataFetchPath = "./Output/RawArticlesData/";

    public MapReduce(){

        try {
            Path path1 = Paths.get(dataWritePath);
            Files.createDirectories(path1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("\n\n\n");
        writeHeaderOfFile();
        mapAllFiles();
        Reduce reduce = new Reduce();
        writeData();
        writeFooterOfFile();
        System.out.println("\n\n\n");
    }

    private void writeData(){
        boolean first = true;
        for(String keyword : ProcessngKeywords.MapReduceKeyWords){
            int value = Reduce.mapFinalCount.get(keyword);
            try {
                if(!first){
                    System.out.println();
                    bufferedWriter.write(",\n");
                }
                first = false;

                String Value = "\""+keyword + "\":\""+Integer.toString(value)+"\"";
                System.out.print(Value);
                bufferedWriter.write(Value);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void mapAllFiles(){
        File dir = new File(dataFetchPath);
        File files[] = dir.listFiles();
        if(files == null){
            return;
        }
        for(File file: dir.listFiles()){
            MapFileData mapFileData = new MapFileData(file);
        }
    }


    private void writeHeaderOfFile(){
        try {
            fileWriter = new FileWriter(new File(dataWritePath+fileName));
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[\n{\n");
            System.out.print("[\n{\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private void writeFooterOfFile(){
        try {
            bufferedWriter.write("\n}\n]");
            System.out.print("\n}\n]");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
