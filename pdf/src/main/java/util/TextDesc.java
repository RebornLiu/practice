package util;

import com.itextpdf.text.BaseColor;

public class TextDesc {
    private String text;

    private BaseColor bgColor;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BaseColor getBgColor() {
        return bgColor;
    }

    public void setBgColor(BaseColor bgColor) {
        this.bgColor = bgColor;
    }
}
