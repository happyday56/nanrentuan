package com.lgh.nanrentuan.controller;

import com.lgh.nanrentuan.model.*;
import com.lgh.nanrentuan.service.ArticleService;
import com.lgh.nanrentuan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2016/10/11.
 */
@Controller
@RequestMapping("/App")
public class AppController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/Register")
    @ResponseBody
    public AppResult register() {
        AppResult appResult = new AppResult();
        AppResultContent appResultContent = new AppResultContent();
        appResultContent.setCode(0L);
        appResultContent.setMsg("");

        AppRegisterModel appRegisterModel = new AppRegisterModel();
        appRegisterModel.setKeys("f9baa248290ac8f98dbf3f4a2a6602c8");


        appResultContent.setData(appRegisterModel);
        appResult.setResult(appResultContent);
        return appResult;
    }

    @RequestMapping("/Login")
    @ResponseBody
    public AppResult login() {
        AppResult appResult = new AppResult();
        AppResultContent appResultContent = new AppResultContent();
        appResultContent.setCode(0L);
        appResultContent.setMsg("");

        AppLoginModel appLoginModel = new AppLoginModel();
        appLoginModel.setAdType(1L);
        appLoginModel.setIsAdmin(1L);
        appLoginModel.setHaveNewVer("0");

        appLoginModel.setDataList(categoryService.getAppCategoryList());

        appResultContent.setData(appLoginModel);
        appResult.setResult(appResultContent);
        return appResult;
    }

    @RequestMapping("/list")
    @ResponseBody
    public AppResult list(@RequestParam(value = "arcTypeID", required = false) String arcTypeID
            , @RequestParam(value = "page", required = false) Integer page) {
        AppResult appResult = new AppResult();
        AppResultContent appResultContent = new AppResultContent();
        appResultContent.setCode(0L);
        appResultContent.setMsg("");

        AppArticleListModel appArticleListModel = articleService.getAppArticleList(Long.valueOf(arcTypeID), page);
        appResultContent.setData(appArticleListModel);
        appResult.setResult(appResultContent);
        return appResult;
    }

    private AppArticleModel getAppArticleModel(Integer id) {
        AppArticleModel appArticleModel = new AppArticleModel();
        appArticleModel.setId(id.toString());
        appArticleModel.setTitle("时间变短了");
        appArticleModel.setContent("公司座落在被誉为“包公故里、三国旧址、淮军摇篮");
//        appArticleModel.setTime(new Date());

        appArticleModel.setUserName("zhagnsan");
        appArticleModel.setHeadPath("http://tp4.sinaimg.cn/1053636487/30/1297394933/1");
        appArticleModel.setImagesThumbnail("http://ww4.sinaimg.cn/mw600/ad525726jw1dzpjp99lgxj.jpg,http://ww3.sinaimg.cn/mw600/ad525726jw1dzpjpe1vvij.jpg");
        appArticleModel.setImages("http://ww4.sinaimg.cn/mw600/ad525726jw1dzpjp99lgxj.jpg,http://ww3.sinaimg.cn/mw600/ad525726jw1dzpjpe1vvij.jpg");
        appArticleModel.setSourceUrl("http://qing.blog.sina.com.cn/tj/46daaffc33001xr4.html");
        appArticleModel.setSourceFrom("未知");
        appArticleModel.setBarnnerType(0L);
        appArticleModel.setLikeCount(0L);
        appArticleModel.setCommentCount(0L);
        return appArticleModel;
    }
}
