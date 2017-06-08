package cn.edu.ncut.test;

import cn.edu.ncut.service.ProxyInvocationHandler;
import cn.edu.ncut.service.ProxyService;
import cn.edu.ncut.service.ProxyServiceImpl;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * Created by Ocean Lin on 2017/4/29.
 */
public class ProxyTest {

    @Test
    public void test() {

        ProxyService proxyServiceImpl = new ProxyServiceImpl();

        ClassLoader classLoader = ProxyTest.class.getClassLoader();
        //使用继承InvocationHandler的方法，将实现类传入
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler(new ProxyServiceImpl());
        ProxyService proxyService = (ProxyService) Proxy.newProxyInstance(classLoader, new Class<?>[]{ProxyService.class}, proxyInvocationHandler);


        System.out.println("new =========");
        proxyServiceImpl.save("nihao");
        System.out.println("new =========");
        proxyService.save("haha");
        generateProxyClass();
    }


    private void generateProxyClass()
    {
        byte[] proxy0s = ProxyGenerator.generateProxyClass("$Proxy0", new Class<?>[]{ProxyService.class});
        try {
            //FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.java");
            //fileOutputStream.write(proxy0s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("write success!");

    }


}
