package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.Article;
import com.lgh.nanrentuan.entity.Category;
import com.lgh.nanrentuan.model.*;
import com.lgh.nanrentuan.repository.ArticleRepository;
import com.lgh.nanrentuan.repository.CategoryRepository;
import com.lgh.nanrentuan.service.CategoryService;
import com.lgh.nanrentuan.service.CommonService;
import com.lgh.nanrentuan.service.URIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
@Service
public class CommonServiceImpl implements CommonService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private URIService uriService;

    public void setPageCommonData(WebBasePageModel webBasePageModel
            , String title, String keywords, String description) {
        webBasePageModel.setTitle(title);
        webBasePageModel.setKeywords(keywords);
        webBasePageModel.setDescription(description);
        webBasePageModel.setTopNav(getTopNavCategorys());
        webBasePageModel.setRecent(getRecentArticle());
        webBasePageModel.setHot(getHotArticle());
    }

    public List<WebTopNavListModel> getTopNavCategorys() {
        List<WebTopNavListModel> list = new ArrayList<>();
        List<Category> categories = categoryRepository.findAllOrderBySortAsc();
        categories.stream().filter(x -> x.getParent() == null).forEach(parent -> {
            WebTopNavListModel p = new WebTopNavListModel(parent.getPath(), parent.getName());
            List<WebTopNavListModel> list1 = new ArrayList<>();
            categories.stream().filter(y -> y.getParent() != null && y.getParent().equals(parent)).forEach(next -> {
                list1.add(new WebTopNavListModel(next.getPath(), next.getName()));
            });
            p.setList(list1);
            list.add(p);
        });
        return list;
    }

    /**
     * 获得最新文章
     *
     * @return
     */
    private List<WebArticleBaseModel> getRecentArticle() {
        List<WebArticleBaseModel> list = new ArrayList<>();
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "id"));
        Page<Article> articles = articleRepository.findAll(pageable);
        toBaseArticle(list, articles);
        return list;
    }

    /**
     * 获得最热文章
     *
     * @return
     */
    private List<WebArticleBaseModel> getHotArticle() {
        List<WebArticleBaseModel> list = new ArrayList<>();
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "views"));
        Page<Article> articles = articleRepository.findAll(pageable);
        toBaseArticle(list, articles);
        return list;
    }

    private void toBaseArticle(List<WebArticleBaseModel> list, Page<Article> articles) {
        articles.forEach(x -> {
            WebArticleBaseModel webArticleBaseModel = new WebArticleBaseModel();
            webArticleBaseModel.setTitle(x.getTitle());
            webArticleBaseModel.setUrl(uriService.getArticleURI(x.getId()));
            webArticleBaseModel.setTime(x.getUploadTime());
            list.add(webArticleBaseModel);
        });
    }

    public WebErrorPageModel getErrorPage() {
        WebErrorPageModel webErrorPageModel = new WebErrorPageModel();
        setPageCommonData(webErrorPageModel, "", "", "");
        return webErrorPageModel;
    }

}
