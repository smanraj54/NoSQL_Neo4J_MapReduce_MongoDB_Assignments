package com.manraj.assignment3;

public class ArticleSeparation {

    public String getArticles(String rawData){
        int startPoint = rawData.indexOf("\"articles\":[")+12;
        int endPoint = rawData.lastIndexOf("]");
        rawData = rawData.substring(startPoint, endPoint);
        WriteToFile writeRawData = WriteToFile.getInstance();
        //com.manraj.assignment3.WriteToFile writeRawData1 = new com.manraj.assignment3.WriteToFile(this.mongoDataPath.replace(".json", "_"+com.manraj.assignment3.WriteToFile.fileNumber+"_.json"));
        writeRawData.processArticles(rawData);
        //writeRawData1.writeArticleToFile(rawData);

        return rawData;
    }

}
