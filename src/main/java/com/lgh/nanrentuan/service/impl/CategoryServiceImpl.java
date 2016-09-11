package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.Category;
import com.lgh.nanrentuan.model.CategoryListModel;
import com.lgh.nanrentuan.repository.CategoryRepository;
import com.lgh.nanrentuan.service.CategoryService;
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

    public List<CategoryListModel> getlist() {
        List<Category> categories = categoryRepository.findAllOrderBySortAsc();
        List<CategoryListModel> list = new ArrayList<>();
        categories.forEach(x -> {
            list.add(new CategoryListModel(x.getId(), x.getName()));
        });
        return list;
    }
}
