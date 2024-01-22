package electricity.billing.system;

import java.sql.Statement;
// import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

public class databaseConnection {
    
    Connection connection;
    Statement statement;
    databaseConnection(){
        try {
            System.out.println("Starting mysql connection");
            // Class.forName()
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Bill","root","root");
            statement = connection.createStatement();
            System.out.println("Started mysql connection");
            // statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}