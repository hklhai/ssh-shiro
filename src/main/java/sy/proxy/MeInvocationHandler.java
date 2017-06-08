package sy.proxy;

import java.lang.reflect.Method;

/**
 * Created by Ocean Lin on 2017/4/30.
 */
public interface MeInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Exception;
}
