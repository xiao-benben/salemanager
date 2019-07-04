package org.lqz.module.services;

import org.lqz.module.entity.Classification;

import java.util.List;


/**
 * @ClassName ClassificationService
 * @Description TODO 商品分类服务接口,返回所有商品分类列表
 * @Author TNcarrot_Li
 * @Date 2019/6/24 16:24
 * @Version 1.0
 **/

public interface ClassificationService{

    // 列出所有商品种类,返回一个Classification列表
    public List<Classification> selectAll() throws Exception;

}
