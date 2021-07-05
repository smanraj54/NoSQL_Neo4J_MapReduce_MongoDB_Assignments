package com.manraj.assignment3;

import java.io.*;

public class MapFileData {
    public MapFileData(File file){
        String value = "";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder data = new StringBuilder();

            while((value=bufferedReader.readLine()) !=null ){
                data.append(value);
                data.append(System.lineSeparator());
            }
            value = data.toString();
            countMapKeyWords(value);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private void countMapKeyWords( String value){
        MapNode mapNode = new MapNode();
        for(String keyword : ProcessngKeywords.MapReduceKeyWords){
            int count = countSingleKeyword(value, keyword);
            mapNode.MapCount.put(keyword, count);
        }
    }

    private int countSingleKeyword(String value, String keyword){
        if(value.length()<keyword.length()){
            return 0;
        }
        int index = value.indexOf(keyword);
        if(index<0){
            return 0;
        }
        value = value.substring(index+keyword.length(), value.length());
        int count = 1+countSingleKeyword(value, keyword);
        return count;
    }

}
