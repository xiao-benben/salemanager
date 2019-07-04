package org.lqz.module.entity;

/**
 * @ClassName User
 * @Description TODO 定义用户类
 * @Author TNcarrot_Li
 * @Date 2019/6/24 15:37
 * @Version 1.0
 **/

public class User {
    // 定义属性
    private String userId;
    private String userName;
    private String userPassword;
    private int userIdentity;
    private int userDeleteFlag;

    // 定义方法
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserIdentity(int userIdentity) {
        this.userIdentity = userIdentity;
    }

    public int getUserIdentity() {
        return userIdentity;
    }

    public void setUserDeleteFlag(int userDeleteFlag) {
        this.userDeleteFlag = userDeleteFlag;
    }

    public int getUserDeleteFlag() {
        return userDeleteFlag;
    }
}
