/*
 */
package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Datos necesarios para la conexion de la BD
 */
public class ConexionBD {
    public static Connection getConnection()throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url       = "jdbc:mysql://localhost:3306/sistema_bancario";
            String user      = "root";
            String password  = "admincoco";
            con = DriverManager.getConnection(url,user, password);
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new SQLException();
        } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
                throw new ClassNotFoundException();
        }
        return con;
    }
}


