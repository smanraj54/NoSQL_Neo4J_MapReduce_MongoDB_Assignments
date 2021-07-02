public class ArticleSeparation {

    public String getArticles(String rawData){
        int startPoint = rawData.indexOf("\"articles\":[")+12;
        int endPoint = rawData.lastIndexOf("]");
        rawData = rawData.substring(startPoint, endPoint);
        WriteToFile writeRawData = WriteToFile.getInstance();
        //WriteToFile writeRawData1 = new WriteToFile(this.mongoDataPath.replace(".json", "_"+WriteToFile.fileNumber+"_.json"));
        writeRawData.processArticles(rawData);
        //writeRawData1.writeArticleToFile(rawData);

        return rawData;
    }

}
