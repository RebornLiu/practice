package util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PdfRender {
    public static void generateFull(String templatePath,
                                    String destPath,
                                    List<PageContent> pageContents) throws IOException, DocumentException {

        int page = pageContents.size();
        FileOutputStream fos = new FileOutputStream(destPath);// 输出流
        Document doc = new Document();
        PdfCopy copy = new PdfCopy(doc, fos);
        doc.open();
        PdfImportedPage impPage = null;
        for (int i = 0; i < page; i++) {
            PdfReader reader = new PdfReader(templatePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, bos);

            if (pageContents.get(i).getTextes() != null || pageContents.get(i).getImages() != null) {
                BaseFont bf = BaseFont.createFont("D:\\git3333\\practice\\pdf\\src\\main\\java\\font\\simhei.ttf",
                        BaseFont.IDENTITY_H,
                        BaseFont.NOT_EMBEDDED);
                AcroFields form = stamper.getAcroFields();
                //form.removeXfa();
                form.addSubstitutionFont(bf);
                pageContents.get(i).getTextes().forEach((k, v) -> {
                    try {
                        //必须先设置setFieldProperty 否则属性设置无效
                        form.setFieldProperty(k, "bgcolor", BaseColor.BLUE, null);
                        form.setField(k, v);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                });

                pageContents.get(i).getImages().forEach((k, v) -> {
                    int pageNo = form.getFieldPositions(k).get(0).page;
                    Rectangle signRect = form.getFieldPositions(k).get(0).position;
                    Image image = null;
                    try {
                        image = Image.getInstance(v);
                    } catch (BadElementException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    PdfContentByte under = stamper.getOverContent(pageNo);
                    //image.scaleToFit(signRect);//长宽自适应 以图片能放入文本域为目标且最小的边（长或者高）一致，也就是大图会成比例缩小，小图会成比例放大
                    //image.scaleAbsoluteWidth(); 适应固定的宽度
                    //image.scaleAbsoluteHeight();适应固定的高度
                 /*   float documentWidth =
                    float documentHeight =*/
                    image.scaleAbsolute(20, 100);//重新设置宽高
                    image.setAbsolutePosition(signRect.getLeft(), signRect.getBottom());//左下角 pdf的坐标系是左下角开始
                    try {
                        under.addImage(image);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                });
            }


            stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.close();//一定要关闭

            impPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), pageContents.get(i).getPageNo());
            copy.addPage(impPage);
        }

        copy.close();


    }


    /**
     * 获取数据信息
     * */
    private static List<PageContent> getPageContent() {
        //第一页 第二页...
        return null;
    }


    /**
     * 生成pdf 返回临时文件地址
     * */
    private static String generatePDF(String templatePath, String fileName, List<PageContent> pageContents) throws IOException, DocumentException {

        //生成指定的临时文件地址
        String tmpPdf = generateTmpPath(fileName);

        try(FileOutputStream fos = new FileOutputStream(tmpPdf)) {// 输出流
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, fos);
            doc.open();

            //字体设置 必须有否则看不到
            BaseFont bf = BaseFont.createFont("E:\\pdftest\\msyh.ttf",
                    BaseFont.IDENTITY_H,
                    BaseFont.NOT_EMBEDDED);
            BaseFont hei = BaseFont.createFont("D:\\git3333\\practice\\pdf\\src\\main\\java\\font\\simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            //Font font = new Font(FontF);
            //font.getBaseFont();
            pageContents.stream()
                    .sorted(Comparator.comparing(PageContent::getPageNo))
                    .forEach(pageContent -> {
                        try {
                            PdfReader reader = new PdfReader(templatePath);
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();

                            PdfStamper stamper = new PdfStamper(reader, bos);
                            stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑

                            AcroFields form = stamper.getAcroFields();
                            form.addSubstitutionFont(bf);
                            form.addSubstitutionFont(hei);

                            renderPage(pageContent, stamper, form);//渲染页面

                            stamper.close();//一定要关闭
                            PdfImportedPage impPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), pageContent.getPageNo());
                            copy.addPage(impPage);
                        }
                        catch (IOException | DocumentException e) {

                        }
                    });
            copy.close();
        }
        catch (Exception e) {

        }

        return tmpPdf;
    }


    /**
     * 确定临时文件地址
     * */
    private static String generateTmpPath(String fileName) throws RuntimeException{
      /*  //生成指定的临时文件地址
        String tmpDir = System.getProperty("java.io.tmpdir");
        File file = new File(tmpDir);
        if (!file.exists()) {
            //Logger.error("目录不存在" + tmpPdf);
            //抛出异常 后续无法进行
            throw new RuntimeException("确定临时文件失败");
        }
        return tmpDir + "/" + fileName;*/

      return "E:\\pdftest\\result.pdf";
    }


    /**
     * 通用渲染方法， 不涉及逻辑 直接塞内容
     * */
    private static void renderPage(PageContent pageContent,
                                   PdfStamper stamper,
                                   AcroFields form ) {
        Map<String, TextDesc> textes = pageContent.getTexteFileds();
        Map<String, ImageDesc> images = pageContent.getImagesFileds();

        if (textes != null && textes.size() != 0) {
            renderText(textes, stamper, form);
        }

        if (images != null && images.size() != 0) {
            renderImages(images, stamper, form);
        }
    }


    /**
     * 填充文字
     * */
    private static void renderText(Map<String, TextDesc> textes, PdfStamper stamper, AcroFields form) {
        textes.forEach((k, v) -> {
            try {
                //form.setFieldProperty(k, "微软雅黑", v.getBgColor(), null);
                //必须先设置setFieldProperty 否则属性设置无效
                if (v.getBgColor() != null) {
                    form.setFieldProperty(k, "bgcolor", v.getBgColor(), null);
                }
                form.setField(k, v.getText());
            } catch (IOException | DocumentException e) {
                //Logger.error("");
                e.printStackTrace();
                //需要抛出异常？？
            }
        });
    }


    /**
     * 填充图片
     * */
    private static void renderImages(Map<String, ImageDesc> images, PdfStamper stamper, AcroFields form) {
        images.forEach((k, v) -> {
            AcroFields.FieldPosition fieldPosition = form.getFieldPositions(k).get(0);
            int pageNo = fieldPosition.page;
            Rectangle signRect = fieldPosition.position;
            Image image = null;
            try {
                image = Image.getInstance(v.getPath());
            } catch (BadElementException | IOException e) {
                //Logger.error("");
                e.printStackTrace();
                return;
                //这里需要抛出异常
            }
            System.out.println("width:" + image.getPlainWidth());
            System.out.println("height:" + image.getPlainHeight());
            PdfContentByte under = stamper.getOverContent(pageNo);

            if (image.getHeight() > signRect.getHeight() || image.getWidth() > signRect.getWidth()) {
                image.scaleToFit(signRect);
            }
            else {
                image.scaleAbsolute(image.getWidth(), image.getHeight());
            }

            List<AcroFields.FieldPosition> imgBottom = form.getFieldPositions(k + "bottom");
            if (imgBottom != null && imgBottom.size() > 0) {
                Rectangle signRectBottom = imgBottom.get(0).position;
                image.setAbsolutePosition(signRectBottom.getLeft(), signRectBottom.getBottom() - image.getScaledHeight());//左下角 pdf的坐标系是左下角开始
            }
            else {
                image.setAbsolutePosition(signRect.getLeft(), signRect.getBottom());
            }

            try {
                under.addImage(image);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }


    public static void main(String[] args) throws IOException, DocumentException {
        generate2();
        /*BaseFont bf = BaseFont.createFont("E:\\pdftest\\msyh.ttc", "", BaseFont.EMBEDDED);
        String[] encoding = bf.getCodePagesSupported();
        for (int i = 0; i < encoding.length; i++) {
            System.out.println(encoding[i]);
        }*/
    }


    public static void generate2() throws IOException, DocumentException{
        PageContent pageContent1 = new PageContent();
        pageContent1.setPageNo(1);
        PageContent pageContent2 = new PageContent();
        pageContent2.setPageNo(2);

        PageContent pageContent5 = new PageContent();
        pageContent5.setPageNo(5);

        PageContent pageContent3 = new PageContent();
        pageContent3.setPageNo(3);
        Map<String, TextDesc> textes3 = new HashMap<>();
        TextDesc cityNameTextDesc = new TextDesc();
        cityNameTextDesc.setText("北京");
        //cityNameTextDesc.setBgColor(BaseColor.BLUE);
        textes3.put("city1Name", cityNameTextDesc);

        TextDesc qianDesc = new TextDesc();
        qianDesc.setText(NumberFormat.getNumberInstance().format(123456789));
        textes3.put("qian", qianDesc);

        TextDesc longTextDesc = new TextDesc();
        longTextDesc.setText("北京辖东城区、西城区、朝阳区、丰台区、石景山区、海淀区、顺义区、通州区、大兴区、房山区、门头沟区、昌平区、平谷区、密云区、怀柔区、延庆区16个区，共147个街道、38个乡和144个镇。");
        textes3.put("longtext", longTextDesc);

        Map<String, ImageDesc> images3 = new HashMap<>();
        ImageDesc cityNameImageDesc = new ImageDesc();
        cityNameImageDesc.setAbsolute(false);
        cityNameImageDesc.setPath("E:\\pdftest\\images\\page3long_img.jpg");
        images3.put("cityimage", cityNameImageDesc);
        pageContent3.setTexteFileds(textes3);
        pageContent3.setImagesFileds(images3);

        PageContent pageContent4 = new PageContent();
        pageContent4.setPageNo(4);

        Map<String, TextDesc> textes4 = new HashMap<>();
        TextDesc city2NameDesc = new TextDesc();
        city2NameDesc.setText("北京");
        textes4.put("city2Name", city2NameDesc);

        Map<String, ImageDesc> images4 = new HashMap<>();
        ImageDesc jiatongImage = new ImageDesc();
        jiatongImage.setPath("E:\\pdftest\\images\\small.jpg");
/*        jiatongImage.setAbsolute(true);
        jiatongImage.setHeight(100);
        jiatongImage.setWidth(20);*/
        images4.put("jiatongimg", jiatongImage);
        pageContent4.setTexteFileds(textes4);
        pageContent4.setImagesFileds(images4);

        List<PageContent> pageContents = new ArrayList<>();
        pageContents.add(pageContent1);
        pageContents.add(pageContent3);
        pageContents.add(pageContent2);
        pageContents.add(pageContent5);
        pageContents.add(pageContent4);

        String templatePath = "E:\\pdftest\\template.pdf";
        String destPath = "E:\\pdftest\\result.pdf";

        generatePDF(templatePath, null, pageContents);
    }
    public static void generate() throws IOException, DocumentException {

        PageContent pageContent1 = new PageContent();
        pageContent1.setPageNo(1);
        PageContent pageContent2 = new PageContent();
        pageContent2.setPageNo(2);

        PageContent pageContent5 = new PageContent();
        pageContent5.setPageNo(5);

        PageContent pageContent3 = new PageContent();
        pageContent3.setPageNo(3);
        Map<String, String> textes3 = new HashMap<>();
        textes3.put("city1Name", "北京");
        textes3.put("longtext", "北京辖东城区、西城区、朝阳区、丰台区、石景山区、海淀区、顺义区、通州区、大兴区、房山区、门头沟区、昌平区、平谷区、密云区、怀柔区、延庆区16个区，共147个街道、38个乡和144个镇。");

        Map<String, String> images3 = new HashMap<>();
        images3.put("cityimage", "E:\\pdftest\\images\\page3_img.jpg");
        pageContent3.setTextes(textes3);
        pageContent3.setImages(images3);

        PageContent pageContent4 = new PageContent();
        pageContent4.setPageNo(4);

        Map<String, String> textes4 = new HashMap<>();
        textes4.put("city2Name", "北京");
        Map<String, String> images4 = new HashMap<>();
        images4.put("jiatongimg", "E:\\pdftest\\images\\small.jpg");
        pageContent4.setTextes(textes4);
        pageContent4.setImages(images4);

        List<PageContent> pageContents = new ArrayList<>();
        pageContents.add(pageContent1);
        pageContents.add(pageContent2);
        pageContents.add(pageContent3);
        pageContents.add(pageContent4);
        pageContents.add(pageContent5);

        String templatePath = "E:\\pdftest\\template.pdf";
        String destPath = "E:\\pdftest\\result.pdf";

        //generatePDF(templatePath, null, pageContents);

        generateFull(templatePath, destPath, pageContents);
    }
}
