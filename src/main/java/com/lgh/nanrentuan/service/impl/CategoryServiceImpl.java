package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.Category;
import com.lgh.nanrentuan.model.AdminCategoryListModel;
import com.lgh.nanrentuan.model.AdminCategoryModel;
import com.lgh.nanrentuan.model.AppCategoryModel;
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

    public List<CategoryListModel> getParents() {
        List<Category> categories = categoryRepository.findParents();
        List<CategoryListModel> list = new ArrayList<>();
        categories.forEach(x -> {
            list.add(new CategoryListModel(x.getId(), x.getName()));
        });
        return list;
    }

    public List<AdminCategoryListModel> getAdminCategoryList() {
        List<Category> categories = categoryRepository.findParents();
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

    public AdminCategoryModel getAdminCategory(String oper, Long id) {
        AdminCategoryModel adminCategoryModel = new AdminCategoryModel();
        if (oper.equals("edit")) {
            Category category = categoryRepository.findOne(id);
            adminCategoryModel.setId(id);
            adminCategoryModel.setName(category.getName());
            adminCategoryModel.setTitle(category.getTitle());
            adminCategoryModel.setKeywords(category.getKeywords());
            adminCategoryModel.setDescription(category.getDescription());
            if (category.getParent() != null) {
                adminCategoryModel.setParentId(category.getParent().getId());
                adminCategoryModel.setParentName(category.getParent().getName());
            }
            adminCategoryModel.setPath(category.getPath());
            adminCategoryModel.setSort(category.getSort());
        } else if (oper.equals("add")) {
            if (id != null && id > 0) {
                adminCategoryModel.setParentId(id);
            }
        }

        List<Category> categories = categoryRepository.findAllByParent(null);
        List<CategoryListModel> categoryListModels = new ArrayList<>();
        categories.forEach(x -> categoryListModels.add(new CategoryListModel(x.getId(), x.getName())));
        adminCategoryModel.setParents(categoryListModels);

        return adminCategoryModel;
    }

    public void saveCategory(String oper, Long id, String name, Long categoryId, String path, String title, String keywords, String description, Integer sort) {
        Category category = null;
        if (oper.equals("edit")) {
            category = categoryRepository.findOne(id);
            if (category != null) {
                category.setSort(sort);
                category.setPath(path);
                category.setDescription(description);
                category.setKeywords(keywords);
                category.setName(name);
                category.setTitle(title);
                Category parent = categoryRepository.findOne(categoryId);
                if (parent != null) category.setParent(parent);
            }
        } else {
            category = new Category();
            category.setSort(sort);
            category.setPath(path);
            category.setDescription(description);
            category.setKeywords(keywords);
            category.setName(name);
            category.setTitle(title);
            Category parent = categoryRepository.findOne(categoryId);
            if (parent != null) category.setParent(parent);
        }
        categoryRepository.save(category);
    }

    public List<AppCategoryModel> getAppCategoryList() {
        List<AppCategoryModel> appCategoryModels = new ArrayList<>();
        List<CategoryListModel> categoryListModels = getParents();

        categoryListModels.forEach(x -> {
            AppCategoryModel appCategoryModel = new AppCategoryModel();
            appCategoryModel.setTypeId(x.getId().toString());
            appCategoryModel.setTypeTitle(x.getName());
            appCategoryModel.setTb_TopicName(x.getId().toString());
            appCategoryModel.setTypeAd("0");
            appCategoryModel.setTypeIntro(x.getName());
            appCategoryModels.add(appCategoryModel);
        });
        return appCategoryModels;
    }
}
