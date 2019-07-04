package org.lqz.module.services;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName GoodsService
 * @Description TODO 定义商品服务接口
 * @Author TNcarrot_Li
 * @Date 2019/6/25 14:37
 * @Version 1.0
 **/

public interface GoodsService {

    // 根据条件查询商品
    public Vector<Vector> selectByCondition(Object[] paramArray) throws Exception;

    // 根据Id逻辑删除商品
    public int deleteById(Object[] paramArray) throws Exception;

    // 根据id更新商品
    public int updateById(Object[] paramArray) throws Exception;

    // 根据id新添商品
    public int insertById(Object[] paramArray) throws Exception;

    // 查询所有商品
    public List selectAll() throws Exception;

    // 根据id查询商品
    public List selectById(Object[] paramArray) throws Exception;

    // 根据id修改库存
    public int updateInventoryById(Object[] paramArray) throws Exception;

}
