package com.lgh.nanrentuan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/9.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminArticleModel {
    private Long id;
    private String title;
    private String keywords;
    private String description;

    @DateTimeFormat(style = "yyyy-MM-dd")
    private Date uploadTime;
    private Long views;
    private String categoryTitle;
    private String pictureUrl;
}
