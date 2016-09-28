package com.lgh.nanrentuan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 最新文章
 * Created by Administrator on 2016/5/2.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Category category;

    /**
     * 文章标题
     */
    @Column(length = 200)
    private String title;

    /**
     * 图片地址
     */
    @Column(length = 200)
    private String pictureUrl;
    /**
     * 内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;

    /**
     * 摘要
     */
    @Column(length = 500)
    private String summary;

    /**
     * seo关键字
     */
    @Column(length = 200)
    private String keywords;

    /**
     * seo描述
     */
    @Column(length = 500)
    private String description;


    /**
     * 上传时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date uploadTime;

    /**
     * 浏览次数
     */
    private Long views;
}
