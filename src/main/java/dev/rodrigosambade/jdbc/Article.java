package dev.rodrigosambade.jdbc;
import java.math.BigDecimal;
public record Article(int id, String brand, String model, String description, BigDecimal price, BigDecimal discount, String family) {
    @Override public String toString() {
        return id + " — " + brand + " " + model;
    }
}
