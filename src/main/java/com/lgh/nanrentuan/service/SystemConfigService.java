package com.lgh.nanrentuan.service;

import com.lgh.nanrentuan.model.SystemConfigModel;

/**
 * Created by Administrator on 2016/10/2.
 */
public interface SystemConfigService {

    String title = "Title";
    String keywords = "Keywords";
    String description = "Description";
    String top = "Top";

    SystemConfigModel list();

    void save(String title, String keywords, String description, String top);

}
