package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2016/9/12.
 */
@Getter
@Setter
public class WebArticlePageModel extends WebBasePageModel {
    String navUrl;
    String navTitle;
    WebArticleModel data;
}
