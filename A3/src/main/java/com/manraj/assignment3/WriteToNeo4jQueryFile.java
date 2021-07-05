package com.manraj.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteToNeo4jQueryFile {

    private FileWriter fileWriter = null;
    private BufferedWriter bufferedWriter = null;
    private String path = "Neo4j_ParksDataAndQueries/RegionParkQueries/";
    private String fileName = "RegionParkQueries.txt";
    private String region = null;
    private String parkList = null;

    public WriteToNeo4jQueryFile(String region, String parkList){
        this.parkList = parkList;
        this.region = region;
        Path path1 = Paths.get(path);
        try {
            Files.createDirectories(path1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        File file = new File(path+fileName);
        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        writeCreateNodeQueryToFile();
        try {
            bufferedWriter.write("\n\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void writeCreateNodeQueryToFile(){
        String parks[] = parkList.split(",");
        String query = "CREATE (R"+(++Read_region_park_Data.regionCount)+":Region {name: '"+region+"'})\n";
        try {
            bufferedWriter.write(query);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        for(String park : parks){
            query = "CREATE (P"+(++Read_region_park_Data.parkCount)+":Park {name: '"+park+"', region: '"+region+"'})\n";
            try {
                bufferedWriter.write(query);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
