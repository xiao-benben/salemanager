package org.lqz.module.services.Impl;

import org.lqz.module.dao.Impl.BaseDaoImpl;
import org.lqz.module.services.SaleslistService;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName SaleslistServiceImpl
 * @Description TODO 实现销售单接口服务
 * @Author TNcarrot_Li
 * @Date 2019/6/27 14:48
 * @Version 1.0
 **/

public class SaleslistServiceImpl implements SaleslistService {

    //根据条件查找销售单
    @Override
    public Vector<Vector> selectSaleslistByCondition(Object[] paramArray) throws Exception {
        Vector<Vector> rows = new Vector<Vector>();
        BaseDaoImpl dao = new BaseDaoImpl();
        StringBuilder sqlBuilder = new StringBuilder(
                "select saleslistId, saleslistNumber, goodsName, saleslistQuantity, classificationName, warehouseName, userName," +
                        "goodsSellingPrice*saleslistQuantity, saleslistDate, " +
                        "saleslist.goodsId, saleslist.classificationId, saleslist.warehouseId, saleslist.userId "
                        + " from saleslist, user, goods, classification, warehouse "
                        + " where saleslist.userId=user.userId" +
                        " and saleslist.classificationId=classification.classificationId" +
                        " and saleslist.warehouseId=warehouse.warehouseId" +
                        " and saleslist.goodsId=goods.goodsId" +
                        " and saleslist.saleslistDeleteFlag='0'" +
                        " and goods.goodsDeleteFlag='0'" +
                        " and classification.classificationDeleteFlag='0'" +
                        " and warehouse.warehouseDeleteFlag='0'");
        String name = paramArray[0].toString().trim();
        if (!name.isEmpty()) {
            sqlBuilder.append(" and goods.goodsName like '%" + paramArray[0] + "%' ");
        }
        if (!"全部".equals(paramArray[1])) {
            sqlBuilder.append(" and saleslist.classificationId='" + paramArray[1] + "' ");
        }
        if (!"全部".equals(paramArray[2])) {
            sqlBuilder.append(" and saleslist.warehouseId='" + paramArray[2] + "' ");
        }
        if("0".equals(paramArray[4])) {
        	sqlBuilder.append(" and saleslist.userId = '" + paramArray[3] + "'");
        }
        String sql = sqlBuilder.toString();
        List<Object[]> list = dao.select(sql, 13, null);
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

    //添加销售单
    @Override
    public int insertSaleslist(Object[] paramArray) throws Exception {
        BaseDaoImpl dao = new BaseDaoImpl();
        int result = 0;
        result = dao.insert(
                "insert into saleslist(saleslistId,saleslistNumber,userId,warehouseId,classificationId,saleslistQuantity,goodsId,saleslistDate)" 
                		+ "  values(?,?,?,?,?,?,?,?)",
                paramArray);
        return result;
    }
}

