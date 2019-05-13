package com.shandong.culture.search.web;

import com.dm.common.dto.TableResult;
import com.shandong.culture.search.entity.Article;
import com.shandong.culture.search.formvo.ArticleForm;
import com.shandong.culture.search.formvo.ArticleSearchFrom;
import com.shandong.culture.search.model.ResponseVO;
import com.shandong.culture.search.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 文章操作入口类, 新增, 删除, 修改, 查询等接口
 * @Author: moon
 * @CreateDate: 2019/5/5 0005 13:48
 * @UpdateUser: moon
 * @UpdateDate: 2019/5/5 0005 13:48
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Api("资源处理类处理类")
@RequestMapping("articles")
@RestController
public class ArticleController {

    private final static Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;


    @ApiOperation(value = "保存文章", notes = "保存文章")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article saveArticleInfo(@RequestBody Article article) {
        articleService.save(article);
        return article;
    }

    @ApiOperation(value = "修改文章", notes = "修改文章")
    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Article updateArticleInfo(@PathVariable("id") String id, @RequestBody Article article) {
        articleService.update(article);
        return article;
    }

    @ApiOperation(value = "删除文章", notes = "删除文章")
    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticleInfo(@PathVariable("id") String id) {
        articleService.delete(id);
    }

    @ApiOperation(value = "模糊搜索,条件查询文章", notes = "模糊搜索,条件查询文章")
    @GetMapping(params = {"draw", "!keyword"})
    public TableResult<Article> matchQueryArticle(@RequestParam("draw") Long draw, ArticleForm article) {
        return TableResult.success(draw, articleService.matchQueryArticle(article));
    }


    @ApiOperation(value = "全文搜索", notes = "全文搜索")
    @GetMapping("/searchArticle")
    public TableResult<Article> searchArticle(@RequestParam("draw") Long draw, ArticleSearchFrom article) {
        return TableResult.success(draw, articleService.searchArticle(article));
    }

}
