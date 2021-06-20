import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RemoteConnection {

    public Connection getRemoteConnection(String url, String user, String password) throws SQLException, ClassNotFoundException, IOException {
        Class.forName ("com.mysql.jdbc.Driver");
        Connection remoteConnection = DriverManager.getConnection(url, user, password);
        //System.out.println ("Database connection established");
        LogsGenerator.getInstance().writeLogs("\n\tRemote Database Connection successful\n\n");

        return remoteConnection;
    }
}
