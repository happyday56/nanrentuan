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
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    WebIndexPageModel getIndex(Integer pageNo, Integer pageSize);

    /**
     * 获取分类页面数据
     *
     * @param path
     * @param pageNo
     * @param pageSize
     * @return
     */
    WebCategoryPageModel getCategory(String path, Integer pageNo, Integer pageSize);

    /**
     * 获取文章页面数据
     *
     * @param id
     * @return
     */
    WebArticlePageModel getArticle(Long id);

    /**
     * 获得图片地址
     * @param path
     * @return
     */
    String getPictureUrl(String path);
}
