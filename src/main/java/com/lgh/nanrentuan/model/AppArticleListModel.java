package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
@Getter
@Setter

public class AppArticleListModel {

    private Integer page;
    private Integer count;
    private Long totalNum;
    private List<AppArticleModel> dataList;

}
