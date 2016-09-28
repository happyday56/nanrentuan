package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.Category;
import com.lgh.nanrentuan.model.WebBasePageModel;
import com.lgh.nanrentuan.model.WebErrorPageModel;
import com.lgh.nanrentuan.model.WebIndexPageModel;
import com.lgh.nanrentuan.model.WebTopNavListModel;
import com.lgh.nanrentuan.repository.CategoryRepository;
import com.lgh.nanrentuan.service.CategoryService;
import com.lgh.nanrentuan.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
@Service
public class CommonServiceImpl implements CommonService {


    @Autowired
    private CategoryRepository categoryRepository;

    public void setPageCommonData(WebBasePageModel webBasePageModel
            , String title, String keywords, String description) {
        webBasePageModel.setTitle(title);
        webBasePageModel.setKeywords(keywords);
        webBasePageModel.setDescription(description);
        webBasePageModel.setTopNav(getTopNavCategorys());
    }

    public List<WebTopNavListModel> getTopNavCategorys() {
        List<WebTopNavListModel> list = new ArrayList<>();
        List<Category> categories = categoryRepository.findAllOrderBySortAsc();
        categories.stream().filter(x -> x.getParent() == null).forEach(parent -> {
            WebTopNavListModel p = new WebTopNavListModel(parent.getPath(), parent.getName());
            List<WebTopNavListModel> list1 = new ArrayList<>();
            categories.stream().filter(y -> y.getParent() != null && y.getParent().equals(parent)).forEach(next -> {
                list1.add(new WebTopNavListModel(next.getPath(), next.getName()));
            });
            p.setList(list1);
            list.add(p);
        });
        return list;
    }

    public WebErrorPageModel getErrorPage() {
        WebErrorPageModel webErrorPageModel = new WebErrorPageModel();
        setPageCommonData(webErrorPageModel, "", "", "");
        return webErrorPageModel;
    }

}
