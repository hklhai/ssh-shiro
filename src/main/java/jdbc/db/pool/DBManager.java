package jdbc.db.pool;

/**
 * Created by lh on 2017/4/19.
 */
public class DBManager {
    //采用内部类实现的单例模式，加载内部类时总是线程互斥的
    private static class createPool {
        private static JdbcPool pool = new JdbcPool();
    }
    public static JdbcPool getInstance()
    {
        return createPool.pool;
    }

}
