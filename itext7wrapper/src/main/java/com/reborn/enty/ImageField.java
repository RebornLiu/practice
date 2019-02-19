package com.reborn.enty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageField {
    private String path;
    private boolean isAbsolute;
    private float height;
    private float width;

    /**
     * path类型
     * 0: 本地文件
     * 1：url
     * 2：base64
     * */
    private int type;
}
