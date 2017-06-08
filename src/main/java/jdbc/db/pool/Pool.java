package jdbc.db.pool;

import java.sql.Connection;

/**
 * Created by lh on 2017/4/18.
 */
public interface Pool {
    public PooledConnection getConnection();

    public void createConnection(int ocunt);
}
