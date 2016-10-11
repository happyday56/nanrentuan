package com.lgh.nanrentuan.controller;

import com.lgh.nanrentuan.model.*;
import com.lgh.nanrentuan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.eclipse.persistence.sessions.SessionProfiler.Register;

/**
 * Created by Administrator on 2016/10/11.
 */
@Controller
@RequestMapping("/App")
public class AppController {

    @Autowired
    private CategoryService categoryService;

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

        List<AppCategoryModel> appCategoryModels = new ArrayList<>();

        List<CategoryListModel> categoryListModels = categoryService.getParents();
        categoryListModels.forEach(x -> {
            AppCategoryModel appCategoryModel = new AppCategoryModel();
            appCategoryModel.setTypeId(x.getId().toString());
            appCategoryModel.setTypeTitle(x.getName());
            appCategoryModel.setTb_TopicName(x.getId().toString());
            appCategoryModel.setTypeAd("0");
            appCategoryModel.setTypeIntro(x.getName());
            appCategoryModels.add(appCategoryModel);
        });

        appLoginModel.setDataList(appCategoryModels);

        appResultContent.setData(appLoginModel);
        appResult.setResult(appResultContent);
        return appResult;
    }

    @RequestMapping("/list")
    @ResponseBody
    public AppResult list() {
        AppResult appResult = new AppResult();
        AppResultContent appResultContent = new AppResultContent();
        appResultContent.setCode(0L);
        appResultContent.setMsg("");


        AppArticleListModel appArticleListModel = new AppArticleListModel();
        appArticleListModel.setCount(5);
        appArticleListModel.setPage(0);
        appArticleListModel.setTotalNum(5);
        List<AppArticleModel> list = new ArrayList<>();

        list.add(getAppArticleModel());
        list.add(getAppArticleModel());
        list.add(getAppArticleModel());
        list.add(getAppArticleModel());
        list.add(getAppArticleModel());

        appArticleListModel.setDataList(list);
        appResultContent.setData(appArticleListModel);
        appResult.setResult(appResultContent);
        return appResult;
    }

    private AppArticleModel getAppArticleModel() {
        AppArticleModel appArticleModel = new AppArticleModel();
        appArticleModel.setId(UUID.randomUUID().toString());
        appArticleModel.setTitle("时间变短了");
        appArticleModel.setContent("公司座落在被誉为“包公故里、三国旧址、淮军摇篮、科教基地、滨湖新城”的省会城市――安徽合肥。");
        appArticleModel.setUserName("zhagnsan");
        appArticleModel.setTime(new Date().toString());
        appArticleModel.setHeadPath("head");
        appArticleModel.setImagesThumbnail("http://ww4.sinaimg.cn/mw600/ad525726jw1dzpjp99lgxj.jpg,http://ww3.sinaimg.cn/mw600/ad525726jw1dzpjpe1vvij.jpg");
        appArticleModel.setImages("http://ww4.sinaimg.cn/mw600/ad525726jw1dzpjp99lgxj.jpg,http://ww3.sinaimg.cn/mw600/ad525726jw1dzpjpe1vvij.jpg");
        appArticleModel.setSourceUrl("sourceurl");
        appArticleModel.setSourceFrom("sourceRom");
        return appArticleModel;
    }
}
