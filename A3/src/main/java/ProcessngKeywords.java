import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProcessngKeywords {

    public static List<String> keyWords = null;
    public static List<String> MapReduceKeyWords = null;

    public ProcessngKeywords(){
        keyWords = new ArrayList<>();
        keyWords.add("Canada");
        keyWords.add("University");
        keyWords.add("Dalhousie");
        keyWords.add("Halifax");
        keyWords.add("Canada%20Education");
        keyWords.add("Moncton");
        keyWords.add("Toronto");

        MapReduceKeyWords = new ArrayList<>();
        MapReduceKeyWords.add("Canada");
        MapReduceKeyWords.add("Nova Scotia");
        MapReduceKeyWords.add("education");
        MapReduceKeyWords.add("higher");
        MapReduceKeyWords.add("learning");
        MapReduceKeyWords.add("city");
        MapReduceKeyWords.add("accommodation");
        MapReduceKeyWords.add("price");
        fetchData();
    }
    private void fetchData(){
        String URL = "https://newsapi.org/v2/everything?q=<KeywordName>&sortBy=popularity&apiKey=6e7ddac0bd2e44ec82aa90073f939e16&language=en&pageSize=90";
        for( String keyWord : keyWords){
            String temp = URL.replace("<KeywordName>",keyWord);
            //System.out.println(temp);
            String rawData = new FetchAPI().fetchAPI(temp);
            ArticleSeparation articleSeparation = new ArticleSeparation();
            System.out.println(articleSeparation.getArticles(rawData));
        }
        WriteToFile.getInstance().close();
        new WriteToMongoFile();

        new MapReduce();
        new MongoDBConnection();

    }


    public static List<String> getKeywordsList(){
        return keyWords;
    }


}
