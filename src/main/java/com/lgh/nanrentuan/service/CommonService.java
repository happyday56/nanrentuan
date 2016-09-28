package com.lgh.nanrentuan.service;

import com.lgh.nanrentuan.model.WebBasePageModel;
import com.lgh.nanrentuan.model.WebErrorPageModel;
import com.lgh.nanrentuan.model.WebIndexPageModel;
import com.lgh.nanrentuan.model.WebTopNavListModel;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface CommonService {

    /**
     * 设置seo等公共数据
     *
     * @param webBasePageModel
     * @param title
     * @param keywords
     * @param description
     */
    void setPageCommonData(WebBasePageModel webBasePageModel
            , String title, String keywords, String description);


    /**
     * 获得顶部分类
     *
     * @return
     */
    List<WebTopNavListModel> getTopNavCategorys();

    /**
     * 获得错误页面
     * @return
     */
    WebErrorPageModel getErrorPage();
}
