package tr.com.canyazilim.core;

import java.sql.*;
import tr.com.canyazilim.interfaces.CoreInterfaces;

public class ObjectHelper extends CoreFields implements CoreInterfaces {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
        } catch (SQLException e) {
            System.out.println("Bağlantı Başarısız...");
        }
        return connection;
    }

}
