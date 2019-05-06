package render;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.layout.Document;
import field.FieldInfo;
import field.PageInfo;
import field.TextField;
import font.FontRegister;

import java.util.Optional;

/**
 * 文本域填充
 * 可以设置字体 非默认字体 需要先注册
 * 颜色 6字符 16进制形式 #00008b
 * 字体大小
 * */
public class TextRender implements Render {

    @Override
    public void render(Document document, PageInfo pageInfo, FieldInfo fieldInfo, PdfAcroForm form) {
        TextField textField = (TextField)fieldInfo;
        Optional.ofNullable(form.getField(textField.getKey()))
                .ifPresent((field -> {
                    field.setValue(textField.getValue());
                    if (FontRegister.getFont(textField.getFont()) != null) {
                        field.setFont(FontRegister.getFont(textField.getFont()));
                    }
                    if (textField.getSize() != null) {
                        field.setFontSize(textField.getSize());
                    }
                    if (textField.getColor() != null) {
                        field.setColor(convertHexToRgb(textField.getColor()));
                    }
                }));
    }

    private DeviceRgb convertHexToRgb(String hex) {
        Integer r = Integer.valueOf(hex.substring(1, 3), 16);
        Integer g = Integer.valueOf(hex.substring(3, 5), 16);
        Integer b = Integer.valueOf(hex.substring(5), 16);

        return new DeviceRgb(r, g, b);
    }
}
