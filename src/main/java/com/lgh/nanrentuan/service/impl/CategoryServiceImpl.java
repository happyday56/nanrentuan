package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.Category;
import com.lgh.nanrentuan.model.AdminCategoryListModel;
import com.lgh.nanrentuan.model.CategoryListModel;
import com.lgh.nanrentuan.repository.CategoryRepository;
import com.lgh.nanrentuan.service.CategoryService;
import com.lgh.nanrentuan.service.URIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private URIService uriService;

    public List<CategoryListModel> getlist() {
        List<Category> categories = categoryRepository.findAllOrderBySortAsc();
        List<CategoryListModel> list = new ArrayList<>();
        categories.forEach(x -> {
            list.add(new CategoryListModel(x.getId(), x.getName()));
        });
        return list;
    }

    public List<AdminCategoryListModel> getAdminCategoryList() {
        List<Category> categories = categoryRepository.findAllOrderBySortAsc();
        List<AdminCategoryListModel> list = new ArrayList<>();
        categories.forEach(x -> {
            AdminCategoryListModel adminCategoryListModel = new AdminCategoryListModel();
            adminCategoryListModel.setId(x.getId());
            adminCategoryListModel.setName(x.getName());
            if (x.getParent() != null)
                adminCategoryListModel.setParentName(x.getParent().getName());
            adminCategoryListModel.setPath(x.getPath());
            adminCategoryListModel.setSort(x.getSort());
            adminCategoryListModel.setUrl(uriService.getCategoryURI(x.getDescription()));
            list.add(adminCategoryListModel);
        });
        return list;
    }
}
