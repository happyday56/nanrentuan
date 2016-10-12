package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Administrator on 2016/10/11.
 */
@Getter
@Setter
public class AppArticleModel {

    private Long barnnerType;
    private String id;
    private String title;
    private String content;
    private Date time;
    private Long commentCount;
    private Long likeCount;
    private String userName;

    private String sourceFrom;
    private String sourceUrl;
    private String headPath;
    private String images;
    private String imagesThumbnail;
}
