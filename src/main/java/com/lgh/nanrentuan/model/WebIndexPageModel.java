package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */

@Getter
@Setter
public class WebIndexPageModel extends WebBasePageModel {
    List<WebArticleListModel> list;
}
