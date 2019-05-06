package field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 表单域基础类
 * */
@Setter
@Getter
@ToString
public class FieldInfo {

    public FieldInfo(Integer fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * 表单类型
     * @see FieldType
     * */
    private Integer fieldType;

    /**
     * 表单域对应的key
     * */
    private String key;
}
