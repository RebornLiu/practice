package font;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import exception.RenderException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * 字体管理类 负责字体的注册和获取
 * */
public class FontRegister {
    private static Map<String, PdfFont> fontTable = new HashMap<>();

    /*
     * 默认微软雅黑的注册
     * */
    static {
        try {
            byte[] msyh = IOUtils.toByteArray(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("msyh.ttf")));
            fontTable.put(DefaultFont.MSYH.getKey(), PdfFontFactory.createFont(msyh, true));
        } catch (IOException e) {
            throw new RenderException("加载字体失败");
        }
    }


    /**
     *
     * 注册自定义的字体
     * @param key 字体的key
     * @param path 字体的路径*/
    public static void regist(String key, String path) {
        try {
            fontTable.put(key, PdfFontFactory.createFont(path));
        }
        catch (Exception e) {
            throw new RenderException("注册字体失败");
        }
    }


    /**
     * 注册自定义的字体
     * @param key 字体的key
     * @param bytes 字体文件的字节数组
     * */
    public static void regist(String key, byte[] bytes) {
        try {
            fontTable.put(key, PdfFontFactory.createFont(bytes, true));
        }
        catch (Exception e) {
            throw new RenderException("注册字体失败");
        }
    }


    /**
     * 获取字体
     * */
    public static PdfFont getFont(String key) {
        return fontTable.get(key);
    }
}
