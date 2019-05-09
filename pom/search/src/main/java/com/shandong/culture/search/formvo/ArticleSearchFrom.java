package com.shandong.culture.search.formvo;

import lombok.Data;

/**
* @Description:    查询专用
* @Author:         moon
* @CreateDate:     2019/5/8 0008 15:27
* @UpdateUser:     moon
* @UpdateDate:     2019/5/8 0008 15:27
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Data
public class ArticleSearchFrom  extends  BaseForm{

    /**
     * 资源所属分类
     */
    private String resourceClassification;
    /**
     * 搜索关键字
     */
    private String keyword;
    /**
     * 资源标签
     */
    private String resourceLable;
}
