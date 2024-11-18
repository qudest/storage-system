package vsu.bd.project.enums;

import lombok.Getter;

@Getter
public enum OperationType {

    ARRIVAL("Arrival"), WRITE_OFF("Write off"), DELIVERY("Delivery");

    private final String displayValue;

    OperationType(String displayValue) {
        this.displayValue = displayValue;
    }
}
