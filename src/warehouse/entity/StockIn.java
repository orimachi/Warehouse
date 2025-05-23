package warehouse.entity;

import java.util.Date;
import java.util.UUID;
import warehouse.bean.ECalcUnit;
import warehouse.bean.EStatus;

public class StockIn {
    private UUID id;
    private UUID idProduct;
    private int quantity;
    private ECalcUnit calcUnit;
    private UUID idSupplier;
    private UUID idStock;
    private String idAccount;
    private Date updatedDate = new Date();
    private EStatus status;

    public StockIn() {
    }

    public StockIn(UUID id, UUID idProduct, int quantity, ECalcUnit calcUnit, UUID idSupplier, UUID idStock, String idAccount, EStatus status) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.calcUnit = calcUnit;
        this.idSupplier = idSupplier;
        this.idStock = idStock;
        this.idAccount = idAccount;
        this.status = status;
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

    public UUID getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(UUID idSupplier) {
        this.idSupplier = idSupplier;
    }

    public UUID getIdStock() {
        return idStock;
    }

    public void setIdStock(UUID idStock) {
        this.idStock = idStock;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
    
   
    
}
