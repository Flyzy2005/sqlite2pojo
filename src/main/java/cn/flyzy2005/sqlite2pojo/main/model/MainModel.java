package cn.flyzy2005.sqlite2pojo.main.model;

import cn.flyzy2005.sqlite2pojo.TwoTuple;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Fly on 2017/9/25.
 */
public interface MainModel {
    List<String> listTables() throws SQLException;

    /** list name and type of all columns
     * @param tableName tableName
     * @return {@link TwoTuple}first:type;second:columnName
     * @throws SQLException
     */
    List<TwoTuple<String, String>> listColumns(String tableName) throws SQLException;
}
