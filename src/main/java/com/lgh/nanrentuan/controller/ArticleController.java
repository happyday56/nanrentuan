package com.lgh.nanrentuan.controller;

import com.lgh.nanrentuan.entity.Article;
import com.lgh.nanrentuan.model.WebArticleBaseModel;
import com.lgh.nanrentuan.model.WebArticlePageModel;
import com.lgh.nanrentuan.model.WebCategoryPageModel;
import com.lgh.nanrentuan.repository.ArticleRepository;
import com.lgh.nanrentuan.service.ArticleService;
import com.lgh.nanrentuan.service.CommonService;
import com.lgh.nanrentuan.service.URIService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/9/9.
 */
@Controller
public class ArticleController {

    private static Log log = LogFactory.getLog(ArticleController.class);
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private URIService uriService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "")
    public String index(Model model) {
        return index(0, model);
    }

    @RequestMapping(value = "/{page:[0-9]+}")
    public String index(@PathVariable("page") Integer page, Model model) {
        model.addAttribute("page", articleService.getIndex(page, 10));
        return "index";
//        return test(model);
    }

    @RequestMapping("/{path:[a-z]+}")
    public String category(@PathVariable(value = "path") String path, Model model) {
//        log.info("path " + path);
        if (path.equals("favicon"))
            return "404error";
        else
            return category(path, 0, model);
    }

    @RequestMapping("/{path:[a-z]+}/{page:[0-9]+}")
    public String category(@PathVariable(value = "path") String path, @PathVariable(value = "page") Integer page, Model model) {
        WebCategoryPageModel webCategoryPageModel = articleService.getCategory(path, page, 10);
        if (webCategoryPageModel != null) {
            model.addAttribute("page", webCategoryPageModel);
            return "category";
        } else {
            return "404error";
        }
    }


    @RequestMapping("/{id:[0-9]+}.html")
    public String article(@PathVariable(value = "id") Long id, Model model) {
        WebArticlePageModel webArticlePageModel = articleService.getArticle(id);
        if (webArticlePageModel != null) {
            model.addAttribute("page", webArticlePageModel);
            return "article";
        } else {
            return "404error";
        }
    }


    @RequestMapping(value = "/view/viewcount", method = RequestMethod.GET)
    @ResponseBody
    public void viewcount(@RequestParam Long id) throws Exception {
        Article blog = articleRepository.findOne(id);
        if (blog != null) {
            blog.setViews(blog.getViews() + 1);
            articleRepository.save(blog);
        }
    }

    /**
     * 欢乐变兼容
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/v/news/{id:[0-9]+}.html")
    public String huanlebian(@PathVariable(value = "id") Long id, Model model) {
        return article(id,model);
    }

//
//    @RequestMapping(value = "/about")
//    public String about(Model model) {
//        return "about";
//    }
//
//    @RequestMapping(value = "/as")
//    public String as(Model model) {
//        return "as";
//    }
//
//    @RequestMapping(value = "/links")
//    public String links(Model model) {
//        return "links";
//    }

    @RequestMapping(value = "/404error")
    public String error(Model model) {
//        model.addAttribute("page", commonService.getErrorPage());
        return "404error";
    }

    @RequestMapping(value = "/html/test")
    public String test(Model model) {
        model.addAttribute("page", "i here");
        return "test";
    }

//    @RequestMapping(value = "/html/empty")
//    public String empty(Model model) {
//        return "empty";
//    }

}