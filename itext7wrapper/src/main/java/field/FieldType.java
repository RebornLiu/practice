package field;

/**
 * 表单域类型
 * */
public enum FieldType {
    TEXT(1, "文本"),
    IMAGE(2, "图片"),
    TABLE(3, "表格")
    ;

    private Integer value;
    private String desc;

    FieldType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
