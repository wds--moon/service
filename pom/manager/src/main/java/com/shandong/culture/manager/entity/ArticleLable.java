package com.shandong.culture.manager.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Table(name = "`t_article_lable`")
@Data
@Entity
public class ArticleLable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标签名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 状态
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 是否特殊标签
     */
    @Column(name = "`special`")
    private Integer special;

    /**
     * 描述
     */
    @Column(name = "`description`")
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private LocalDateTime createTime;
}
