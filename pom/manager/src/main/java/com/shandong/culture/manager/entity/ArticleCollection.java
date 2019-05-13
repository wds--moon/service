package com.shandong.culture.manager.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Table(name = "`t_article_collection`")
@Data
@Entity
public class ArticleCollection {
    @Id
    @Column(name = "`id`" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private LocalDateTime createTime;

    /**
     * 用户id
     */
    @Column(name = "`user_id`")
    private Integer userId;

    /**
     * 文章id
     */
    @Column(name = "`article_id`")
    private Long articleId;
}
