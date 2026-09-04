package edu.farmingdale.csc311.fleet;

/**
 * A work truck. Its range drops as its payload rating goes up, so it
 * computes range differently from a Car.
 *
 * @author Michael Hickey
 */
public class Truck extends Vehicle {

    /* ------------------------------------------------------------------
     * TODO-07     commit: TODO-07: implement Truck
     * ------------------------------------------------------------------ */

    private double payloadKg;

    public Truck(String vin, String make, String model, int year, String color,
                 int wheels, double engineSize, FuelType fuelType, double fuelCapacity, double payloadKg) {

        super(vin, make, model, year, color, wheels, engineSize, fuelType, fuelCapacity);
        setPayloadKg(payloadKg);
    }

    public double getPayloadKg() {
        return payloadKg;
    }

    public void setPayloadKg(double payloadKg) {
        if (payloadKg <= 0.0 || payloadKg > 20000.0) {
            throw new IllegalArgumentException("payloadKg must be above 0.0 and at most 20000.0: " + payloadKg);
        }
        this.payloadKg = payloadKg;
    }

    @Override
    public String category() {
        return "Truck";
    }

    @Override
    public String hornSound() {
        return "HOOOONK!";
    }

    @Override
    public void honk() {
        honk(2);
    }

    @Override
    public double rangeInMiles() {
        double loadFactor = 1.0 - Math.min(0.35, payloadKg / 20000.0);
        return getFuelCapacity() * getFuelType().getMilesPerUnit() * loadFactor;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s, payload=%.1f kg, range=%.1f mi",
                category(), super.toString(), payloadKg, rangeInMiles());
    }
}
