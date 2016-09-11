package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/11.
 */
@Getter
@Setter
public class WebArticleListModel {
    private Long id;
    private String title;
    private String pictureUrl;
    private String url;
    private String summary;
    private Date time;
    private Long views;
}
