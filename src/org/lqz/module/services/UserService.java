package org.lqz.module.services;


import org.lqz.module.entity.User;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName UserService
 * @Description TODO 定义用户服务接口
 * @Author TNcarrot_Li
 * @Date 2019/6/25 14:23
 * @Version 1.0
 **/

public interface UserService {

    // 通过用户名和密码查询用户
    public User selectOne(Object[] paramArray) throws Exception;

    //通过用户ID修改用户信息
    public int updateUserById(Object[] paramArray) throws Exception;

    // 根据用户Id添加用户
  //  public int insertById(Object[] paramArray) throws Exception;
    public int insertUser(Object[] paraArray) throws Exception;
    // 根据用户Id删除用户
    public int deleteUserByName(Object[] paraArray) throws Exception;
  //  public int deleteById(Object[] paramArray) throws Exception;

    // 查询所有用户,返回一个用户列表
  //  public List<User> selectAll() throws Exception;
    public List selectAll() throws Exception;
    //条件查找用户
    public Vector<Vector> selectByCondition(Object[] paramArray) throws Exception ;
    //计算利润
    public Vector<Vector> selectProfit(Object[] paramArray) throws Exception;
    
}