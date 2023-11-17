package com.helpfinance.infrastructure.contexts;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class helpFinanceContext {
    private static Connection _connection;
    private static Logger logger = LoggerFactory.getLogger(helpFinanceContext.class);

    public static Connection getConnection() {
        if (_connection != null)
            return _connection;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            _connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/pdb", "helpfinance", "123456");
            _connection.setAutoCommit(false);

            if (_connection != null)
                logger.info("Connected successfully to OracleDB with helpfinance user");
                
            return _connection;
        }
        catch (Exception e)
        {
            logger.warn("ERROR WHILE TRYING TO CONNECT TO ORACLE DB. EXCEPTION DETAILS: " + e.getMessage());
            return null;
        }
    }
}
