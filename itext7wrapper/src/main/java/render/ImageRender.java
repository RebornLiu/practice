package render;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import exception.RenderException;
import field.FieldInfo;
import field.ImageField;
import field.ImageResourceType;
import field.PageInfo;

import java.util.Optional;


/**
 * 图片域填充 图片填充到文本域，图片大小自适应文本域，图片以文本域左上角坐标为准
 * */
public class ImageRender implements Render {
    @Override
    public void render(Document document, PageInfo pageInfo, FieldInfo fieldInfo, PdfAcroForm form) {
        ImageField imageField = (ImageField)fieldInfo;
        Optional.ofNullable(form.getField(imageField.getKey()))
                .ifPresent(field -> {
                    Image image = decodeImage(imageField);
                    resizeImage(image, field);
                    image.setPageNumber(pageInfo.getPageNumber());
                    document.add(image);
                });
    }


    /**
     * 自适应图片
     * */
    private static void resizeImage(Image image, PdfFormField field) {
        Rectangle rectangle = field.getWidgets().get(0).getRectangle().toRectangle();
        if (image.getImageWidth() < rectangle.getWidth() && image.getImageHeight() < rectangle.getHeight()) {
            image.setFixedPosition(rectangle.getLeft(), rectangle.getTop() - image.getImageHeight());
            image.setWidth(image.getImageWidth());
            image.setHeight(image.getImageHeight());
            return;
        }

        float resizeWidth = image.getImageWidth();
        float resizeHeight = image.getImageHeight();
        float ratio = image.getImageHeight() / image.getImageWidth();
        if (resizeWidth > rectangle.getWidth()) {
            resizeWidth = rectangle.getWidth();
            resizeHeight = rectangle.getWidth() * ratio;
        }

        if (resizeHeight > rectangle.getHeight()) {
            resizeHeight = rectangle.getHeight();
            resizeWidth = rectangle.getHeight() / ratio;
        }

        image.setFixedPosition(rectangle.getLeft(), rectangle.getTop() - resizeHeight);
        image.setWidth(resizeWidth);
        image.setHeight(resizeHeight);
    }


    /**
     * 解码图片
     * */
    private static Image decodeImage(ImageField imageField) {
        try {
            if (ImageResourceType.URL == imageField.getType()) {
                return new Image(ImageDataFactory.create(imageField.getValue()));
            }

            if (ImageResourceType.LOCAL == imageField.getType()) {
                return new Image(ImageDataFactory.create(imageField.getValue()));
            }
        }
        catch (Exception e) {
            throw new RenderException("图片解析失败", e);
        }

        if (imageField.getType() == ImageResourceType.CUSTOM) {
            if (imageField.getConverter() == null) {
                throw new RenderException("自定义图片类型需要设置converter");
            }

            return new Image(ImageDataFactory.create(imageField.getConverter().apply(imageField.getValue())));
        }

        throw new RenderException("解析图片失败");
    }
}
