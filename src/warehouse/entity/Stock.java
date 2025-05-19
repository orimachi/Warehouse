package warehouse.entity;

import java.util.Date;
import java.util.UUID;
import warehouse.bean.ECalcUnit;

public class Stock {
    private UUID id;
    private UUID idProduct;
    private int quantity;
    private ECalcUnit calcUnit;
    private UUID idWarehouse;
    private Date lastUpdate = new Date();
    private UUID idAccount;

    public Stock() {
    }
    
    public Stock(UUID id, UUID idProduct, int quantity, ECalcUnit calcUnit, UUID idWarehouse, UUID idAccount) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.calcUnit = calcUnit;
        this.idWarehouse = idWarehouse;
        this.idAccount = idAccount;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public UUID getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(UUID idAccount) {
        this.idAccount = idAccount;
    }
    
    
}
