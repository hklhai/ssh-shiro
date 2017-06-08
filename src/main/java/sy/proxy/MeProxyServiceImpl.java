package sy.proxy;

/**
 * Created by lh on 2017/4/28.
 */

public class MeProxyServiceImpl implements MeProxyService {
    @Override
    public void save(String param) throws Exception {
        System.out.println("Save Method");
    }

    @Override
    public void update(String param) throws Exception {
        System.out.println("Update Method");
    }
}
