package org.lqz.module.services.Impl;

import java.util.List;
import java.util.Vector;

import org.lqz.module.dao.BaseDao;
import org.lqz.module.dao.Impl.BaseDaoImpl;
import org.lqz.module.entity.User;
import org.lqz.module.services.UserService;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

public class UserServiceImpl implements UserService {
	
	// 实现按照条件查找
    @Override
    public Vector<Vector> selectByCondition(Object[] paramArray) throws Exception {
        Vector<Vector> rows = new Vector<Vector>();
        BaseDaoImpl dao = new BaseDaoImpl();
        StringBuilder sqlBuilder = new StringBuilder(
                "select user.userId, userName, saleslistId, saleslistDate, goods.goodsId, sum(saleslistQuantity*goodsSellingPrice)" +
                        "from goods, user, saleslist " +
                        "where userDeleteFlag='0' and " +
                        "saleslistDeleteFlag='0' and " +
                        "goodsDeleteFlag='0' and " +
                        "saleslist.userId=user.userId and " +
                        "saleslist.goodsId=goods.goodsId group by saleslistDate,userId"
        );
        /*System.out.println("paramArray[0] = " + paramArray[0] + ", paramArray[1] = " + paramArray[1]);
        if (!"全部".equals(paramArray[0])) {
            sqlBuilder.append(" and goods.classificationId='" + paramArray[0] + "'");
        }
        if (!"全部".equals(paramArray[1])) {
            sqlBuilder.append(" and goods.warehouseId='" + paramArray[1] + "'");
        }*/
        String sql = sqlBuilder.toString();
        List<Object[]> list = dao.select(sql, 6, null);
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
    
    
 // 计算利润
    @Override
    public Vector<Vector> selectProfit(Object[] paramArray) throws Exception {
        Vector<Vector> rows = new Vector<Vector>();
        BaseDaoImpl dao = new BaseDaoImpl();
        StringBuilder sqlBuilder = new StringBuilder(
                "select saleslistDate, sum(saleslistQuantity*(goodsSellingPrice-entrylistUnitPrice)/2)" +
                        "from goods, entrylist, saleslist " +
                        "where entrylistDeleteFlag='0' and " +
                        "saleslistDeleteFlag='0' and " +
                        "goodsDeleteFlag='0' and " +
                        "saleslist.goodsId=entrylist.goodsId and " +
                        "saleslist.goodsId=goods.goodsId group by saleslistDate"
        );
        /*System.out.println("paramArray[0] = " + paramArray[0] + ", paramArray[1] = " + paramArray[1]);
        if (!"全部".equals(paramArray[0])) {
            sqlBuilder.append(" and goods.classificationId='" + paramArray[0] + "'");
        }
        if (!"全部".equals(paramArray[1])) {
            sqlBuilder.append(" and goods.warehouseId='" + paramArray[1] + "'");
        }*/
        String sql = sqlBuilder.toString();
        List<Object[]> list = dao.select(sql, 2, null);
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
    
	//查询所有用户名
	@Override
	public List selectAll() throws Exception {
		BaseDaoImpl dao = new BaseDaoImpl();
		List list = dao.select("select userId, userName from user where userDeleteFlag = '0'", 2, null);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}
		
	//查询一条记录
	@Override
	public User selectOne(Object[] paraArray) throws Exception {
		User user = new User();
		BaseDaoImpl dao = new BaseDaoImpl();
		String sql = "select userId,userName,userPassword,userIdentity from user where userName=? and userPassword=?";
		List list = dao.select(sql, 4, paraArray);
		if (!list.isEmpty()) {
			user.setUserId((String) ((Object[]) list.get(0))[0]);
			user.setUserName((String) ((Object[]) list.get(0))[1]);
			user.setUserPassword((String) ((Object[]) list.get(0))[2]);
			user.setUserIdentity((int) ((Object[]) list.get(0))[3]);
			return user;
		}
		return null;
	}
	
	//通过Id修改用户
	@Override
	public int updateUserById(Object[] paraArray) throws Exception {
		int result = 0;
		BaseDaoImpl dao = new BaseDaoImpl();
		String sql = "update user set userName = ?,userPassword=? where userId=?";
		result = dao.update(sql, paraArray);
		return result;
	}
	
	//通过用户名删除用户
	public int deleteUserByName(Object[] paraArray) throws Exception {
		int result = 0;
		BaseDaoImpl dao = new BaseDaoImpl();
		String sql = "update user set userDeleteFlag = '1' where userName = ?";
		result = dao.update(sql, paraArray);
		return result;
	}
	
	//插入用户信息
	public int insertUser(Object[] paraArray) throws Exception{
		int result = 0;
		BaseDaoImpl dao = new BaseDaoImpl();
		String sql = "insert into user(userId, userName, userPassword, userIdentity) values(?,?,?,?)";
		result = dao.insert(sql, paraArray);
		return result;
	}
}
