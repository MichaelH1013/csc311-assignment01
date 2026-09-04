package edu.farmingdale.csc311.fleet;

/**
 * A named group of vehicles stored in a plain array.
 * No ArrayList, no HashMap. Arrays and loops only.
 *
 * @author Michael Hickey
 */
public class Fleet {

    public static final int MAX_VEHICLES = 25;

    /* ------------------------------------------------------------------
     * TODO-08     commit: TODO-08: implement Fleet storage
     * ------------------------------------------------------------------ */

    private final String name;
    private final Vehicle[] vehicles;
    private int count;

    public Fleet(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Fleet name cannot be null or blank: " + name);
        }
        this.name = name.trim();
        this.vehicles = new Vehicle[MAX_VEHICLES];
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public boolean contains(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (vehicles[i].equals(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (count >= MAX_VEHICLES) {
            return false;
        }
        if (contains(vehicle)) {
            return false;
        }
        vehicles[count] = vehicle;
        count++;
        return true;
    }

    public boolean removeByVin(String vin) {
        if (vin == null || vin.isBlank()) {
            return false;
        }
        String trimmedVin = vin.trim();
        int indexToRemove = -1;
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getVin().equalsIgnoreCase(trimmedVin)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            return false;
        }

        // Shift elements left
        for (int i = indexToRemove; i < count - 1; i++) {
            vehicles[i] = vehicles[i + 1];
        }
        vehicles[count - 1] = null;
        count--;
        return true;
    }

    public Vehicle findByVin(String vin) {
        if (vin == null || vin.isBlank()) {
            return null;
        }
        String trimmedVin = vin.trim();
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getVin().equalsIgnoreCase(trimmedVin)) {
                return vehicles[i];
            }
        }
        return null;
    }

    public int size() {
        return count;
    }

    public Vehicle[] toArray() {
        Vehicle[] result = new Vehicle[count];
        for (int i = 0; i < count; i++) {
            result[i] = vehicles[i];
        }
        return result;
    }

    /* ------------------------------------------------------------------
     * TODO-09     commit: TODO-09: implement Fleet reports
     * ------------------------------------------------------------------ */

    public Vehicle[] sortedByYear() {
        Vehicle[] copy = toArray();

        // Insertion sort ordered by year (oldest first), then make A to Z ignoring case
        for (int i = 1; i < copy.length; i++) {
            Vehicle key = copy[i];
            int j = i - 1;
            while (j >= 0) {
                if (copy[j].getYear() > key.getYear()) {
                    copy[j + 1] = copy[j];
                    j--;
                } else if (copy[j].getYear() == key.getYear()) {
                    if (copy[j].getMake().compareToIgnoreCase(key.getMake()) > 0) {
                        copy[j + 1] = copy[j];
                        j--;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            copy[j + 1] = key;
        }
        return copy;
    }

    public int countWithFuelType(FuelType fuel) {
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getFuelType() == fuel) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public double averageEngineSize() {
        double totalEngineSize = 0.0;
        int engineCount = 0;
        for (int i = 0; i < count; i++) {
            if (vehicles[i].getFuelType().hasEngine()) {
                totalEngineSize += vehicles[i].getEngineSize();
                engineCount++;
            }
        }
        if (engineCount == 0) {
            return 0.0;
        }
        return totalEngineSize / engineCount;
    }

    public Vehicle longestRange() {
        if (count == 0) {
            return null;
        }
        Vehicle longest = vehicles[0];
        double maxRange = longest.rangeInMiles();

        for (int i = 1; i < count; i++) {
            double currentRange = vehicles[i].rangeInMiles();
            if (currentRange > maxRange) {
                maxRange = currentRange;
                longest = vehicles[i];
            }
        }
        return longest;
    }
}