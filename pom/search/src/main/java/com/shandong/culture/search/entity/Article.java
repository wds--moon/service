package com.shandong.culture.search.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Table(name = "`t_article`")
@Data
public class Article {
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 资源所属分类
     */
    @Column(name = "`resource_classification`")
    private String resourceClassification;

    /**
     * 资源编号
     */
    @Column(name = "`resource_number`")
    private String resourceNumber;

    /**
     * 资源类别
     */
    @Column(name = "`resource_type`")
    private String resourceType;

    /**
     * 资源名字
     */
    @Column(name = "`resource_name`")
    private String resourceName;

    /**
     * 资源标签 以逗号隔开
     */
    @Column(name = "`resource_lable`")
    private String resourceLable;

    /**
     * 地理位置
     */
    @Column(name = "`geographical_location`")
    private String geographicalLocation;

    /**
     * 文化资源数量
     */
    @Column(name = "`resource_quantity`")
    private Integer resourceQuantity;

    /**
     * 区域
     */
    @Column(name = "`region`")
    private String region;

    /**
     * 年代
     */
    @Column(name = "`years`")
    private String years;

    /**
     * 占地面积
     */
    @Column(name = "`area_covered`")
    private String areaCovered;

    /**
     * 等级
     */
    @Column(name = "`grade`")
    private String grade;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private LocalDateTime createTime;

    /**
     * 最后编辑时间
     */
    @Column(name = "`last_update`")
    private LocalDateTime lastUpdate;

    /**
     * 附件存储路径
     */
    @Column(name = "`att_url`")
    private String attUrl;

    /**
     * 图片路径集合
     */
    @Column(name = "`img_url`")
    private String imgUrl;

    /**
     * 正文内容
     */
    @Column(name = "`context`")
    private String context;
}