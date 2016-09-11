package com.lgh.nanrentuan.service;

/**
 * Created by Administrator on 2016/6/10.
 */
public interface URIService {
    String getCategoryURI(String one);

    String getCategoryURI(String one, String two);

    String getArticleURI(Long id);
}
