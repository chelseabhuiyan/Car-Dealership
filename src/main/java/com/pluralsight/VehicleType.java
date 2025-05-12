/** Enum used in vehicletype **/

package com.pluralsight;

public enum VehicleType {
    CAR, TRUCK, SUV, VAN;

    public static VehicleType fromString(String type) {
        return VehicleType.valueOf(type.toUpperCase());
    }
}
