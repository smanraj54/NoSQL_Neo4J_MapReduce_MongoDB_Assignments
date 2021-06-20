import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionQueries {
    private String cityName = "";
    private String transactionName = "";
    private Connection remoteConnection = null;
    private int customer_zip_code_prefix = 0;


    public TransactionQueries(String transactionName, Connection remoteConnection, int customer_zip_code_prefix ) throws IOException, SQLException {
        LogsGenerator.getInstance().writeLogs("\n\tTransaction: " + transactionName + " Received!");
        this.cityName = transactionName+" City";
        this.remoteConnection = remoteConnection;
        this.customer_zip_code_prefix = customer_zip_code_prefix;
        this.transactionName = transactionName;


    }


    public void run() {
        try {
            executeTransactions();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void executeTransactions() throws SQLException, IOException {

        LogsGenerator.getInstance().writeLogs("\n\tTransaction: " + transactionName + " Has Started! ");
        //System.out.println("\n\n\n\t\t\tWait is Over!!!\n\n\n");
        Statement statement = remoteConnection.createStatement();

        readDataQuery(statement);
        updateDataQuery(statement);
        commit(statement);

    }

    private void readDataQuery(Statement statement) throws SQLException, IOException {
        String readQuery = "Select * from customers where customer_zip_code_prefix =  "+ customer_zip_code_prefix;
        LogsGenerator.getInstance().writeLogs("\n\n\tTransaction: " + transactionName + " Is Reading from table!");
        LogsGenerator.getInstance().writeLogs("\n\tRead Query is: " + readQuery);
        ResultSet resultSet= statement.executeQuery(readQuery);
        LogsGenerator.getInstance().writeLogs("\n\tData Fetched from Transaction: " + transactionName + " given below: ");

        while(resultSet.next()){

            LogsGenerator.getInstance().writeLogs("\n\t\t\tcustomer ID      =       "+resultSet.getString("customer_id") +
                    "\n\t\t\tcustomer_unique_id        =       "+ resultSet.getString("customer_unique_id") +
                    "\n\t\t\tcustomer_zip_code_prefix      =       "+resultSet.getInt("customer_zip_code_prefix") +
                    "\n\t\t\tcustomer City      =       "+resultSet.getString("customer_city") +
                    "\n\t\t\tcustomer State      =       "+resultSet.getString("customer_state"));

        }
    }

    private void updateDataQuery(Statement statement) throws SQLException, IOException {
        String writeQuery = "UPDATE customers SET customer_city = '"+cityName+"' WHERE customer_zip_code_prefix =  "+ customer_zip_code_prefix;
        LogsGenerator.getInstance().writeLogs("\n\n\tTransaction: " + transactionName + " Is Updating the table! ");
        LogsGenerator.getInstance().writeLogs("\n\tRead Query is: " + writeQuery);
        statement.execute(writeQuery);
        LogsGenerator.getInstance().writeLogs("\n\t\tTransaction: " + transactionName + " Updated The Table Value! ");
    }

    private void commit(Statement statement) throws SQLException, IOException {
        LogsGenerator.getInstance().writeLogs("\n\n\tTransaction: " + transactionName + " is Committing! ");
        statement.execute("commit");
        LogsGenerator.getInstance().writeLogs("\n\tTransaction: " + transactionName + " Is Committed! ");
    }

    private void rollback(Statement statement) throws SQLException, IOException {
        LogsGenerator.getInstance().writeLogs("\n\n\tTransaction: " + transactionName + " is Rolling back! ");
        statement.execute("rollback");
        LogsGenerator.getInstance().writeLogs("\n\tTransaction: " + transactionName + " Is Rolled Back! ");

    }


}
