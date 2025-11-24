package one.digitalinnovation.beerstock.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import one.digitalinnovation.beerstock.enums.BeerType;

public class BeerDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    private String brand;

    @NotNull
    private BeerType type;

    @Min(0)
    private Integer quantity;

    @Max(100)
    private Integer max;

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public BeerType getType() { return type; }
    public void setType(BeerType type) { this.type = type; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getMax() { return max; }
    public void setMax(Integer max) { this.max = max; }
}
