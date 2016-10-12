package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.Article;
import com.lgh.nanrentuan.entity.Category;
import com.lgh.nanrentuan.model.*;
import com.lgh.nanrentuan.repository.ArticleRepository;
import com.lgh.nanrentuan.repository.CategoryRepository;
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
import java.text.SimpleDateFormat;
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
        webIndexPageModel.setList(toWebArticleList(articles.getContent()));

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
            webCategoryPageModel.setList(toWebArticleList(articles.getContent()));

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

    private List<WebArticleListModel> toWebArticleList(List<Article> articles) {
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

    public AppArticleListModel getAppArticleList(Long categoryId, Integer page) {
        AppArticleListModel appArticleListModel = new AppArticleListModel();

        Pageable pageable = new PageRequest(page, 10, new Sort(Sort.Direction.DESC, "id"));

        Category category = null;
        if (categoryId != null && categoryId > 0) {
            category = categoryRepository.findOne(categoryId);
        }

        Page<Article> articles = null;
        if (category != null)
            articles = articleRepository.findAllByCategory(category, pageable);
        else
            articles = articleRepository.findAll(pageable);

        appArticleListModel.setCount(articles.getTotalPages());
        appArticleListModel.setPage(page);
        appArticleListModel.setTotalNum(articles.getTotalElements());

        appArticleListModel.setDataList(toAppArticleList(articles.getContent()));
        return appArticleListModel;
    }

    private List<AppArticleModel> toAppArticleList(List<Article> articles) {
        List<AppArticleModel> list = new ArrayList<>();
        articles.forEach(x -> {
            AppArticleModel appArticleModel = new AppArticleModel();
            appArticleModel.setId(x.getId().toString());
            appArticleModel.setTitle(x.getTitle());
//            if (!StringUtils.isEmpty(x.getPictureUrl())) {
//                appArticleModel.setImagesThumbnail(getPictureUrl(x.getPictureUrl()));
//            }
            appArticleModel.setContent(x.getContent());
            appArticleModel.setSummary(x.getSummary());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            appArticleModel.setTime(simpleDateFormat.format(x.getUploadTime()));
//            appArticleModel.setUrl(uriService.getArticleURI(x.getId()));
            appArticleModel.setLikeCount(x.getViews());

            appArticleModel.setUserName("欢喜阅");
//            appArticleModel.setHeadPath("http://tp4.sinaimg.cn/1053636487/30/1297394933/1");
            appArticleModel.setImagesThumbnail("http://ww4.sinaimg.cn/mw600/ad525726jw1dzpjp99lgxj.jpg,http://ww3.sinaimg.cn/mw600/ad525726jw1dzpjpe1vvij.jpg");
            appArticleModel.setImages("http://ww4.sinaimg.cn/mw600/ad525726jw1dzpjp99lgxj.jpg,http://ww3.sinaimg.cn/mw600/ad525726jw1dzpjpe1vvij.jpg");
//            appArticleModel.setSourceUrl("http://qing.blog.sina.com.cn/tj/46daaffc33001xr4.html");
//            appArticleModel.setSourceFrom("未知");
            appArticleModel.setBarnnerType(0L);
//            appArticleModel.setLikeCount(0L);
            appArticleModel.setCommentCount(0L);
            list.add(appArticleModel);
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
