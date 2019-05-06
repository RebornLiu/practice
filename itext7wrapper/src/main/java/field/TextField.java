package field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文本域
 * */
@Setter
@Getter
@ToString
public class TextField extends FieldInfo {

    public TextField() {
        super(FieldType.TEXT.getValue());
    }

    /**
     * 文本内容
     * */
    private String value;

    /**
     * 字体
     * @see font.FontRegister
     * */
    private String font;

    /**
     * 文字大小
     *
     * */
    private Integer size;

    /**
     * 字体颜色 16进制
     * 例如 #00008b
     * */
    private String color;
}
