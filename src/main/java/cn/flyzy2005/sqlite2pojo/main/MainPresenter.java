package cn.flyzy2005.sqlite2pojo.main;

import cn.flyzy2005.sqlite2pojo.TwoTuple;
import cn.flyzy2005.sqlite2pojo.main.model.impl.MainModelImpl;
import cn.flyzy2005.sqlite2pojo.util.CommonUtils;
import cn.flyzy2005.sqlite2pojo.util.LogUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Fly on 2017/9/25.
 */
public class MainPresenter implements MainContract.Presenter {
    private MainView view;
    private MainModelImpl model;

    public MainPresenter(MainView view) {
        this.view = view;
        model = new MainModelImpl();
    }

    public void generatorPojo() {
        try {
            List<String> tableNames = model.listTables();
            if (tableNames.size() < 1) {
                view.error(new Exception("the number of tables is zero or not found the database file"));
                return;
            }
            for (String tableName : tableNames) {
                List<TwoTuple<String, String>> columns = model.listColumns(tableName);
                StringBuilder builder = new StringBuilder();
                for (TwoTuple<String, String> tuple : columns) {
                    switch (tuple.first) {
                        case "TEXT":
                            builder.append("private ").append("String ").append(CommonUtils.underscore2Camel(tuple.second)).append(";").append("\n");
                            break;
                        case "INTEGER":
                            builder.append("private ").append("int ").append(CommonUtils.underscore2Camel(tuple.second)).append(";").append("\n");
                            break;
                        case "BLOB":
                            builder.append("private ").append("byte[] ").append(CommonUtils.underscore2Camel(tuple.second)).append(";").append("\n");
                            break;
                        case "REAL":
                            builder.append("private ").append("double ").append(CommonUtils.underscore2Camel(tuple.second)).append(";").append("\n");
                            break;
                    }
                }
                LogUtils.createLog(tableName, builder.toString());
            }
            view.success();
        } catch (SQLException e) {
            view.error(e);
        }
    }
}
