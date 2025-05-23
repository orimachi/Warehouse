package warehouse.entity;

import java.util.Date;
import java.util.UUID;
import warehouse.bean.EStatus;

public class StockIn {
    private UUID id;
    private UUID idProduct;
    private int quantity;
    private UUID idSupplier;
    private UUID idWareHouse;
    private String username;
    private Date updatedDate = new Date();
    private EStatus status;

    public StockIn() {
    }

    public StockIn(UUID id, UUID idProduct, int quantity, UUID idSupplier, UUID idWareHouse, String username, EStatus status) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.idSupplier = idSupplier;
        this.idWareHouse = idWareHouse;
        this.username = username;
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

    public UUID getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(UUID idSupplier) {
        this.idSupplier = idSupplier;
    }

    public UUID getIdWarehouse() {
        return idWareHouse;
    }

    public void setIdWarehouse(UUID idWareHouse) {
        this.idWareHouse = idWareHouse;
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
