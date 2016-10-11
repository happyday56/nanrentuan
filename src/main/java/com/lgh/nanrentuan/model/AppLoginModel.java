package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
@Getter
@Setter
public class AppLoginModel {

    private String  haveNewVer;
    private Long isAdmin;
    private Long adType;
    private List<AppCategoryModel> dataList;
}
