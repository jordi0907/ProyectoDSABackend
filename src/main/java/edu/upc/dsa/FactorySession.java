package edu.upc.dsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactorySession {
    public static Session openSession() {


        Connection conn = getConnection();

        Session session = new SessionImpl(conn);

        return session;
    }



    private static Connection getConnection() {
        Connection conn = null;

        try {

            //conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/covid?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=covid19");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/covid?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=admin&password=covid19");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
