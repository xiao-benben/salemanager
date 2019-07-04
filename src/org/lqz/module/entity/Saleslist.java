package org.lqz.module.entity;

/**
 * @ClassName Saleslist
 * @Description TODO 定义销售单类
 * @Author TNcarrot_Li
 * @Date 2019/6/24 16:06
 * @Version 1.0
 **/

public class Saleslist {

    // 定义属性
    private String saleslistId;
    private String saleslistNumber;
    private String userId;
    private String warehouseId;
    private String classificationId;
    private int saleslistQuantity;
    private String goodsId;
    private String saleslistDate;
    private int saleslistDeleteFlag;

    // 定义方法
    public void setSaleslistId(String saleslistId) {
        this.saleslistId = saleslistId;
    }

    public String getSaleslistId() {
        return saleslistId;
    }

    public void setSaleslistNumber(String saleslistNumber) {
        this.saleslistNumber = saleslistNumber;
    }

    public String getSaleslistNumber() {
        return saleslistNumber;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setSaleslistQuantity(int saleslistQuantity) {
        this.saleslistQuantity = saleslistQuantity;
    }

    public int getSaleslistQuantity() {
        return saleslistQuantity;
    }

    public void setSaleslistDate(String saleslistDate) {
        this.saleslistDate = saleslistDate;
    }

    public String getSaleslistDate() {
        return saleslistDate;
    }

    public void setSaleslistDeleteFlag(int saleslistDeleteFlag) {
        this.saleslistDeleteFlag = saleslistDeleteFlag;
    }

    public int getSaleslistDeleteFlag() {
        return saleslistDeleteFlag;
    }
}

