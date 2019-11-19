package loader;

import java.lang.reflect.Field;
import java.security.ProtectionDomain;

/**
 * @author by liuweiliang1
 * @date 2019/9/9 15:17
 * @description
 */
public class LoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        System.out.println(LoaderDemo.classWhereFrom("com.alibaba.fastjson.JSON"));
    }


    //查看类 是从哪个jar中加载的
    public static String classWhereFrom(String clz) throws ClassNotFoundException {
        ProtectionDomain domain = Class.forName(clz).getProtectionDomain();
        return domain.getCodeSource().getLocation().toString();
    }

    //查看classloader 加载的所有类
    public static void all(ClassLoader classLoader) throws NoSuchFieldException, IllegalAccessException {
        Field field = ClassLoader.class.getDeclaredField("classes");
        field.setAccessible(true);
        Object o = field.get(classLoader);
    }
}
