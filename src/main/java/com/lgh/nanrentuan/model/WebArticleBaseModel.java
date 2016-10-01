package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Administrator on 2016/10/1.
 */
@Getter
@Setter
public class WebArticleBaseModel {
    private String title;
    private String url;
    private Date time;
}
