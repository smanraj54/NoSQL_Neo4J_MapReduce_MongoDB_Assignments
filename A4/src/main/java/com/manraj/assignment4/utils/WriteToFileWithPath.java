package com.manraj.assignment4.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteToFileWithPath {

    private static WriteToFileWithPath instance = null;
    private FileWriter fileWriter = null;
    private BufferedWriter bufferedWriter = null;

    private WriteToFileWithPath(){

        try {
            Path path1 = Paths.get("./Output/Assignment4/Problem2/");
            Files.createDirectories(path1);
            Path path2 = Paths.get("./Output/Assignment4/Problem3/");
            Files.createDirectories(path1);
            Files.createDirectories(path2);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void write(String path, String data) throws IOException {
        File file = new File(path);
        fileWriter = new FileWriter(file, true);
        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.flush();
    }

    public static WriteToFileWithPath getInstance(){
        if(instance == null){
            instance = new WriteToFileWithPath();
        }
        return instance;
    }

    public void close(){
        closeWriters();
        instance = null;
    }

    public void closeWriters(){
        try {
            this.bufferedWriter.close();
            this.fileWriter.close();
            instance = null;
        } catch (IOException ioException) {
            //ioException.printStackTrace();
        }
    }

}
