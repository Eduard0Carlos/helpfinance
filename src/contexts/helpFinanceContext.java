package contexts;

import java.sql.Connection;
import java.sql.DriverManager;

public class helpFinanceContext {
    private static Connection _connection;

    public static Connection getConnection()
    {
        if (_connection != null)
            return _connection;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            _connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "helpfinance", "123456");

            if (_connection != null)
                System.out.println("Conectado ao banco com sucesso!");

            return _connection;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
