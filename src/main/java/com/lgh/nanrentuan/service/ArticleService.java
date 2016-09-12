package com.lgh.nanrentuan.service;


import com.lgh.nanrentuan.model.WebArticlePageModel;
import com.lgh.nanrentuan.model.WebCategoryPageModel;
import com.lgh.nanrentuan.model.WebIndexPageModel;


/**
 * Created by Administrator on 2016/9/9.
 */
public interface ArticleService {
    /**
     * 获得首页数据
     * @param pageNo
     * @param pageSize
     * @return
     */
    WebIndexPageModel getIndex(Integer pageNo, Integer pageSize);

    WebCategoryPageModel getCategory(String path ,Integer pageNo, Integer pageSize);

    WebArticlePageModel getArticle(Long id);
}
