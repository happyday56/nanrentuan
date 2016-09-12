package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2016/9/12.
 */
@Getter
@Setter
public class AdminCategoryListModel {
    private Long id;
    private String name;
    private String parentName;
    private String path;
    private Integer sort;
    private String url;
}
