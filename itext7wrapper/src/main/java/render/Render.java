package render;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.layout.Document;
import field.FieldInfo;
import field.PageInfo;

/**
 * 域填充接口
 * */
public interface Render {
    void render(
            /**文本对象*/
            Document document,
            /**单页填充信息*/
            PageInfo pageInfo,
            /**填充的域信息*/
            FieldInfo fieldInfo,
            /**表单对象*/
            PdfAcroForm form);
}
