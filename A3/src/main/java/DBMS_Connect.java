import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class DBMS_Connect {


    public static void main (String[] args) {

        String URL = "https://newsapi.org/v2/everything?q=<KeywordName>&sortBy=popularity&apiKey=6e7ddac0bd2e44ec82aa90073f939e16&language=en&pageSize=5";
        URL = URL.replace("<KeywordName>","Dalhousie");
        new FetchAPI(URL);
    }
}
