package com.shandong.culture.search.entity;

import javax.persistence.*;
import lombok.Data;

@Table(name = "`t_article_version`")
@Data
public class ArticleVersion {
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章编号前缀
     */
    @Column(name = "`resource_num`")
    private String resourceNum;

    /**
     * 对应版本号
     */
    @Column(name = "`version`")
    private Integer version;
}