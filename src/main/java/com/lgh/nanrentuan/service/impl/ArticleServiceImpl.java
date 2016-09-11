package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.Article;
import com.lgh.nanrentuan.model.WebArticleListModel;
import com.lgh.nanrentuan.repository.ArticleRepository;
import com.lgh.nanrentuan.service.ArticleService;
import com.lgh.nanrentuan.service.URIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    private URIService uriService;

    @Autowired
    private EntityManager entityManager;

    public List<WebArticleListModel> getIndexArticlelist(Integer pageNo, Integer pageSize) {
        StringBuilder hql = new StringBuilder();
        hql.append("select a from Article a order by a.id");
        Query query = entityManager.createQuery(hql.toString());
        query.setMaxResults(pageSize);
        query.setFirstResult(pageSize * pageNo);
        List list = query.getResultList();

        List<Article> articles = new ArrayList<>();
        list.forEach(x -> {
            articles.add((Article) x);
        });

        List<WebArticleListModel> result = new ArrayList<>();
        toArticleList(result, articles);
        return result;
    }

    private void toArticleList(List<WebArticleListModel> list, List<Article> articles) {
        articles.forEach(x -> {
            WebArticleListModel webArticleListModel = new WebArticleListModel();
            webArticleListModel.setId(x.getId());
            webArticleListModel.setTitle(x.getTitle());
            webArticleListModel.setPictureUrl(x.getPictureUrl());//todo
            webArticleListModel.setSummary(x.getSummary());
            webArticleListModel.setTime(x.getUploadTime());
            webArticleListModel.setUrl(uriService.getArticleURI(x.getId()));
            webArticleListModel.setViews(x.getViews());
            list.add(webArticleListModel);
        });
    }
}
