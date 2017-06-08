package jdbc.db.pool.test;

import cn.edu.ncut.model.User;
import cn.edu.ncut.service.UserService;
import jdbc.db.pool.DBManager;
import jdbc.db.pool.JdbcPool;
import jdbc.db.pool.PooledConnection;
import org.apache.poi.ss.formula.functions.T;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class JdbcTest {
    JdbcPool pool = DBManager.getInstance();
    PooledConnection connection = null;

    @Before
    public void before() {
        pool.init();

    }

    @Test
    public void testJdbc() throws Exception {
        connection = pool.getConnection();
        ResultSet resultSet = connection.queryFromSQL("select * from tb_user");
       // System.out.print("线程名：" + Thread.class.getName());
        if (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
        resultSet.close();
        connection.close();
    }

    @Test
    public void threadTest() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        testJdbc();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }

}
