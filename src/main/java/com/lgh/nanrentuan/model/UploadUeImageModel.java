package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2016/9/12.
 */
@Getter
@Setter
public class UploadUeImageModel {
    private int error;

    private int code;

    private String message;

    private String url;

    private Object data;
}
