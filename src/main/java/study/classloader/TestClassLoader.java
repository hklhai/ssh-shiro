package study.classloader;

/**
 * Created by lh on 2017/4/19.
 */
public class TestClassLoader {
    public static void main(String[] args) {
        String name = TestClassLoader.class.getClassLoader().getClass().getName();
        System.out.println(name);//sun.misc.Launcher$AppClassLoader
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        while (classLoader != null) {
            if (null != classLoader.getParent()) {
                System.out.println(classLoader.getParent().getClass().getName());
                classLoader = classLoader.getParent();
            }else
            {
                classLoader = classLoader.getParent();
                break;
            }
        }
        System.out.println(classLoader);

    }

}
