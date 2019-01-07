import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TestPicModify {

    //将文本替换为图片 图片位置取文本框的位置 @{newImage.png}
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\ppttest\\imagetext.pptx");
        XMLSlideShow ppt = new XMLSlideShow(fileInputStream);

        List<XSLFSlide> slideList = ppt.getSlides();
        XSLFSlide picSlide = slideList.get(0); //picSlide.getSlideShow()
        XSLFTextShape oldShape = (XSLFTextShape)picSlide.getShapes().get(0);
        Rectangle2D anchor = oldShape.getAnchor();
        List<XSLFTextParagraph> textParagraphs = oldShape.getTextParagraphs();
        String text = textParagraphs.get(0).getText();
        String path = text.substring(2, text.length() - 1);

        XSLFPictureData pd = ppt.addPicture(IOUtils.toByteArray(new FileInputStream("E:\\ppttest\\" + path)), PictureData.PictureType.PNG);
        XSLFPictureShape n_shape = picSlide.createPicture(pd);
        n_shape.setAnchor(anchor);
        picSlide.removeShape(oldShape);

        String outputFilePath = "E:\\ppttest\\imageppt.pptx";
        FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
        ppt.write(fileOutputStream);
    }
}
