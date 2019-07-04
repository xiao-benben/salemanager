package org.lqz.module.entity;

/*
 *@Type&Name //TODO 商品分类类 & Classification
 *@Author TNcarrot_Li
 *@Date 16:07 2019/6/20
 *@Version 1.0
 *@Description //TODO 定义 Category 类的基本属性，及其设置、获取方法
 *@Other //TODO 实体定义
 **/


public class Classification {

    // 定义基本属性
    private String classificationId;
    private String classificationName;
    private int classificationDeleteFlag;

    // 定义基本方法

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassificationId(){
        return classificationId;
    }

    public void setClassificationName(String classificationName){
        this.classificationName = classificationName;
    }

    public String getClassificationName(){
        return classificationName;
    }

    public void setClassificationDeleteFlag(int classificationDeleteFlag) {
        this.classificationDeleteFlag = classificationDeleteFlag;
    }

    public int getClassificationDeleteFlag(){
        return this.classificationDeleteFlag;
    }
}
