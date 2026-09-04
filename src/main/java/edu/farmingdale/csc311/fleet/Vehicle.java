package edu.farmingdale.csc311.fleet;

/**
 * Base class for everything the motor pool owns. Abstract on purpose:
 * the fleet holds cars and trucks, never a plain "vehicle".
 *
 * @author Michael Hickey
 */
public abstract class Vehicle implements Honkable {

    /* ------------------------------------------------------------------
     * TODO-02     commit: TODO-02: add Vehicle fields and constructor
     * ------------------------------------------------------------------ */

    private final String vin;
    private final String make;
    private final String model;
    private String color;
    private int year;
    private int wheels;
    private final double engineSize;
    private final FuelType fuelType;
    private double fuelCapacity;

    protected Vehicle(String vin, String make, String model, int year, String color,
                      int wheels, double engineSize, FuelType fuelType, double fuelCapacity) {

        // Validate VIN
        if (vin == null) {
            throw new IllegalArgumentException("vin cannot be null: " + vin);
        }
        String trimmedVin = vin.trim();
        if (trimmedVin.length() != 17) {
            throw new IllegalArgumentException("vin must be exactly 17 characters: " + vin);
        }
        this.vin = trimmedVin.toUpperCase();

        // Validate Make, Model using helper
        this.make = validateStringField(make, "make");
        this.model = validateStringField(model, "model");

        // Validate FuelType
        if (fuelType == null) {
            throw new IllegalArgumentException("fuelType cannot be null");
        }
        this.fuelType = fuelType;

        // Validate Engine Size
        if (fuelType.hasEngine()) {
            if (engineSize <= 0.0 || engineSize > 8.5) {
                throw new IllegalArgumentException("engineSize must be above 0.0 and at most 8.5: " + engineSize);
            }
        } else {
            if (engineSize != 0.0) {
                throw new IllegalArgumentException("engineSize must be exactly 0.0 for electric vehicles: " + engineSize);
            }
        }
        this.engineSize = engineSize;

        // Call setters for fields with validation rules
        setYear(year);
        setColor(color);
        setWheels(wheels);
        setFuelCapacity(fuelCapacity);
    }

    /**
     * Private helper to validate string fields: not null, not blank, stored trimmed.
     */
    private static String validateStringField(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank: " + value);
        }
        return value.trim();
    }

    /* ------------------------------------------------------------------
     * TODO-03     commit: TODO-03: add Vehicle getters and setters
     * ------------------------------------------------------------------ */

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1900 || year > 2100) {
            throw new IllegalArgumentException("year must be between 1900 and 2100: " + year);
        }
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = validateStringField(color, "color");
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        if (wheels < 2 || wheels > 18) {
            throw new IllegalArgumentException("wheels must be between 2 and 18: " + wheels);
        }
        this.wheels = wheels;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        if (fuelCapacity <= 0.0) {
            throw new IllegalArgumentException("fuelCapacity must be above 0.0: " + fuelCapacity);
        }
        this.fuelCapacity = fuelCapacity;
    }

    /* ------------------------------------------------------------------
     * TODO-04     commit: TODO-04: implement honk methods from Honkable
     * ------------------------------------------------------------------ */

    @Override
    public void honk() {
        System.out.println(hornSound());
    }

    @Override
    public void honk(int times) {
        if (times < 1) {
            throw new IllegalArgumentException("times must be at least 1: " + times);
        }
        for (int i = 0; i < times; i++) {
            System.out.println(hornSound());
        }
    }

    /** Subclasses answer these three. */
    public abstract String hornSound();

    public abstract String category();

    public abstract double rangeInMiles();

    /* ------------------------------------------------------------------
     * TODO-05     commit: TODO-05: add toString, equals and hashCode
     * ------------------------------------------------------------------ */

    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO-05");
    }

    @Override
    public boolean equals(Object other) {
        throw new UnsupportedOperationException("TODO-05");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("TODO-05");
    }
}