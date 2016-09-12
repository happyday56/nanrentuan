package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
@Getter
@Setter
public class WebCategoryPageModel extends WebBasePageModel {
    List<WebArticleListModel> list;
    Paging paging;
    String navUrl;
    String navTitle;
}
