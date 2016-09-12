package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
@Getter
@Setter
public class AdminCategoryModel {
    private Long id;
    private String name;
    private Long parentId;
    private String parentName;
    private String path;
    private String title;
    private String keywords;
    private String description;
    private Integer sort;
    private List<CategoryListModel> parents;
}
