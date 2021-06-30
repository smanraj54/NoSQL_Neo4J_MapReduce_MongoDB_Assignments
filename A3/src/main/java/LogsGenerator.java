import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LogsGenerator {
    //private BufferedWriter bufferedWriter = null;
    //private FileWriter fileWriter = null;
    //private FileHandler fileHandler = null;
    //private Logger logger = null;
    private FileOutputStream fileOutputStream = null;
    private static LogsGenerator logsGenerator= null;
    private Map<String, FileOutputStream> fileOutputStreamPerTransaction = null;
    private LogsGenerator() throws IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MMM_yy");
        String fileName = "./src/main/Logs/logs_"+simpleDateFormat.format(date)+".log";
        //fileWriter = new FileWriter(fileName, true);
        fileOutputStream = new FileOutputStream(new File(fileName), true);
//        if(logsGenerator.fileOutputStreamPerTransaction == null){
//            logsGenerator.fileOutputStreamPerTransaction = new HashMap<>();
//        }
//        if(!logsGenerator.fileOutputStreamPerTransaction.containsKey(transaction)){
//            logsGenerator.fileOutputStreamPerTransaction.put(transaction, fileOutputStream);
//        }
        //this.bufferedWriter = new BufferedWriter(fileWriter);

        //fileHandler = new FileHandler(fileName, true);
        //logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        //logger.addHandler(fileHandler);


    }
    public static LogsGenerator getInstance() throws IOException {
        if(logsGenerator==null){
            logsGenerator = new LogsGenerator();
        }

        return logsGenerator;
    }

    public void writeLogs(String logs ) throws IOException {
        this.fileOutputStream.write(logs.getBytes(), 0, logs.length());;
        //logger.info(logs);
        //bufferedWriter.write(logs);
        //System.out.println(logs);
    }
    public void closeLogs() throws IOException {
        String logs = "\n\n\n\t\t\t\t********Logger Closed**********";
        this.fileOutputStream.write(logs.getBytes(), 0, logs.length());
        //bufferedWriter.write(logs);
        //bufferedWriter.close();
    }


}
