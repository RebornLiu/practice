package util;

import java.util.Map;

public class PageContent {
    private int pageNo;
    private Map<String, String> textes;
    private Map<String, String> images;

    private Map<String/*formkey*/, TextDesc> texteFileds;
    private Map<String/*formkey*/, ImageDesc> imagesFileds;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Map<String, String> getTextes() {
        return textes;
    }

    public void setTextes(Map<String, String> textes) {
        this.textes = textes;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    public Map<String, TextDesc> getTexteFileds() {
        return texteFileds;
    }

    public void setTexteFileds(Map<String, TextDesc> texteFileds) {
        this.texteFileds = texteFileds;
    }

    public Map<String, ImageDesc> getImagesFileds() {
        return imagesFileds;
    }

    public void setImagesFileds(Map<String, ImageDesc> imagesFileds) {
        this.imagesFileds = imagesFileds;
    }
}
