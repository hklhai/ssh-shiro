package cn.edu.ncut.test;

import org.junit.Test;
import sy.proxy.*;

/**
 * Created by Ocean Lin on 2017/4/30.
 */
public class MeProxyTest {


    @Test
    public void testCreateMeyhodS() {
        MeProxy meProxy = new MeProxy();
        String meyhodS = meProxy.createMethods(new Class<?>[]{MeProxyService.class});
        System.out.println(meyhodS);
    }

    //    @Test
    //    public void testNewProxyInstance() throws Exception {
    //        MeProxy meProxy = new MeProxy();
    //        ClassLoader classLoader = MeProxyTest.class.getClassLoader();
    //        //使用继承InvocationHandler的方法，将实现类传入
    //        ProxyMeInvocationHandler proxyInvocationHandler = new ProxyMeInvocationHandler(new MeProxyServiceImpl());
    //
    //        MeProxyService meProxyService = (MeProxyService)meProxy.newProxyInstance(classLoader, new Class<?>[]{MeProxyService.class}, proxyInvocationHandler);
    //        meProxyService.save(null);
    //    }
}
