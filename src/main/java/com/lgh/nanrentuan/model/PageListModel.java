package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lenovo on 2015/7/14.
 */
@Getter
@Setter
public class PageListModel {
    private List<?> list;
    private Paging page;
}
