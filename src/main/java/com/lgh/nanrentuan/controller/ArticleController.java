package com.lgh.nanrentuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/9/9.
 */
@Controller
public class ArticleController {

    @RequestMapping("/")
    public String index() {

        return "index";
    }

    @RequestMapping("/{one}")
    public String category(@PathVariable(value = "one") String one) {

        return "category";
    }

    @RequestMapping("/{one}/{two}")
    public String category(@PathVariable(value = "one") String one, @PathVariable(value = "two") String two) {

        return "category";
    }

    @RequestMapping("/{id}.html")
    public String article(@PathVariable(value = "id") Long id) {
        return "article";
    }
}
