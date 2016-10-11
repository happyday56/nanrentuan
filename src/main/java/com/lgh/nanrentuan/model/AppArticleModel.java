package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2016/10/11.
 */
@Getter
@Setter
public class AppArticleModel {

    private String barnnerType;
    private String id;
    private String title;
    private String content;
    private String time;
    private String commentCount;
    private String likeCount;
    private String userName;

    private String sourceFrom;
    private String sourceUrl;
    private String headPath;
    private String images;
    private String imagesThumbnail;
}
