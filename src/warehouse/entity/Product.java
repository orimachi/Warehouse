package warehouse.entity;

public class Product {
    private String id;
    private String name;
    private String currency;
    private Double price;
    private Double inputPrice;
    private String description;
    private String nameSuppliers;

    public Product() {
    }

    public Product(String id, String name, String currency, Double price, Double inputPrice, String description, String nameSuppliers) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.price = price;
        this.inputPrice = inputPrice;
        this.description = description;
        this.nameSuppliers = nameSuppliers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(Double inputPrice) {
        this.inputPrice = inputPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameSuppliers() {
        return nameSuppliers;
    }

    public void setNameSuppliers(String nameSuppliers) {
        this.nameSuppliers = nameSuppliers;
    }
    
    
}
