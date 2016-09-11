package com.lgh.nanrentuan.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingModel {
    /**
     * 开始
     */
    private int begin;
    /**
     * 结束
     */
    private int end;
    /**
     * 当前页码
     */
    private int current;
    /**
     * 总页码
     */
    private int total;
    /**
     * 每页记录数
     */
    private int length;
    /**
     * 总记录数
     */
    private long count;

}