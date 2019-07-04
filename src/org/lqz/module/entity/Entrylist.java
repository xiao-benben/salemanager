package org.lqz.module.entity;

/**
 * @ClassName Entrylist
 * @Description TODO 定义进货清单类
 * @Author HP
 * @Date 2019/6/24 15:52
 * @Version 1.0
 **/

public class Entrylist {

    // 定义属性
    private String entrylistId;
    private String entrylistNumber;
    private String userId;
    private String warehouseId;
    private String classificationId;
    private String goodsId;
    private int entrylistQuantity;
    private double entrylistUnitPrice;
    private String entrylistDate;
    private int entrylistDeleteFlag;

    // 定义方法

    public void setEntrylistId(String entrylistId) {
        this.entrylistId = entrylistId;
    }

    public String getEntrylistId() {
        return entrylistId;
    }

    public void setEntrylistNumber(String entrylistNumber) {
        this.entrylistNumber = entrylistNumber;
    }

    public String getEntrylistNumber() {
        return entrylistNumber;
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

    public void setEntrylistQuantity(int entrylistQuantity) {
        this.entrylistQuantity = entrylistQuantity;
    }

    public int getEntrylistQuantity() {
        return entrylistQuantity;
    }

    public void setEntrylistUnitPrice(double entrylistUnitPrice) {
        this.entrylistUnitPrice = entrylistUnitPrice;
    }

    public double getEntrylistUnitPrice() {
        return entrylistUnitPrice;
    }

    public void setEntrylistDate(String entrylistDate) {
        this.entrylistDate = entrylistDate;
    }

    public String getEntrylistDate() {
        return entrylistDate;
    }

    public void setEntrylistDeleteFlag(int entrylistDeleteFlag) {
        this.entrylistDeleteFlag = entrylistDeleteFlag;
    }

    public int getEntrylistDeleteFlag() {
        return entrylistDeleteFlag;
    }
}
