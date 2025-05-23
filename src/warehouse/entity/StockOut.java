package warehouse.entity;

import java.util.Date;
import java.util.UUID;

public class StockOut {
    private UUID id;
    private Date addDate = new Date();
    private int quantity;
    private UUID idProduct;
    private UUID idWarehouse;
    private String idAccount;

    public StockOut() {
    }

    public StockOut(UUID id, int quantity, UUID idProduct, UUID idWarehouse, String idAccount) {
        this.id = id;
        this.quantity = quantity;
        this.idProduct = idProduct;
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

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public UUID getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(UUID idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }
    
    
}
