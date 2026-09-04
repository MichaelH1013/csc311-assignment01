package edu.farmingdale.csc311.fleet;

/**
 * A passenger car: a Vehicle plus a door count.
 *
 * @author Michael Hickey
 */
public class Car extends Vehicle {

    /* ------------------------------------------------------------------
     * TODO-06     commit: TODO-06: implement Car
     * ------------------------------------------------------------------ */

    private int doors;

    public Car(String vin, String make, String model, int year, String color,
               int wheels, double engineSize, FuelType fuelType, double fuelCapacity, int doors) {

        super(vin, make, model, year, color, wheels, engineSize, fuelType, fuelCapacity);
        setDoors(doors);
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        if (doors < 2 || doors > 5) { // or explicit check: doors != 2 && doors != 3 && doors != 4 && doors != 5
            throw new IllegalArgumentException("doors must be 2, 3, 4, or 5: " + doors);
        }
        this.doors = doors;
    }

    @Override
    public String category() {
        return "Car";
    }

    @Override
    public double rangeInMiles() {
        return getFuelCapacity() * getFuelType().getMilesPerUnit();
    }

    @Override
    public String hornSound() {
        return "Beep beep!";
    }

    @Override
    public String toString() {
        return String.format("%s -> %s, doors=%d, range=%.1f mi",
                category(), super.toString(), doors, rangeInMiles());
    }
}