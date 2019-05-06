import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import field.*;
import font.DefaultFont;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import render.RenderRegister;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PDFUtil {

    /**
     * 适用于一个模板生成一个文件的场景
     * */
    public static void renderPDF(List<PageInfo> pageInfoList, String templatePath, String destPath) throws IOException {
        try (PdfWriter writer = new PdfWriter(destPath);
             PdfReader reader = new PdfReader(templatePath);
             PdfDocument pdfDocument = new PdfDocument(reader, writer);
             Document document = new Document(pdfDocument)) {

            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, false);

            pageInfoList.forEach(page -> {
                List<FieldInfo> fieldInfos = page.getFieldInfos();
                if (fieldInfos == null || fieldInfos.size() == 0) {
                    return;
                }

                fieldInfos.forEach(field ->
                        RenderRegister.getRender(field.getFieldType()).render(document, page, field, form));
            });

            form.flattenFields();
        }

    }


    /**
     * 适用于根据单页模板 生成多页的场景
     * */
    public static void renderRepeatPDF(List<PageInfo> pageInfoList, String templatePath, String destPath) throws IOException {
        try (PdfWriter writer = new PdfWriter(destPath);
             PdfDocument finalDoc = new PdfDocument(writer)) {

            for (PageInfo pageInfo : pageInfoList) {
                ByteArrayOutputStream outputStream = getPageContent(pageInfo, templatePath);
                if (outputStream == null) {
                    continue;
                }

                copyContent(outputStream, finalDoc);
            }
        }
    }


    /**
     * 获取单页渲染之后的字节流
     * */
    private static ByteArrayOutputStream getPageContent(PageInfo pageInfo, String templatePath) throws IOException {
        try (InputStream templateIs = new FileInputStream(templatePath);
             PdfReader templateReader = new PdfReader(templateIs);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PdfWriter pageWriter = new PdfWriter(outputStream);
             PdfDocument pagePdfDocument = new PdfDocument(templateReader, pageWriter);
             Document pageDocument = new Document(pagePdfDocument)) {

            PdfAcroForm form = PdfAcroForm.getAcroForm(pagePdfDocument, false);

            List<FieldInfo> fieldInfos = pageInfo.getFieldInfos();
            if (fieldInfos == null || fieldInfos.size() == 0) {
                return null;
            }

            for (FieldInfo fieldInfo : fieldInfos) {
                RenderRegister.getRender(fieldInfo.getFieldType()).render(pageDocument, pageInfo, fieldInfo, form);
            }

            form.flattenFields();
            return outputStream;
        }
    }


    /**
     * 将单页信息拷贝到最终模板
     * */
    private static void copyContent(ByteArrayOutputStream outputStream, PdfDocument file) throws IOException {
        try (PdfReader reader = new PdfReader(new ByteArrayInputStream(outputStream.toByteArray()));
             PdfDocument document = new PdfDocument(reader)) {
            document.copyPagesTo(1, document.getNumberOfPages(), file);
        }
    }


    public static void main(String[] args) throws IOException {
        String template = "C:\\Users\\liuweiliang1\\Desktop\\template.pdf";
        String save = "C:\\Users\\liuweiliang1\\Desktop\\result.pdf";

        PageInfo page1 = new PageInfo();
        page1.setPageNumber(1);

        TextField textField = new TextField();
        textField.setKey("test1");
        textField.setValue("这是测试1的text1");
        textField.setFont(DefaultFont.MSYH.getKey());
        textField.setSize(30);
        textField.setColor("#00008b");

        ImageField imageField = new ImageField();
        imageField.setKey("img1");
        imageField.setType(ImageResourceType.CUSTOM);
        imageField.setValue("1234567");
        imageField.setConverter((value) -> {
            Code128Bean code128Bean = new Code128Bean();
            ByteArrayOutputStream ous = new ByteArrayOutputStream();
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, "image/png", 200,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            code128Bean.generateBarcode(canvas, value);
            try {
                canvas.finish();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ous.toByteArray();
        });
        List<FieldInfo> fieldInfos = new ArrayList<>(2);
        fieldInfos.add(textField);
        fieldInfos.add(imageField);
        page1.setFieldInfos(fieldInfos);


        PageInfo page2 = new PageInfo();
        page2.setPageNumber(2);
        TextField textField2 = new TextField();
        textField2.setKey("test2");
        textField2.setValue("这个是测试2的text2");
        List<FieldInfo> fieldInfos1 = new ArrayList<>(2);
        fieldInfos1.add(textField2);
        page2.setFieldInfos(fieldInfos1);

        List<PageInfo> pageInfos = new ArrayList<>(2);
        pageInfos.add(page1);
        pageInfos.add(page2);
        renderRepeatPDF(pageInfos, template, save);

    }
}
