package org.lqz.module.services;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName EntrylistService
 * @Description TODO 定义进货表功能接口
 * @Author TNcarrot_Li
 * @Date 2019/6/25 14:52
 * @Version 1.0
 **/

public interface EntrylistService {

    // 根据条件查询入库单
    public Vector<Vector> selectEntrylistByCondition(Object[] paramArray) throws Exception;

    // 根据id逻辑删除入库单
    public int deleteEntrylistById(Object[] paramArray) throws Exception;

    // 根据id更新入库单
    public int updateEntrylistById(Object[] paramArray) throws Exception;

    // 添加入库单
    public int insertEntrylist(Object[] paramArray) throws Exception;
    
 // 通过商品ID查询进价
    public List selectPriceById(Object[] paramArray) throws Exception;

}
