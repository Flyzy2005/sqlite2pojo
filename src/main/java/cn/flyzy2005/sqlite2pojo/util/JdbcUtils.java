package cn.flyzy2005.sqlite2pojo.util;

import java.sql.*;

/**
 * Created by Fly on 2017/9/25.
 */
public class JdbcUtils {
    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Fly\\Desktop\\nameseve.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void free(Statement statement, ResultSet resultSet) throws SQLException {
        if (null == statement)
            statement.close();
        if (null == resultSet)
            resultSet.close();
    }
}
