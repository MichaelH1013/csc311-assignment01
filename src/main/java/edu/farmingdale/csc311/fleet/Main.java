package edu.farmingdale.csc311.fleet;

/**
 * Driver. This is the only class that prints a report.
 *
 * @author Michael Hickey
 */
public class Main {

    /* ------------------------------------------------------------------
     * TODO-10     commit: TODO-10: build the fleet demo in Main
     * ------------------------------------------------------------------ */

    public static void main(String[] args) {
        Fleet fleet = buildFleet();
        printInventory(fleet);
        printSoundCheck(fleet);
        printReport(fleet);
        runGuardRails();
    }

    private static Fleet buildFleet() {
        Fleet fleet = new Fleet("Farmingdale Motor Pool");

        fleet.add(new Car("1HGCM82633A004352", "Honda", "Accord", 2023, "Blue", 4, 2.0, FuelType.GASOLINE, 15.8, 4));
        fleet.add(new Car("5YJ3E1EA7PF123456", "Tesla", "Model 3", 2024, "Red", 4, 0.0, FuelType.ELECTRIC, 75.0, 4));
        fleet.add(new Car("JTDKARFU2J3061234", "Toyota", "Prius", 2020, "Silver", 4, 1.8, FuelType.HYBRID, 11.3, 5));
        fleet.add(new Truck("1FT8W3BT5MEC12345", "Ford", "F-350", 2021, "White", 6, 6.7, FuelType.DIESEL, 40.0, 3500.0));
        fleet.add(new Truck("3C6UR5DL9JG123456", "Ram", "2500", 2019, "Black", 4, 6.4, FuelType.GASOLINE, 31.0, 1800.0));

        return fleet;
    }

    private static void printInventory(Fleet fleet) {
        for (Vehicle v : fleet.sortedByYear()) {
            System.out.println(v);
        }
    }

    private static void printSoundCheck(Fleet fleet) {
        for (Honkable h : fleet.sortedByYear()) {
            h.honk();
        }
        Vehicle accord = fleet.findByVin("1HGCM82633A004352");
        if (accord != null) {
            accord.honk(3);
        }
    }

    private static void printReport(Fleet fleet) {
        System.out.printf("%-20s: %d%n", "Vehicle Count", fleet.size());
        System.out.printf("%-20s: %.1f L%n", "Average Engine Size", fleet.averageEngineSize());

        Vehicle longest = fleet.longestRange();
        if (longest != null) {
            System.out.printf("%-20s: %d %s %s (%.1f mi)%n",
                    "Longest Range", longest.getYear(), longest.getMake(), longest.getModel(), longest.rangeInMiles());
        }

        for (FuelType fuel : FuelType.values()) {
            System.out.printf("  %-9s: %d%n", fuel.getLabel(), fleet.countWithFuelType(fuel));
        }
    }

    private static void runGuardRails() {
        // a, b, c guard rails using specified format
        Fleet testFleet = new Fleet("Test Fleet");
        Car accord = new Car("1HGCM82633A004352", "Honda", "Accord", 2023, "Blue", 4, 2.0, FuelType.GASOLINE, 15.8, 4);
        Car prius = new Car("JTDKARFU2J3061234", "Toyota", "Prius", 2020, "Silver", 4, 1.8, FuelType.HYBRID, 11.3, 5);

        testFleet.add(accord);

        boolean addedAgain = testFleet.add(accord);
        System.out.printf("%-23s: %s%n", "Add duplicate", addedAgain);

        testFleet.add(prius);
        boolean removed = testFleet.removeByVin("JTDKARFU2J3061234");
        System.out.printf("%-23s: %s%n", "Remove Prius", removed);

        System.out.printf("%-23s: %s%n", "Final size", testFleet.size());

        // d, e, f separate try/catch blocks catching IllegalArgumentException
        try {
            new Car("1HGCM82633A004352", "Honda", "Accord", 2023, "Blue", 4, 2.0, FuelType.ELECTRIC, 15.8, 4);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        try {
            FuelType.fromLabel("Steam");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        try {
            accord.honk(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}