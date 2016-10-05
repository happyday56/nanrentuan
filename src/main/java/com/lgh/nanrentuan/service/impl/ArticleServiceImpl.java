package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.Article;
import com.lgh.nanrentuan.entity.Category;
import com.lgh.nanrentuan.entity.SystemConfig;
import com.lgh.nanrentuan.model.*;
import com.lgh.nanrentuan.repository.ArticleRepository;
import com.lgh.nanrentuan.repository.CategoryRepository;
import com.lgh.nanrentuan.repository.SystemConfigRepository;
import com.lgh.nanrentuan.service.ArticleService;
import com.lgh.nanrentuan.service.CommonService;
import com.lgh.nanrentuan.service.SystemConfigService;
import com.lgh.nanrentuan.service.URIService;
import com.lgh.nanrentuan.service.resource.StaticResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private CommonService commonService;

    @Autowired
    private SystemConfigService systemConfigService;


    public WebIndexPageModel getIndex(Integer pageNo, Integer pageSize) {
        WebIndexPageModel webIndexPageModel = new WebIndexPageModel();

        SystemConfigModel systemConfigModel = systemConfigService.list();

        commonService.setPageCommonData(webIndexPageModel
                , systemConfigModel.getTitle()
                , systemConfigModel.getKeywords()
                , systemConfigModel.getDescription());

        Pageable pageable = new PageRequest(pageNo, pageSize, new Sort(Sort.Direction.DESC, "id"));
        Page<Article> articles = articleRepository.findAll(pageable);
        webIndexPageModel.setList(convertArticleList(articles.getContent()));

        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setPageNumber(pageNo);
        paging.setTotalCount(articles.getTotalElements());
        paging.setTotalPage(articles.getTotalPages());
        paging.setUrl("/[number]");
        paging.setPages(paging.getPages());
        webIndexPageModel.setPaging(paging);

        return webIndexPageModel;
    }

    @Override
    public WebCategoryPageModel getCategory(String path, Integer pageNo, Integer pageSize) {
        Category category = categoryRepository.findByPath(path);
        if (category != null) {
            WebCategoryPageModel webCategoryPageModel = new WebCategoryPageModel();
            commonService.setPageCommonData(webCategoryPageModel, category.getTitle(), category.getKeywords(), category.getDescription());

            webCategoryPageModel.setNavTitle(category.getName());
            webCategoryPageModel.setNavUrl(uriService.getCategoryURI(category.getPath()));

            Pageable pageable = new PageRequest(pageNo, pageSize, new Sort(Sort.Direction.DESC, "id"));
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
        return null;
    }

    private List<WebArticleListModel> convertArticleList(List<Article> articles) {
        List<WebArticleListModel> list = new ArrayList<>();
        articles.forEach(x -> {
            WebArticleListModel webArticleListModel = new WebArticleListModel();
            webArticleListModel.setId(x.getId());
            webArticleListModel.setTitle(x.getTitle());
            if (!StringUtils.isEmpty(x.getPictureUrl())) {
                webArticleListModel.setPictureUrl(getPictureUrl(x.getPictureUrl()));
            }
            webArticleListModel.setSummary(x.getSummary());
            webArticleListModel.setTime(x.getUploadTime());
            webArticleListModel.setUrl(uriService.getArticleURI(x.getId()));
            webArticleListModel.setViews(x.getViews());
            list.add(webArticleListModel);
        });
        return list;
    }

    public WebArticlePageModel getArticle(Long id) {
        Article article = articleRepository.findOne(id);
        if (article != null) {
            WebArticlePageModel webArticlePageModel = new WebArticlePageModel();
            commonService.setPageCommonData(webArticlePageModel, article.getTitle(), article.getKeywords(), article.getDescription());
            if (article != null) {
                webArticlePageModel.setTitle(article.getTitle());
                webArticlePageModel.setKeywords(article.getKeywords());
                webArticlePageModel.setDescription(article.getDescription());
                webArticlePageModel.setNavTitle(article.getCategory().getName());
                webArticlePageModel.setNavUrl(uriService.getCategoryURI(article.getCategory().getPath()));

                WebArticleModel webArticleModel = new WebArticleModel();
                webArticleModel.setTitle(article.getTitle());
                webArticleModel.setTime(article.getUploadTime());

                webArticleModel.setContent(article.getContent());
                webArticleModel.setId(article.getId());
                webArticleModel.setViews(article.getViews());
                webArticlePageModel.setData(webArticleModel);
            }
            return webArticlePageModel;
        }
        return null;
    }


    public String getPictureUrl(String path) {
        try {
            return staticResourceService.getResource(path).toString();
        } catch (URISyntaxException e) {

        }
        return "";
    }


}
