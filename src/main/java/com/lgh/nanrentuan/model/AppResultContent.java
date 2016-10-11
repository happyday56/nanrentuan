package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
@Setter
@Getter
public class AppResultContent {
    private Long code;
    private String msg;
    private List<String> extenInfo;
    private Object data;
}
