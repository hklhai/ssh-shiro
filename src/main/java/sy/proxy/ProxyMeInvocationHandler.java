package sy.proxy;

import cn.edu.ncut.service.ProxyService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lh on 2017/4/28.
 */
public class ProxyMeInvocationHandler implements MeInvocationHandler {

    private MeProxyService proxyService;

    public ProxyMeInvocationHandler(MeProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        before();

        //业务方法
        method.invoke(proxyService, args);

        after();
        return null;
    }


    private void before() {
        System.out.println("Method Before Call");
    }

    private void after() {
        System.out.println("Method After Call");
    }
}
