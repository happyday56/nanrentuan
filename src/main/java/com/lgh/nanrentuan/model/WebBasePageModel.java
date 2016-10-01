package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * web页面基础
 * Created by lgh on 2016/5/10.
 */
@Getter
@Setter
public abstract class WebBasePageModel {
    /**
     * 标题
     */
    private String title;
    /**
     * 关键字
     */
    private String keywords;

    /**
     * 描述
     */
    private String description;

    /**
     * 导航
     */
    private List<WebTopNavListModel> topNav;

    /**
     * 最新文章
     */
    private List<WebArticleBaseModel> recent;
    /**
     * 最热门文章
     */
    private List<WebArticleBaseModel> hot;
}
