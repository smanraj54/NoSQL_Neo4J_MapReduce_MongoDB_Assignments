package com.manraj.assignment4;

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

    private WriteToFileWithPath(String path, String data){
        File file = new File(path);
        try {
            Path path1 = Paths.get("./Output/Assignment4/");
            Files.createDirectories(path1);

            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public WriteToFileWithPath getInstance(String path, String data){
        if(instance == null){
            instance = new WriteToFileWithPath(path, data);
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
