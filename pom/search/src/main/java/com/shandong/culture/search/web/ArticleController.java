package com.shandong.culture.search.web;

import com.shandong.culture.search.formvo.ArticleForm;
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
@RequestMapping("article")
@RestController
public class ArticleController {

    private final static Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;


    @ApiOperation(value = "保存文章", notes = "保存文章")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseVO saveArticleInfo(@RequestBody ArticleForm article) {
        LOG.info("{}", article);
        articleService.save(article);
        return ResponseVO.success();
    }

    @ApiOperation(value = "修改文章", notes = "修改文章")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseVO updateArticleInfo() {
        return ResponseVO.success();
    }

    @ApiOperation(value = "删除文章", notes = "删除文章")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteArticleInfo(@PathVariable("id") String id) {
        articleService.delete(id);
        return ResponseVO.success();
    }

    @ApiOperation(value = "条件查询文章", notes = "条件查询文章")
    @RequestMapping(value = "/findArticle", method = RequestMethod.POST)
    public ResponseVO deleteArticleInfo(@RequestBody ArticleForm article) {
        return articleService.findArticle(article);
    }

    @ApiOperation(value = "模糊搜索", notes = "模糊搜索")
    @RequestMapping(value = "/matchQueryArticle", method = RequestMethod.POST)
    public ResponseVO matchQueryArticle(@RequestBody ArticleForm article) {
        return articleService.matchQueryArticle(article);
    }
}
