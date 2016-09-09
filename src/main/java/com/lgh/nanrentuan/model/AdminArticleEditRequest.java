package com.lgh.nanrentuan.model;


import com.lgh.nanrentuan.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/15.
 */
@Getter
@Setter
public class AdminArticleEditRequest {


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
    private List<Category> categories;


}
