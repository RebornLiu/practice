package com.reborn.enty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TextField {

    private String text;

    private boolean bold;
}
