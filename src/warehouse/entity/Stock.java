package warehouse.entity;

import java.util.UUID;

public class Stock {
    private UUID id;
    private UUID idProduct;
    private int quantity;
    private UUID idWarehouse;

    public Stock() {
    }
    
    public Stock(UUID id, UUID idProduct, int quantity, UUID idWarehouse) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
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

    public UUID getIdWareHouse() {
        return idWarehouse;
    }

    public void setIdWareHouse(UUID idWareHouse) {
        this.idWarehouse = idWareHouse;
    }

}
