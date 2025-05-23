package warehouse.entity;

import java.util.Date;
import java.util.UUID;
import warehouse.bean.EStatus;

public class StockOut {
    private UUID id;
    private int quantity;
    private UUID idProduct;
    private UUID idStock;
    private UUID idAccount;
    private Date addDate = new Date();
    private EStatus status;

    public StockOut() {
    }

    public StockOut(UUID id, int quantity, UUID idProduct, UUID idStock, UUID idAccount, EStatus status) {
        this.id = id;
        this.quantity = quantity;
        this.idProduct = idProduct;
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

    public UUID getIdStock() {
        return idStock;
    }

    public void setIdStock(UUID idStock) {
        this.idStock = idStock;
    }

    public UUID getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(UUID idAccount) {
        this.idAccount = idAccount;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
}
