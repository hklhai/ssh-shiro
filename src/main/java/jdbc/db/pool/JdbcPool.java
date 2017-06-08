package jdbc.db.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by lh on 2017/4/18.
 */
public class JdbcPool implements Pool {

    private static String jdbcDriver = "";
    private static String db = "";
    private static String name = "";
    private static String password = "";
    private static int initConn;
    private static int maxConn;
    private static int increaseConn;

    private Vector<PooledConnection> pooledConnections = new Vector<>();

    public void init() {
        InputStream resourceAsStream = JdbcPool.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcDriver = properties.getProperty("jdbcDriver");
        db = properties.getProperty("db");
        name = properties.getProperty("name");
        password = properties.getProperty("password");
        initConn = Integer.valueOf(properties.getProperty("initConn"));
        maxConn = Integer.valueOf(properties.getProperty("maxConn"));
        increaseConn = Integer.valueOf(properties.getProperty("increaseConn"));

        try {
            Driver newInstance = (Driver) Class.forName(jdbcDriver).newInstance();
            DriverManager.registerDriver(newInstance);

            //创建数据库连接，放入集合中
            createConnection(initConn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public synchronized PooledConnection getConnection() {
        if (pooledConnections.size() <= 0) {
            System.out.print("连接池中无连接");
            throw new RuntimeException("连接池中无连接");
        }
        PooledConnection pooledConnection = getPooledConnection();
        if (null == pooledConnection) {
          //连接池均忙碌 增长
            createConnection(increaseConn);
            pooledConnection = getPooledConnection();
            while(null==pooledConnection)
            {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pooledConnection = getPooledConnection();
            }
            }
        return pooledConnection;
    }

    private PooledConnection getPooledConnection() {
        for (int i = 0; i < pooledConnections.size(); i++) {
            if (!pooledConnections.get(i).isBuABoolean()) {
                Connection trueConn = pooledConnections.get(i).getConnection();

                try {
                    if (trueConn.isValid(0)) {
                        Connection connection = DriverManager.getConnection(db, name, password);
                        pooledConnections.get(i).setConnection(connection);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                pooledConnections.get(i).setBuABoolean(true);
                return pooledConnections.get(i);
            } else {
            }
        }
        return null;
    }

    @Override
    public void createConnection(int count) {
        for (int i = 0; i < count; i++) {
            if (maxConn > 0 && pooledConnections.size() >= maxConn) {
                System.out.print("连接池中数量大于最大值");
                throw new RuntimeException("连接池中数量大于最大值");
            }
            try {
                Connection connection = DriverManager.getConnection(db, name, password);
                PooledConnection pooledConnection = new PooledConnection(connection, false);
                pooledConnections.add(pooledConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
}
