package com.shandong.culture.search.web;

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
@Api("文章处理类")
@RequestMapping("articles")
@RestController
public class ArticleController {

    private final static Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;


    @ApiOperation(value = "保存文章", notes = "保存文章")
    @PostMapping(value = "/save")
    public ResponseVO saveArticleInfo(@RequestBody Article article) {
        articleService.save(article);
        return ResponseVO.success();
    }

    @ApiOperation(value = "修改文章", notes = "修改文章")
    @PutMapping(value = "/update")
    public ResponseVO updateArticleInfo() {
        return ResponseVO.success();
    }

    @ApiOperation(value = "删除文章", notes = "删除文章")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseVO deleteArticleInfo(@PathVariable("id") String id) {
        articleService.delete(id);
        return ResponseVO.success();
    }

    @ApiOperation(value = "条件查询文章", notes = "条件查询文章")
    @GetMapping(value = "/findArticle")
    public ResponseVO findArticle(ArticleForm article) {
        return articleService.findArticle(article);
    }

    @ApiOperation(value = "模糊搜索", notes = "模糊搜索")
    @GetMapping(value = "/matchQueryArticle")
    public ResponseVO matchQueryArticle(ArticleForm article) {
        return articleService.matchQueryArticle(article);
    }


    @ApiOperation(value = "全文搜索", notes = "全文搜索")
    @GetMapping(value = "/searchArticle")
    public ResponseVO searchArticle(ArticleSearchFrom article) {
        return articleService.searchArticle(article);
    }
}
