import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Transactions {

    public void RunTransactions(){
        //Connection remoteConnection = null;


        try
        {
            LogsGenerator.getInstance().writeLogs("\n\n\n\n\t\t\t\t********NEW Program********\n\n");
            String url = "jdbc:mysql://35.232.204.126/task2";
            String user = "root";
            String password = "manu@1234";
            RemoteConnection remoteConnection = new RemoteConnection();
            int prefix = 1151;
            Connection connections[] = {remoteConnection.getRemoteConnection(url, user, password),
                    remoteConnection.getRemoteConnection(url, user, password),
                    remoteConnection.getRemoteConnection(url, user, password)
            };


            TransactionQueries transaction1 = new TransactionQueries("T1", connections[0], prefix);
            Thread t1 = new Thread(transaction1);
            t1.start();


            TransactionQueries transaction2 = new TransactionQueries("T2", connections[1], prefix);
            Thread t2 = new Thread(transaction2);
            t2.start();

            TransactionQueries transaction3 = new TransactionQueries("T3", connections[2], prefix);
            Thread t3 = new Thread(transaction3);
            t3.start();



//            transaction3.executeTransactions();




        }
        catch (Exception e)
        {
            try {
                LogsGenerator.getInstance().writeLogs("\n\tRemote Database Connection Failed\n\n");
                e.printStackTrace();
            }
            catch(Exception exception){
                e.printStackTrace();
            }
        }
        finally
        {
//            if (remoteConnection != null)
//            {
//                try
//                {
//                    remoteConnection.close ();
//                    System.out.println ("Database connection terminated");
//                    LogsGenerator.getInstance().writeLogs("\n\tRemote Database Connection Closed\n\n");
//
//                }
//                catch (Exception exception) {
//                    //ignore
//                }
//            }
            try{
                if(LogsGenerator.getInstance()!=null){
                    LogsGenerator.getInstance().writeLogs("\n\n\n\n\t\t\t\t********Program Closed!!!!********\n\n");
                    LogsGenerator.getInstance().closeLogs();
                }
            }
            catch(Exception exception){
                // ignore
            }
        }
    }
    private String scanInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
