package org.lqz.module.services;

import org.lqz.module.entity.Warehouse;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName WarehouseService
 * @Description TODO 仓库服务接口
 * @Author TNcarrot_Li
 * @Date 2019/6/24 16:42
 * @Version 1.0
 **/

public interface WarehouseService {

    // 列出所有仓库
    public List<Warehouse> selectAll() throws Exception;

    /**
      * 查询所有仓库，返回容器Vector
      * Vector用法:https://www.runoob.com/java/java-vector-class.html
     **/
    public Vector<Vector> selectAllVector() throws Exception;

    // 根据仓库Id修改仓库，返回受影响的行数
    public int updateById(Object[] paramArray) throws Exception;

    // 根据仓库Id逻辑删除仓库，返回受影响的行数
    public int deleteById(Object[] paramArray) throws Exception;

    // 根据仓库Id添加仓库
    public int insertById(Object[] paramArry) throws Exception;
}
