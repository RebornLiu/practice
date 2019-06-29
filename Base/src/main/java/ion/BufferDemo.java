package ion;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BufferDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Map<String, String> map = new HashMap<>(3);
        map.put("1", "1");
        Field f = HashMap.class.getDeclaredField("table");
        f.setAccessible(true);
        Object[] o = (Object[]) f.get(map);
        System.out.println(o.length);
    }
}
