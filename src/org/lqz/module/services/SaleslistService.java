package org.lqz.module.services;

import java.util.Vector;

/**
 * @ClassName SaleslistService
 * @Description TODO 定义销售单接口
 * @Author TNcarrot_Li
 * @Date 2019/6/25 15:02
 * @Version 1.0
 **/

public interface SaleslistService {

    // 根据条件查询销售单
    public Vector<Vector> selectSaleslistByCondition(Object[] paramArray) throws Exception;

    // 添加销售单
    public int insertSaleslist(Object[] paramArray) throws Exception;
}
