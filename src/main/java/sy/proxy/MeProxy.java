package sy.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Ocean Lin on 2017/4/30.
 */
public class MeProxy {

    public static String rn = "\r\n";

    public static Object newProxyInstance(ClassLoader classLoader, Class<?>[] interfaces, MeInvocationHandler handler) throws Exception {
        //返回一个代理类实例，存放在内存中

        //生成一个Java类，先用String拼字符串，然后再使用流的方式写入文件或者在控制台输出
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package sy.proxy;").append(rn).append(rn);
        stringBuilder.append("import java.lang.reflect.Method;").append(rn);
        stringBuilder.append("public final class $Proxy0 extends MeProxy implements MeProxyService").append(rn);
        stringBuilder.append("{").append(rn);
        stringBuilder.append("MeInvocationHandler h;").append(rn);
        stringBuilder.append("public $Proxy0(MeInvocationHandler h)").append(rn);
        stringBuilder.append("{").append(rn);
        stringBuilder.append("this.h=h;").append(rn);
        stringBuilder.append("}").append(rn);
        stringBuilder.append(rn).append(createMethods(interfaces));
        stringBuilder.append(rn);

        generateProxyClass(stringBuilder.toString());


        //动态编译
        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, null);
        Iterable javaFileObjects =
                standardFileManager.getJavaFileObjects("D:\\HXQH\\dev\\IDEA\\ssh-lab\\src\\main\\java\\sy\\proxy\\$Proxy0.java");
        systemJavaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects).call();

        //将字节码文件加载到内存中
        MeClassLoader meClassLoader = new MeClassLoader("D:\\HXQH\\dev\\IDEA\\ssh-lab\\src\\main\\java\\sy\\proxy\\");
        Class<?> proxy0 = meClassLoader.findClass("$Proxy0");//返回的就是内存中的字节码对象
        //需要先拿到构造器对ProxyMeInvocationHandler初始化
        Constructor<?> constructor = proxy0.getConstructor(MeInvocationHandler.class);
        return constructor.newInstance(handler);
    }

    private static void  generateProxyClass(String s) {
        File proxyFile = new File("D:\\HXQH\\dev\\IDEA\\ssh-lab\\src\\main\\java\\sy\\proxy\\$Proxy0.java");

        try {
            FileWriter fileWriter = new FileWriter(proxyFile);
            fileWriter.write(s);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("write success!");

    }

    public static String createMethods(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();

        for (Class interf : interfaces) {
            Method[] methods = interf.getMethods();
            for (Method m : methods) {
                sb.append(" public final void ").append(m.getName()).append("(String var1) throws Exception").append(rn);
                sb.append("{").append(rn);
                sb.append("Method m = ").append(interf.getName());
                sb.append(".class.getMethod(").append("\"").append(m.getName()).append("\"").append(",").append("new Class[]{String.class}").append(");").append(rn);
                sb.append("this.h.invoke(this, m, new Object[]{var1});").append(rn);
                sb.append("}").append(rn);
            }

        }
        sb.append("}").append(rn);
        return sb.toString();
    }

}
