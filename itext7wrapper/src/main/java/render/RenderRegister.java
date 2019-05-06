package render;

import field.FieldType;

import java.util.HashMap;
import java.util.Map;

/**
 * 域填充管理器 注册基础渲染类 对外提供接口 可以注册新的域填充类
 * */
public class RenderRegister {

    private static Map<Integer, Render> renders = new HashMap<>();

    static {
        renders.put(FieldType.TEXT.getValue(), new TextRender());
        renders.put(FieldType.IMAGE.getValue(), new ImageRender());
    }


    /**
     * 用于扩展 可以自己注册对应的解析器
     * */
    public static void regist(int type, Render render) {
        renders.put(type, render);
    }

    public static Render getRender(Integer type) {
        return renders.get(type);
    }
}
