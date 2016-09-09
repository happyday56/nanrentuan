package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lenovo on 2015/7/14.
 */
@Getter
@Setter
public class AdminArticleListRequest {
    private int type;
    private String key;
    private int current;
    private int isContentNull;
    private int length;


}
