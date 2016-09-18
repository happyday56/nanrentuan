package com.lgh.nanrentuan.service;

import com.lgh.nanrentuan.model.AdminCategoryListModel;
import com.lgh.nanrentuan.model.AdminCategoryModel;
import com.lgh.nanrentuan.model.CategoryListModel;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface CategoryService {
    List<CategoryListModel> getParents();

    List<AdminCategoryListModel> getAdminCategoryList();


    AdminCategoryModel getAdminCategory(String oper, Long id);

    void saveCategory(String oper, Long id, String name, Long categoryId, String path, String title, String keywords, String description, Integer sort);
}
