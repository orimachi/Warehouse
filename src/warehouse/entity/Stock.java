package warehouse.entity;

import java.util.UUID;
import warehouse.bean.ECalcUnit;

public class Stock {
    private UUID id;
    private UUID idProduct;
    private int quantity;
    private ECalcUnit calcUnit;
    private UUID idWarehouse;

    public Stock() {
    }
    
    public Stock(UUID id, UUID idProduct, int quantity, ECalcUnit calcUnit, UUID idWarehouse) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.calcUnit = calcUnit;
        this.idWarehouse = idWarehouse;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ECalcUnit getCalcUnit() {
        return calcUnit;
    }

    public void setCalcUnit(ECalcUnit calcUnit) {
        this.calcUnit = calcUnit;
    }

    public UUID getIdWareHouse() {
        return idWarehouse;
    }

    public void setIdWareHouse(UUID idWareHouse) {
        this.idWarehouse = idWareHouse;
    }
    
    
}
