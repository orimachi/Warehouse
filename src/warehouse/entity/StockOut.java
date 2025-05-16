package warehouse.entity;

import java.util.Date;
import java.util.UUID;
import warehouse.bean.ECalcUnit;

public class StockOut {
    private UUID id;
    private Date addDate = new Date();
    private int quantity;
    private ECalcUnit calcUnit;
    private UUID idSupplier;
    private UUID idWarehouse;
    private UUID idAccount;

    public StockOut() {
    }

    public StockOut(UUID id, int quantity, ECalcUnit calcUnit, UUID idSupplier, UUID idWarehouse, UUID idAccount) {
        this.id = id;
        this.quantity = quantity;
        this.calcUnit = calcUnit;
        this.idSupplier = idSupplier;
        this.idWarehouse = idWarehouse;
        this.idAccount = idAccount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
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

    public UUID getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(UUID idSupplier) {
        this.idSupplier = idSupplier;
    }

    public UUID getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(UUID idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public UUID getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(UUID idAccount) {
        this.idAccount = idAccount;
    }
    
    
}
