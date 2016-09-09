package com.lgh.nanrentuan.controller;


import com.lgh.nanrentuan.repository.ArticleRepository;
import com.lgh.nanrentuan.repository.CategoryRepository;
import com.lgh.nanrentuan.entity.Article;
import com.lgh.nanrentuan.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/7/14.
 */
@Controller
@RequestMapping("/man")
public class ManagerController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager entityManager;

    @RequestMapping("/main")
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("manager/main");

        return view;
    }

    @RequestMapping("/articlelist")
    public ModelAndView articlelist(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("manager/articlelist");
        return view;
    }


    @RequestMapping("/articlelist.do")
    public
    @ResponseBody
    PageListModel articlelistdo(@RequestBody AdminArticleListRequest data) {

        PageListModel result = new PageListModel();

        StringBuilder hql = new StringBuilder();
        hql.append("select a from Article ");
        if (!StringUtils.isEmpty(data.getKey())) hql.append(" where a.title like :title");

        hql.append(" order by a.id desc");

        Query query = entityManager.createQuery(hql.toString());
        if (!StringUtils.isEmpty(data.getKey())) query.setParameter("title", "%" + data.getKey() + "%");
        query.setFirstResult((data.getCurrent() - 1) * data.getLength());
        query.setMaxResults(data.getLength());
        List resultList = query.getResultList();

        List<AdminArticleModel> list = new ArrayList<>();
        resultList.forEach(x -> {
            Article article = (Article) x;
            list.add(new AdminArticleModel(article.getId(), article.getTitle()
                    , article.getKeywords()
                    , article.getDescription()
                    , article.getUploadTime()
                    , article.getViews()
                    , article.getCategory().getTitle()
                    , article.getPictureUrl()));
        });
        result.setList(list);


        Paging page = new Paging();
        page.setCurrent(data.getCurrent());
        page.setLength(data.getLength());
        page.setCount(resultList.size());
        result.setPage(page);

        return result;
    }

    @RequestMapping("/articleedit")
    public ModelAndView articleedit(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("manager/articleedit");
        return view;
    }

    @RequestMapping("/articleedit.do")
    public
    @ResponseBody
    Object articleeditdo(Long id) {

        if (id == null) {
            return categoryRepository.findAll();
        } else {
            AdminArticleEditRequest result = new AdminArticleEditRequest();
            Article article = articleRepository.findOne(id);
            if (article != null) {
                result.setId(article.getId());
                if (article.getCategory() != null)
                    result.setCategoryId(article.getCategory().getId());
                result.setContent(article.getContent());
                result.setSummary(article.getSummary());
                result.setDescription(article.getDescription());
                result.setKeywords(article.getKeywords());
                result.setTitle(article.getTitle());
                result.setUploadTime(article.getUploadTime());
                result.setViews(article.getViews());

                result.setPictureUrl(article.getPictureUrl());
                result.setCategories(categoryRepository.findAll());
            }
            return result;
        }

    }

    @RequestMapping("/articleedit.save")
    public
    @ResponseBody
    Integer articleeditsave(@RequestBody AdminArticleSaveRequest request) {

        if ("add".equals(request.getOperType())) {
            Article article = new Article();
            article.setContent(request.getContent());
            article.setSummary(request.getSummary());
            article.setDescription(request.getDescription());
            article.setKeywords(request.getKeywords());
            article.setTitle(request.getTitle());
            article.setUploadTime(request.getUploadTime());
            article.setViews(request.getViews());
            article.setCategory(categoryRepository.findOne(request.getCategoryId()));
            article.setPictureUrl(request.getPictureUrl());
            articleRepository.save(article);
        } else {
            Article article = articleRepository.findOne(request.getId());
            article.setContent(request.getContent());
            article.setSummary(request.getSummary());
            article.setDescription(request.getDescription());
            article.setKeywords(request.getKeywords());
            article.setTitle(request.getTitle());
            article.setUploadTime(request.getUploadTime());
            article.setViews(request.getViews());
            article.setCategory(categoryRepository.findOne(request.getCategoryId()));
            article.setPictureUrl(request.getPictureUrl());
            articleRepository.save(article);
        }

        return 1;
    }

}
