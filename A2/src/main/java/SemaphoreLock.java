import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class SemaphoreLock {

    private static int semaphoreValue = 1;
    private static Map<String, Integer> queueList = null;
    private static SemaphoreLock semaphoreLock = null;
    private static int counter = 0;

    private SemaphoreLock(){
    }
    public static SemaphoreLock getInstance(){
        if(semaphoreLock==null){
            semaphoreLock = new SemaphoreLock();
            queueList = new HashMap<>();
        }
        return semaphoreLock;
    }

    public void waitForSemaphore(String transactionName) throws InterruptedException {
        if(!queueList.containsKey(transactionName)){
            queueList.put(transactionName, (int) transactionName.charAt(1) - 48);
            System.out.println("\n\n\n\n\t\t\t\t"+ queueList.get(transactionName) + ".) "+ transactionName + "\n\t\t\t\t Semaphore Value:"+ queueList.get(transactionName)+"\n\n\n\n");
        }
        try {
            LogsGenerator.getInstance().writeLogs("\n\n\tTransaction: " + transactionName + " is WAITING for the Table access!!! ");
        }catch(Exception exception){
            exception.printStackTrace();
        }
        while (queueList.get(transactionName)!=semaphoreValue){
            sleep(queueList.get(transactionName)*900);
            semaphoreValue = queueList.get(transactionName);
            if(queueList.get(transactionName)==semaphoreValue) break;
        }
        try {
            LogsGenerator.getInstance().writeLogs("\n\tTransaction: " + transactionName + " Has LOCKED Table!!! ");
        }catch(Exception exception){
            exception.printStackTrace();
        }
        System.out.println("\n\n\nWait is Over!!!");
    }
    public void signalSemaphore(String transactionName){
        try {
            LogsGenerator.getInstance().writeLogs("\n\tTransaction: " + transactionName + " Has UnLOCKED Table!!! ");
        }catch(Exception exception){
            exception.printStackTrace();
        }
        SemaphoreLock.semaphoreValue = SemaphoreLock.semaphoreValue+1;
        System.out.println("\n\n\n\n\t\t\t\t Semaphore Current Value =  "+ semaphoreValue + "\n\n\n\n");
    }
}
