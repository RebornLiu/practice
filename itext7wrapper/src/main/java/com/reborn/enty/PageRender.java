package com.reborn.enty;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class PageRender {

    public void render(String templatePath, String desc, List<PageContent> contents) throws IOException {
        PdfReader reader = new PdfReader(templatePath);
        PdfWriter writer = new PdfWriter(desc);
        PdfDocument pdfDocument = new PdfDocument(reader, writer);

        PdfAcroForm pdfAcroForm = PdfAcroForm.getAcroForm(pdfDocument, false);



    }


    private void pageWriter(PageContent content, PdfAcroForm form) {

    }


    private void textWriter(String key, TextField textField, PdfAcroForm form) throws IOException {
        PdfFormField pdfFormField = form.getField(key);
        pdfFormField.setValue(textField.getText())
                .setFont(PdfFontFactory.createFont());
    }

    private void imageWriter(String key, ImageField imageFields, PdfAcroForm form, PdfWriter writer, PdfDocument document) {
        PdfFormField formField = form.getField(key);
        if (formField == null) {
            return;
        }

        Image image = decodeImg(imageFields);
        if (image == null) {
            return;
        }



    }


    //todo
    private Image decodeImg(ImageField imageField) {
        try {
            return new Image(ImageDataFactory.create(imageField.getPath()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
