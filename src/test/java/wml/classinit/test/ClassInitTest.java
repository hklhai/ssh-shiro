package wml.classinit.test;

import org.junit.Test;

/**
 * Created by lh on 2017/4/23.
 */
public class ClassInitTest {

    @Test
    public void testInit() {
        new Son();
        System.out.println("=====");
        new Son();
    }

}

class Father {
    public String fStr1 = "father1";
    protected String fStr2 = "father2";
    private String fStr3 = "father3";

    {
        System.out.println("Father common block be called");
    }

    static {
        System.out.println("Father static block be called");
    }

    public Father() {
        System.out.println("Father constructor be called");
    }
}

class Son extends Father {
    public String SStr1 = "Son1";
    protected String SStr2 = "Son2";
    private String SStr3 = "Son3";

    {
        System.out.println("Son common block be called");
    }

    static {
        System.out.println("Son static block be called");
    }

    public Son() {
        System.out.println("Son constructor be called");
    }
}