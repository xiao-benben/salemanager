package org.lqz.module.services.Impl;

import java.util.List;
import java.util.Vector;

import org.lqz.module.dao.Impl.BaseDaoImpl;
import org.lqz.module.services.EntrylistService;
//import org.lqz.module.services.StockOrderService;
import org.lqz.module.*;
public class EntrylistServiceImpl implements EntrylistService {
	
	//条件查询入库单
	@Override
	public Vector<Vector> selectEntrylistByCondition(Object[] paraArray) throws Exception {
		Vector<Vector> rows = new Vector<Vector>();
		BaseDaoImpl dao = new BaseDaoImpl();
		/*StringBuilder sqlBuilder = new StringBuilder(
				"select s.id,s.bill_no,g.name,s.amount,c.name,w.name,u.name,c.id,w.id "
						+ " from stock_order s,goods g,user u,category c,warehouse w "
						+ " where s.handler_id=u.id and s.goods_id=g.id and s.category_id=c.id and s.warehouse_id=w.id and s.sign='0' and s.del_flag='0' and g.del_flag='0' and c.del_flag=0 and w.del_flag='0' ");
	*/
		StringBuilder sqlBuilder = new StringBuilder(
				"select entrylistId, e.entrylistNumber, g.goodsName, e.entrylistQuantity, e.entrylistUnitPrice,  c.classificationName, w.warehouseName , u.userName , e.entrylistDate"
						+ " from entrylist e, goods g, classification c, warehouse w, user u "
						+ " where u.userId = e.userId and w.warehouseId = e.warehouseId and g.goodsId = e.goodsId and c.classificationId = e.classificationId and e.entrylistDeleteFlag = '0' and g.goodsDeleteFlag = '0' and c.classificationDeleteFlag = '0' and w.warehouseDeleteFlag = '0' and u.userDeleteFlag = '0' ");
	
		String name = paraArray[0].toString().trim();
		if (!name.isEmpty()) {
			sqlBuilder.append(" and g.goodsName like '%" + paraArray[0] + "%' ");
		}
		if (!"全部".equals(paraArray[1])) {
			sqlBuilder.append(" and c.classificationId='" + paraArray[1] + "' ");
		}
		if (!"全部".equals(paraArray[2])) {
			sqlBuilder.append(" and w.warehouseId='" + paraArray[2] + "' ");
		}
		if (!"全部".equals(paraArray[3])) {
			sqlBuilder.append(" and e.userId='" + paraArray[3] + "' ");
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
	
	//通过id逻辑删除入库单
	@Override
	public int deleteEntrylistById(Object[] paraArray) throws Exception {
		BaseDaoImpl dao = new BaseDaoImpl();
		int result = 0;
		result = dao.update("update entrylist set entrylistDeleteFlag='1' where entrylistId=?", paraArray);
		return result;
	}
	
	//通过id修改入库单
	@Override
	public int updateEntrylistById(Object[] paraArray) throws Exception {
		BaseDaoImpl dao = new BaseDaoImpl();
		int result = 0;
		result = dao.update("update entrylist set entrylistQuantity=? where entrylistId=?", paraArray);
		return result;
	}
	
	//插入入库单
	@Override
	public int insertEntrylist(Object[] paraArray) throws Exception {
		BaseDaoImpl dao = new BaseDaoImpl();
		int result = 0;
		result = dao.insert(
				"insert into entrylist(entrylistId,entrylistNumber,userId,warehouseId,classificationId,goodsId,entrylistQuantity,entrylistUnitPrice,entrylistDate,entrylistDeleteFlag) "
				+ " values(?,?,?,?,?,?,?,?,?,'0')", paraArray);
		return result;
	}
	
	// 通过商品ID查询进价
    public List selectPriceById(Object[] paramArray) throws Exception {
    	BaseDaoImpl dao = new BaseDaoImpl();
    	StringBuilder sqlBuilder = new StringBuilder("select distinct entrylistUnitPrice from entrylist where goodsId=?");
    	String sql = sqlBuilder.toString();
        List<Object[]> list = dao.select(sql, 1, paramArray);
        return list;
    }
}
