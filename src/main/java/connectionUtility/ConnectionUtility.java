package connectionUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

    private static String url="jdbc:mysql://localhost:3306/jspservletcrud?useSSL=false";
    private static String user_name="root";
    private static String password="root";

    public static Connection getConnection(){

        Connection connection=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(url,user_name,password);

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
