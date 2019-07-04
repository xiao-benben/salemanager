package org.lqz.module.entity;

/**
 * @ClassName Warehouse
 * @Description TODO 定义仓库类
 * @Author TNcarrot_Li
 * @Date 2019/6/24 15:32
 * @Version 1.0
 **/

public class Warehouse {
    // 定义属性
    private String warehouseId;
    private String warehouseName;
    private String warehousePosition;
    private int warehouseDeleteFlag;

    // 定义方法
    public void setWarehouseId(String warehouseId){
        this.warehouseId = warehouseId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehousePosition(String warehousePosition) {
        this.warehousePosition = warehousePosition;
    }

    public String getWarehousePosition() {
        return warehousePosition;
    }

    public void setWarehouseDeleteFlag(int warehouseDeleteFlag) {
        this.warehouseDeleteFlag = warehouseDeleteFlag;
    }

    public int getWarehouseDeleteFlag() {
        return warehouseDeleteFlag;
    }
}
