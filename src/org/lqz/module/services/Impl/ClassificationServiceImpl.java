package org.lqz.module.services.Impl;

import org.lqz.module.dao.Impl.BaseDaoImpl;
import org.lqz.module.services.ClassificationService;

import java.util.List;

/**
 * @ClassName Classification
 * @Description TODO 实现查询分类方法接口
 * @Author TNcarrot_Li
 * @Date 2019/6/25 15:43
 * @Version 1.0
 **/

public class ClassificationServiceImpl implements ClassificationService {

    // 查询所有分类

    @Override
    public List selectAll() throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        List list = dao.select("select classificationId, classificationName " +
                "from classification where 1=1 and classificationdeleteflag='0' ",
                2,null);
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }
}
