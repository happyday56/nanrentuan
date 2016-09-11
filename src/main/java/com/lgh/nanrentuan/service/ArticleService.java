package com.lgh.nanrentuan.service;

import com.lgh.nanrentuan.model.WebArticleListModel;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface ArticleService {
    List<WebArticleListModel> getIndexArticlelist(Integer pageNo, Integer pageSize);
}
