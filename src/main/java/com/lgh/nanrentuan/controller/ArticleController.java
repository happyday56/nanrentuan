package com.lgh.nanrentuan.controller;

import com.lgh.nanrentuan.entity.Article;
import com.lgh.nanrentuan.model.WebIndexPageModel;
import com.lgh.nanrentuan.repository.ArticleRepository;
import com.lgh.nanrentuan.repository.CategoryRepository;
import com.lgh.nanrentuan.service.ArticleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping("/")
    public String index(Model model) {
        return index(0, model);
    }

    @RequestMapping(value = "/{page}")
    public String index(@PathVariable("page") Integer page, Model model) {
        WebIndexPageModel webIndexPageModel = new WebIndexPageModel();
        webIndexPageModel.setTitle("男人团，找福利，谋福利，快乐多多，幸福多多");
        webIndexPageModel.setKeywords("");//todo
        webIndexPageModel.setDescription("");

        webIndexPageModel.setList(articleService.getIndexArticlelist(page, 10));
        model.addAttribute("page", webIndexPageModel);
        return "index";
    }

    @RequestMapping("/{one}")
    public String category(@PathVariable(value = "one") String one, Model model) {

        return "category";
    }

    @RequestMapping("/{one}/{two}")
    public String category(@PathVariable(value = "one") String one, @PathVariable(value = "two") String two, Model model) {

        return "category";
    }

    @RequestMapping("/{id}.html")
    public String article(@PathVariable(value = "id") Long id, Model model) {
        return "article";
    }


    @RequestMapping(value = "/viewcount", method = RequestMethod.GET)
    @ResponseBody
    public void viewcount(@RequestParam Long id) throws Exception {
        Article blog = articleRepository.findOne(id);
        if (blog != null) {
            blog.setViews(blog.getViews() + 1);
            articleRepository.save(blog);
        }
    }

    @RequestMapping(value = "/about")
    public String about(Model model) {
        return "about";
    }

    @RequestMapping(value = "/as")
    public String as(Model model) {
        return "as";
    }

    @RequestMapping(value = "/links")
    public String links(Model model) {
        return "links";
    }

    @RequestMapping(value = "/404error")
    public String error(Model model) {
        return "404error";
    }

}
