package warehouse.entity;

import java.util.UUID;
import warehouse.bean.ECalcUnit;
import warehouse.bean.ECategory;

public class Product {
    private String id;
    private String name;
    private ECalcUnit calcUnit;
    private String description;
    private UUID idSupplier;
    private ECategory category;

    public Product() {
    }

    public Product(String id, String name, ECalcUnit calcUnit, String description, UUID idSupplier, ECategory category) {
        this.id = id;
        this.name = name;
        this.calcUnit = calcUnit;
        this.description = description;
        this.idSupplier = idSupplier;
        this.category = category;
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

    public ECalcUnit getCalcUnit() {
        return calcUnit;
    }

    public void setCalcUnit(ECalcUnit calcUnit) {
        this.calcUnit = calcUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(UUID idSupplier) {
        this.idSupplier = idSupplier;
    }

    public ECategory getCategory() {
        return category;
    }

    public void setCategory(ECategory category) {
        this.category = category;
    }

}
