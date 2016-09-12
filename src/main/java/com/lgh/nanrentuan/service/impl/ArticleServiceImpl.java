package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.Article;
import com.lgh.nanrentuan.entity.Category;
import com.lgh.nanrentuan.model.*;
import com.lgh.nanrentuan.repository.ArticleRepository;
import com.lgh.nanrentuan.repository.CategoryRepository;
import com.lgh.nanrentuan.service.ArticleService;
import com.lgh.nanrentuan.service.URIService;
import com.lgh.nanrentuan.service.resource.StaticResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private URIService uriService;

    @Autowired
    private StaticResourceService staticResourceService;

    @Autowired
    private EntityManager entityManager;

    public WebIndexPageModel getIndex(Integer pageNo, Integer pageSize) {
        WebIndexPageModel webIndexPageModel = new WebIndexPageModel();
        webIndexPageModel.setTitle("男人团，找福利，谋福利，快乐多多，幸福多多");
        webIndexPageModel.setKeywords("");//todo
        webIndexPageModel.setDescription("");

        Pageable pageable = new PageRequest(pageNo, pageSize, new Sort(Sort.Direction.ASC, "id"));
        Page<Article> articles = articleRepository.findAll(pageable);
        webIndexPageModel.setList(convertArticleList(articles.getContent()));

        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setPageNumber(pageNo);
        paging.setTotalCount(articles.getTotalElements());
        paging.setTotalPage(articles.getTotalPages());
        paging.setUrl("/index/[number]");
        paging.setPages(paging.getPages());
        webIndexPageModel.setPaging(paging);

        return webIndexPageModel;
    }

    @Override
    public WebCategoryPageModel getCategory(String path, Integer pageNo, Integer pageSize) {
        WebCategoryPageModel webCategoryPageModel = new WebCategoryPageModel();
        webCategoryPageModel.setTitle("");//todo
        webCategoryPageModel.setKeywords("");//todo
        webCategoryPageModel.setDescription("");
        webCategoryPageModel.setNavTitle("");
        webCategoryPageModel.setNavUrl("");

        Category category = categoryRepository.findByPath(path);
        Pageable pageable = new PageRequest(pageNo, pageSize, new Sort(Sort.Direction.ASC, "id"));
        Page<Article> articles = articleRepository.findAllByCategory(category, pageable);
        webCategoryPageModel.setList(convertArticleList(articles.getContent()));

        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setPageNumber(pageNo);
        paging.setTotalCount(articles.getTotalElements());
        paging.setTotalPage(articles.getTotalPages());

        paging.setUrl("/" + path + "/[number]");
        paging.setPages(paging.getPages());
        webCategoryPageModel.setPaging(paging);

        return webCategoryPageModel;
    }

    private List<WebArticleListModel> convertArticleList(List<Article> articles) {
        List<WebArticleListModel> list = new ArrayList<>();
        articles.forEach(x -> {
            WebArticleListModel webArticleListModel = new WebArticleListModel();
            webArticleListModel.setId(x.getId());
            webArticleListModel.setTitle(x.getTitle());
            webArticleListModel.setPictureUrl(getPictureUrl(x.getPictureUrl()));
            webArticleListModel.setSummary(x.getSummary());
            webArticleListModel.setTime(x.getUploadTime());
            webArticleListModel.setUrl(uriService.getArticleURI(x.getId()));
            webArticleListModel.setViews(x.getViews());
            list.add(webArticleListModel);
        });
        return list;
    }

    public WebArticlePageModel getArticle(Long id) {
        WebArticlePageModel webArticlePageModel = new WebArticlePageModel();
        Article article = articleRepository.findOne(id);
        if (article != null) {
            webArticlePageModel.setTitle("");//todo
            webArticlePageModel.setKeywords("");//todo
            webArticlePageModel.setDescription("");
            webArticlePageModel.setNavTitle("");
            webArticlePageModel.setNavUrl("");

            WebArticleModel webArticleModel = new WebArticleModel();
            webArticleModel.setTitle(article.getTitle());
            webArticleModel.setDescription(article.getDescription());
            webArticleModel.setKeywords(article.getKeywords());
            webArticleModel.setTime(article.getUploadTime());

            webArticleModel.setContent(article.getContent());
            webArticleModel.setId(article.getId());
            webArticleModel.setViews(article.getViews());
            webArticlePageModel.setData(webArticleModel);
        }
        return webArticlePageModel;
    }


    public String getPictureUrl(String path) {
        try {
            return staticResourceService.getResource(path).toString();
        } catch (URISyntaxException e) {

        }
        return "";
    }


}
