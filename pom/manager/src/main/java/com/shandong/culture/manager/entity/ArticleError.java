package com.shandong.culture.manager.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Table(name = "`t_article_error`")
@Data
@Entity
public class ArticleError {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private LocalDateTime createTime;

    /**
     * 文章id
     */
    @Column(name = "`article_id`")
    private Long articleId;

    /**
     * 纠错人
     */
    @Column(name = "`user_id`")
    private Integer userId;

    /**
     * 处理人
     */
    @Column(name = "`deal_id`")
    private Integer dealId;

    /**
     * 处理时间
     */
    @Column(name = "`deal_time`")
    private LocalDateTime dealTime;

    /**
     * 处理结果备注
     */
    @Column(name = "`deal_result`")
    private String dealResult;

    /**
     * 纠错状态
     */
    @Column(name = "`deal_status`")
    private Integer dealStatus;

    /**
     * 纠错内容
     */
    @Column(name = "`error_content`")
    private String errorContent;
}
