package org.lqz.module.services.Impl;

import org.lqz.module.dao.Impl.BaseDaoImpl;
import org.lqz.module.services.GoodsService;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName GoodsServiceImpl
 * @Description TODO 实现商品服务接口
 * @Author TNcarrot_Li
 * @Date 2019/6/25 16:06
 * @Version 1.0
 **/

public class GoodsServiceImpl implements GoodsService {

    // 实现按照条件查找
    @Override
    public Vector<Vector> selectByCondition(Object[] paramArray) throws Exception {
        Vector<Vector> rows = new Vector<Vector>();
        BaseDaoImpl dao = new BaseDaoImpl();
        StringBuilder sqlBuilder = new StringBuilder(
                "select goodsId, goodsName, goodsSellingPrice, goodsOrigin, " +
                        "classificationName, warehouseName, goodsInventory, " +
                        "warehouse.warehouseId, classification.classificationId " +
                        "from goods, warehouse, classification " +
                        "where goodsDeleteFlag='0' and " +
                        "warehouseDeleteFlag='0' and " +
                        "classificationDeleteFlag='0' and " +
                        "goods.warehouseId=warehouse.warehouseId and " +
                        "goods.classificationId=classification.classificationId"
        );
        System.out.println("paramArray[0] = " + paramArray[0] + ", paramArray[1] = " + paramArray[1]);
        if (!"全部".equals(paramArray[0])) {
            sqlBuilder.append(" and goods.classificationId='" + paramArray[0] + "'");
        }
        if (!"全部".equals(paramArray[1])) {
            sqlBuilder.append(" and goods.warehouseId='" + paramArray[1] + "'");
        }
        String sql = sqlBuilder.toString();
        List<Object[]> list = dao.select(sql, 9, null);
        if (!list.isEmpty()) {
            for (Object[] object : list) {
                Vector temp = new Vector<String>();
                for (int i = 0; i < object.length; i++) {
                    temp.add(object[i]);
                }
                rows.add(temp);
            }
        }
        return rows;
    }

    // 实现根据商品Id逻辑删除
    @Override
    public int deleteById(Object[] paramArray) throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        int result = 0;
        result = dao.update("update goods set goodsDeleteFlag='1' where goodsId=?", paramArray);
        return result;
    }

    // 实现根据商品Id更新信息
    @Override
    public int updateById(Object[] paramArray) throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        int result = 0;
        result = dao.update("update goods set goodsName=?," +
                        "goodsSellingPrice=?,goodsOrigin=?," +
                        "goodsInventory=?,warehouseId=?,classificationId=? " +
                        "where goodsId=?", paramArray);
        return result;
    }


    // 实现根据商品Id修改库存
    @Override
    public int updateInventoryById(Object[] paramArray) throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        int result = 0;
        result = dao.update("update goods set goodsInventory=goodsInventory+? where goodsId=?", paramArray);
        return result;
    }

    // 实现根据商品Id插入
    @Override
    public int insertById(Object[] paramArray) throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        int result = 0;
        result = dao.insert(
                "insert into goods(goodsId,goodsName," +
                        "goodsSellingPrice,goodsOrigin," +
                        "goodsInventory,warehouseId,classificationId," +
                        "goodsDeleteFlag)  values(?,?,?,?,?,?,?,'0')",
                paramArray);
        return result;
    }



    // 实现查询所有商品Id和名字
    @Override
    public List selectAll() throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        String sql = "select goodsId,goodsName from goods where 1=1 and goodsDeleteFlag='0' ";
        List<Object[]> list = dao.select(sql, 2, null);
        return list;
    }

    // 实现根据商品Id查询
    @Override
    public List selectById(Object[] paramArray) throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        String sql = "select goods.classificationId,classificationName," +
                "goods.warehouseId,warehouseName,goodsInventory " +
                "from goods ,classification,warehouse "
                + "where goods.classificationId=classification.classificationId" +
                " and " +
                "goods.warehouseId=warehouse.warehouseId" +
                " and goods.goodsDeleteFlag='0' " +
                "and classification.classificationDeleteFlag='0'" +
                " and warehouse.warehouseDeleteFlag='0' " +
                "and goods.goodsId=?";
        List<Object[]> list = dao.select(sql, 5, paramArray);
        return list;
    }
}
