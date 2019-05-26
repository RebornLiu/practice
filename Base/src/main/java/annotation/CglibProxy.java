package annotation;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object target, Method method, Object[] parameters, MethodProxy proxy) throws Throwable {
        return proxy.invokeSuper(target, parameters);
    }
}
