package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Administrator on 2015/7/15.
 */
@Getter
@Setter
public class AdminArticleSaveRequest {


    private Long id;


    private String title;
    private String content;


    private String summary;
    private String keywords;
    private String description;

    private Date uploadTime;
    private Long views;

    private Long categoryId;

    private String pictureUrl;


    private String operType;

}
