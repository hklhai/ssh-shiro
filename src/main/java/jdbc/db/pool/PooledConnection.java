package jdbc.db.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by lh on 2017/4/18.
 */
public class PooledConnection {
    private Connection connection = null;

    private boolean isBuABoolean = false;

    public PooledConnection(Connection connection, boolean isBuABoolean) {
        this.connection = connection;
        this.isBuABoolean = isBuABoolean;
    }

    //查询结果集
    public ResultSet queryFromSQL(String sql) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //更新语句
    public int updaeFromSQL(String sql) {
        Statement statement = null;
        int resultSet = -1;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isBuABoolean() {
        return isBuABoolean;
    }

    public void setBuABoolean(boolean buABoolean) {
        isBuABoolean = buABoolean;
    }

    public void close() {
        isBuABoolean = false;
    }

}
