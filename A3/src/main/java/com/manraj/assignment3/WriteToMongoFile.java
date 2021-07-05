package com.manraj.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToMongoFile {
    public WriteToMongoFile(){
        File file = new File(WriteToFile.mongoDataPath);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("{\n\"articles\": [\n");
            boolean first = true;
            System.out.println("\n\n\t\t"+CleanNode.getCleanNodesList().size()+"\n\n");
            for(CleanNode node : CleanNode.getCleanNodesList()){
                if(!first){
                    bufferedWriter.write(",\n");
                }
                bufferedWriter.write("\t{\"title\":\""+node.getTitle()+"\",");
                bufferedWriter.write("\"content\":\""+node.getContent()+"\"");

                first = false;

                bufferedWriter.write("}");
            }
            bufferedWriter.write("\n]\n}");

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }

    }
}
