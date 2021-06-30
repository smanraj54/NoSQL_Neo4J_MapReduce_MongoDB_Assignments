import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchAPI {

    private static HttpURLConnection connection = null;

    public FetchAPI(String URL){
        BufferedReader bufferedReader;
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        java.net.URL url = null;
        try {


            url = new URL("https://newsapi.org/v2/everything?q=Dalhousie&sortBy=popularity&apiKey=6e7ddac0bd2e44ec82aa90073f939e16");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status>299){
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            else{
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }

            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            bufferedReader.close();
            System.out.println(stringBuffer.toString());

            //System.out.println(status);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        finally{
            connection.disconnect();
        }
    }
}
