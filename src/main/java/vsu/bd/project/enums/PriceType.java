package vsu.bd.project.enums;

import lombok.Getter;

@Getter
public enum PriceType {

    PURCHASING("Purchasing"), SELLING("Selling");

    private final String displayValue;

    PriceType(String displayValue) {
        this.displayValue = displayValue;
    }
}
