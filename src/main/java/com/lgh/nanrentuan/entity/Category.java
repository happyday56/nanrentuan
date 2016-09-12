package com.lgh.nanrentuan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/2.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "path")})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 标题
     */
    @Column(length = 20)
    private String name;
    /**
     * 父项
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Category parent;
    /**
     * 分类路径
     * 根据标题全拼产生
     */
    @Column(length = 50)
    private String path;

    /**
     * seo搜索标题
     */
    @Column(length = 200)
    private String title;
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
     * 排序号
     */
    private Integer sort;

}
