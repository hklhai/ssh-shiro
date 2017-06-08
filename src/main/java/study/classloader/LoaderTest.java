package study.classloader;

/**
 * Created by lh on 2017/4/19.
 */
public class LoaderTest {
    public static void main(String[] args) {
        ClassLoader loader = LoaderTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.getClass().getName());
            loader = loader.getParent();
        }
        System.out.println(loader);
    }
}
