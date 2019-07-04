package org.lqz.module.services.Impl;

import org.lqz.module.dao.Impl.BaseDaoImpl;
import org.lqz.module.entity.Warehouse;
import org.lqz.module.services.WarehouseService;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName WarehouseServiceImpl
 * @Description TODO 实现仓库服务接口
 * @Author TNcarrot_Li
 * @Date 2019/6/27 14:20
 * @Version 1.0
 **/

public class WarehouseServiceImpl implements WarehouseService {

    //遍历所有仓库
    @Override
    public List selectAll() throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        List list = dao.select("select warehouseId," +
                "warehouseName from " +
                "warehouse where 1=1 and warehouseDeleteFlag='0' ", 2, null);
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    // 遍历所有仓库返回Vector
    @Override
    public Vector<Vector> selectAllVector() throws Exception {
        Vector<Vector> rows = new Vector<Vector>();
        BaseDaoImpl dao = new BaseDaoImpl();
        List<Object[]> list = dao.select(
                "select warehouseId, warehouseName, warehousePosition" +
                        " from warehouse where 1=1 and warehouseDeleteFlag='0'",
                3, null);
        if (!list.isEmpty()) {
            int number = 1;
            for (Object[] object : list) {
                Vector temp = new Vector<String>();
                for (int i = 0; i < object.length+1; i++) {
                	if( i == 0) {
                		temp.add(number);
                	}
                	else
                		temp.add(object[i-1]);
                }
                rows.add(temp);
                number++;
            }
        }
        return rows;
    }

    //通过Id修改仓库
    @Override
    public int updateById(Object[] paramArray) throws Exception {
        int result = 0;
        BaseDaoImpl dao = new BaseDaoImpl();
        result = dao.update("update warehouse " +
                "set warehouseName=?" +
                " where warehouseId=?", paramArray);
        return result;
    }

    //通过Id逻辑删除仓库
    @Override
    public int deleteById(Object[] paramArray) throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        int result = 0;
        result = dao.update("update warehouse set warehouseDeleteFlag='1' " +
                "where warehouseId=? and not exists(select * from goods where warehouseId=?)", paramArray);
        return result;
    }

    //插入仓库
    @Override
    public int insertById(Object[] paramArray) throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        int result = 0;
        result = dao.insert("" +
                "insert into warehouse" +
                "(warehouseId,warehouseName,warehousePosition) " +
                "values(?,?,?) ", paramArray);
        return result;
    }
}
