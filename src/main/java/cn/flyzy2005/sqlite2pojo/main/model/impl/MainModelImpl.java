package cn.flyzy2005.sqlite2pojo.main.model.impl;

import cn.flyzy2005.sqlite2pojo.TwoTuple;
import cn.flyzy2005.sqlite2pojo.main.model.MainModel;
import cn.flyzy2005.sqlite2pojo.util.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fly on 2017/9/25.
 */
public class MainModelImpl implements MainModel {
    private Connection connection;

    public MainModelImpl() {
        this.connection = JdbcUtils.getConnection();
    }

    public List<String> listTables() throws SQLException {
        List<String> result = new ArrayList<String>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT name FROM sqlite_master\n" +
                    "WHERE type='table'\n" +
                    "ORDER BY name";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(resultSet.getString("name"));
            }
        } finally {
            JdbcUtils.free(statement, resultSet);
        }
        return result;
    }

    public List<TwoTuple<String, String>> listColumns(String tableName) throws SQLException {
        List<TwoTuple<String, String>> result = new ArrayList<TwoTuple<String, String>>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "PRAGMA table_info([" + tableName + "])";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                TwoTuple<String, String> twoTuple = new TwoTuple<String, String>(type, name);
                result.add(twoTuple);
            }
        } finally {
            JdbcUtils.free(statement, resultSet);
        }
        return result;
    }
}
