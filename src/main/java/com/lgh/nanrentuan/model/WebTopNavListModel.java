package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
@Getter
@Setter
public class WebTopNavListModel extends WebCategoryListModel {
    private List<WebTopNavListModel> list;

    public WebTopNavListModel(String url, String name) {
        super(url, name);
    }
}
