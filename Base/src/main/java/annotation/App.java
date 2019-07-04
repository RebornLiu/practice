package annotation;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Method;

/**
 * 子类重写父类方法后 是不继承annotation的 包含cglib生成的子类
 * 当子类没有重写父类方法，该方法还是可以获取annotation
 * */
public class App {
    public static void main(String[] args) throws NoSuchMethodException {

        /*Method method = Person.class.getDeclaredMethod("echo");
        System.out.println(method.isAnnotationPresent(Demo.class));
        Demo demo = method.getAnnotation(Demo.class);
        System.out.println(demo.value());

        Method[] ms = Student.class.getMethods();
        for (Method m : ms) {
            if (m.getName().equals("echo")) {
                System.out.println(m.isAnnotationPresent(Demo.class));
            }
        }*/

        cglib();

    }

    public static void cglib() throws NoSuchMethodException {
        CglibProxy proxy = new CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(proxy);

        Person p = (Person) enhancer.create();
        Method method = p.getClass().getDeclaredMethod("echo");
        System.out.println(method.isAnnotationPresent(Demo.class));
    }
}
