package field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;

/**
 * 图片域
 * */
@Getter
@Setter
@ToString
public class ImageField extends FieldInfo {
    public ImageField() {
        super(FieldType.IMAGE.getValue());
    }

    /**
     * 图片类型
     * @see ImageResourceType
     * */
    private ImageResourceType type;

    /**
     * 不同的type有不同的含义
     * 图片信息 url 或者 本地路径
     * */
    private String value;

    /**
     * type未custom时，自定义图片资源处理器 将value字段转换成byte[]
     * */
    private Function<String, byte[]> converter;
}
