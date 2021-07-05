package com.manraj.assignment3;

import java.io.*;

public class Read_region_park_Data {

    FileReader fileReader = null;
    BufferedReader bufferedReader = null;
    String path = "Neo4j_ParksDataAndQueries/InputData/";
    public static int regionCount = 0;
    public static int parkCount = 0;

    public Read_region_park_Data(){
        File dir = new File(path);
        File files[] = dir.listFiles();
        if(files==null) return;


        for(File file : files){
            String fileData = readFile(file);
            String region = fetchRegionName(fileData);
            //System.out.println(region+"  :   ");
            String parks = fetchParkNames(fileData);
            //System.out.println(parks+"\n\n");
            new WriteToNeo4jQueryFile(region, parks);

        }
        writeRemainingCommands();

    }

    private void writeRemainingCommands(){
        String path = "Neo4j_ParksDataAndQueries/RegionParkQueries/";
        String fileName = "RegionParkQueries.txt";

        File file  = new File(path+fileName);
        try {

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("match(p:Park), (r:Region) where p.region=r.name create (p) - [:inside] -> (r)\n\n");
            bufferedWriter.write("match(p:Park), (r:Region) where p.region=r.name create (r) - [:contains] -> (p)\n\n");
            bufferedWriter.write("match (r1:Region), (r2:Region) where r1.name <> r2.name create (r1) - [:internal] -> (r2)\n\n");
            bufferedWriter.write("match (r: Region)\n" +
                    "with (r), Size( (r) - [:contains] -> ()) as total_Connections\n" +
                    "order by total_Connections DESC\n" +
                    "return r.name, total_Connections\n\n");
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String readFile(File file){
        StringBuilder fileData = new StringBuilder();
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                fileData.append(line);
        }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fileData.toString();
    }

    private String fetchRegionName(String fileData){
        String startString = "\"Region\": \"";
        int startPoint = fileData.indexOf(startString)+startString.length();
        fileData = fileData.substring(startPoint, fileData.length());
        int endPoint = fileData.indexOf("\"");
        return fileData.substring(0, endPoint);
    }

    private String fetchParkNames(String fileData){
        String startString = "\"Parks\": [";
        int startIndex = fileData.indexOf(startString) + startString.length();
        int endIndex = fileData.lastIndexOf("]");
        fileData = fileData.substring(startIndex, endIndex);

        return recursiveFetchParks(fileData);
    }

    private String recursiveFetchParks(String parkNames){
        if(parkNames.length()<=2){
            return "";
        }

        int startIndex = parkNames.indexOf("\"")+1;
        if(startIndex<0)return "";
        parkNames = parkNames.substring(startIndex, parkNames.length());
        int endIndex = parkNames.indexOf("\"");
        if(endIndex<0)return "";
        String parkName = parkNames.substring(0, endIndex);
        parkNames = parkNames.substring(endIndex+1, parkNames.length());
        String returnVal = recursiveFetchParks(parkNames);
        if(returnVal != "")
            return parkName + "," + returnVal;
        else
            return parkName;
    }



}
