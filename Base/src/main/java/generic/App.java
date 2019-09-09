package generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author by liuweiliang1
 * @date 2019/9/9 14:15
 * @description
 */
public class App {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field field = Demo.class.getDeclaredField("students");

        Type type = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];

        Class clz = (Class)type;
        Student s = (Student)clz.newInstance();
        s.setAge(11);
        System.out.println(s);
    }
}
