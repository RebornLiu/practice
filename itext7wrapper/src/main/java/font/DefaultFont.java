package font;

/**
 * 工具包提供的默认字体
 * */
public enum  DefaultFont {
    MSYH("msyh", "微软雅黑");

    private String key;
    private String desc;

    DefaultFont(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
