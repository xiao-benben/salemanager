package org.lqz.module.entity;

/**
 * @ClassName Goods
 * @Description TODO 定义商品类
 * @Author TNcarrot_Li
 * @Date 2019/6/24 15:43
 * @Version 1.0
 **/

public class Goods {

    // 定义属性
    private String goodsId;
    private String goodsName;
    private double goodsSellingPrice;
    private String goodsOrigin;
    private int goodsInventory;
    private String warehouseId;
    private String classificationId;
    private int goodsDeleteFlag;

    // 定义方法
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsSellingPrice(double goodsSellingPrice) {
        this.goodsSellingPrice = goodsSellingPrice;
    }

    public double getGoodsSellingPrice() {
        return goodsSellingPrice;
    }

    public void setGoodsInventory(int goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    public int getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsOrigin(String goodsOrigin) {
        this.goodsOrigin = goodsOrigin;
    }

    public String getGoodsOrigin() {
        return goodsOrigin;
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

    public void setGoodsDeleteFlag(int goodsDeleteFlag) {
        this.goodsDeleteFlag = goodsDeleteFlag;
    }

    public int getGoodsDeleteFlag() {
        return goodsDeleteFlag;
    }
}
