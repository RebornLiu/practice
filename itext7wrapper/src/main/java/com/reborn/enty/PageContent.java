package com.reborn.enty;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PageContent {
    private int pageNo;
    private Map<String/*formkey*/, TextField> texteFileds;
    private Map<String/*formkey*/, ImageField> imagesFileds;
    private boolean visible = true;//是否渲染到pdf
}
