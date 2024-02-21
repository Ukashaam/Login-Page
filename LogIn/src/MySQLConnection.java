import java.sql.*;
import javax.swing.JOptionPane;

public class MySQLConnection {
    public final String DATABASE = "login";
    public final String USER = "root";
    public final String PASS ="Majeed12$";
    public final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public Connection connection = null;

    public Statement connect(){
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DATABASE, USER, PASS);
            return connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR at MySQLConnection: "+ex.getMessage());
        }
        return null;
    }

}