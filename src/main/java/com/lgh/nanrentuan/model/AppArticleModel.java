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
    private String summary;
    private String content;
    private String time;
    private Long commentCount;
    private Long likeCount;
    private String userName;

    private String sourceFrom;
    private String sourceUrl;
    private String headPath;
    /**
     * 详情中的图片
     */
    private String images;
    /**
     * 列表中的图片
     */
    private String imagesThumbnail;
}
