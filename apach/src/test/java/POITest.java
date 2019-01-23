import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class POITest {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\ppttest\\text.pptx");
        XMLSlideShow ppt = new XMLSlideShow(fileInputStream);

        Map<String, String> textMap = new HashMap<>();

        textMap.put("text1", "新中关购物中心");
        textMap.put("text2", "周边分析");
        textMap.put("image1", "E:\\ppttest\\page3_img.jpg");
        textMap.put("image2", "E:\\ppttest\\page4_img.jpg");

        List<XSLFSlide> slides = ppt.getSlides();
        for (XSLFSlide slide : slides) {
            List<XSLFShape> shapes = slide.getShapes();
            Map<String, List<XSLFTextShape>> afterGroup = shapes.stream()
                    .filter(shape -> shape instanceof XSLFTextShape)
                    .map(shape -> (XSLFTextShape)shape)
                    .collect(Collectors.groupingBy(shape -> {
                        boolean img = shape.getText().trim().startsWith("@{");
                        if (img) {
                            return "img";
                        }
                        else {
                            return "text";
                        }
                    }));

            List<XSLFTextShape> textShapes = afterGroup.get("text");
            List<XSLFTextShape> imgShapes = afterGroup.get("img");

            Optional.ofNullable(textShapes)
                    .orElseGet(ArrayList::new)
                    .forEach(shape -> textPlaceholder(shape, textMap));

            Optional.ofNullable(imgShapes)
                    .orElseGet(ArrayList::new)
                    .forEach(shape -> imagePlacehoder(textMap, shape, slide));

            Iterator<XSLFTextShape> iterator = imgShapes.iterator();
            while (iterator.hasNext()) {
                XSLFTextShape shape = iterator.next();
                slide.removeShape(shape);
            }
        }

        String outputFilePath = "E:\\ppttest\\result.pptx";
        FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
        ppt.write(fileOutputStream);
    }

    public static void imagePlacehoder(Map<String, String> textMap, XSLFTextShape shape, XSLFSlide picSlide) {
        String afterTrim = shape.getText().trim();
        String key = afterTrim.substring(2, afterTrim.length() - 1);
        String imgeSrc = textMap.get(key);
        if (imgeSrc == null) {
            return;
        }

        Rectangle2D archor = shape.getAnchor();
        XMLSlideShow ppt = picSlide.getSlideShow();
        XSLFPictureData pd = null;
        try {
            pd = ppt.addPicture(IOUtils.toByteArray(new FileInputStream(imgeSrc)), PictureData.PictureType.JPEG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSLFPictureShape n_shape = picSlide.createPicture(pd);
        n_shape.setAnchor(archor);
    }

    public static void textPlaceholder(XSLFTextShape shape, Map<String, String> textMap) {
        List<XSLFTextParagraph> textParagraphList = shape.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphList) {
            //正则表达式匹配${}标识符的text
            String text = textParagraph.getText();
            textBox(textMap, textParagraph, text);
        }
    }

    private static void textBox(Map<String, String> textMap, XSLFTextParagraph textParagraph, String text) {
        String regex = "\\$\\{.*?\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> keys = new ArrayList<>();
        while (matcher.find()) {
            keys.add(matcher.group());
        }
        for (String key : keys) {
            String textKey = key.substring(2, key.length() - 1);
            text = text.replace(key, textMap.get(textKey) == null ? " " : textMap.get(textKey));
        }

        List<XSLFTextRun> textRuns = textParagraph.getTextRuns();
        for (XSLFTextRun textRun : textRuns) {
            textRun.setText("");
        }
        if (textRuns.size() > 0) {
            textRuns.get(0).setText(text);
        }
    }

}
