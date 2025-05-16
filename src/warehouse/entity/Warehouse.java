package warehouse.entity;

import java.util.UUID;

public class Warehouse {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private UUID idStock;

    public Warehouse() {
    }

    public Warehouse(UUID id, String name, String address, String phoneNumber, UUID idStock) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.idStock = idStock;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getIdStock() {
        return idStock;
    }

    public void setIdStock(UUID idStock) {
        this.idStock = idStock;
    }
    
    
}
