package warehouse.entity;

import java.util.Date;
import java.util.UUID;
import warehouse.bean.EStatus;

public class StockOut {
    private UUID id;
    private int quantity;
    private UUID idProduct;
    private String username;
    private Date updatedDate = new Date();
    private EStatus status;

    public StockOut() {
    }

    public StockOut(UUID id, int quantity, UUID idProduct, String username, EStatus status) {
        this.id = id;
        this.quantity = quantity;
        this.idProduct = idProduct;
        this.username = username;
        this.status = status;
    }

    
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
