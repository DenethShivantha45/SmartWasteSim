public class TruckHashTable {
    private Truck[] table;
    private int capacity;

    public TruckHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Truck[capacity];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void addTruck(Truck truck) {
        int index = hash(truck.wasteType + truck.baseLocation);
        while (table[index] != null) {
            index = (index + 1) % capacity; // Linear probing
        }
        table[index] = truck;
    }

    public Truck findTruck(String wasteType, String location) {
        int index = hash(wasteType + location);
        int startIndex = index;
        do {
            if (table[index] != null && table[index].wasteType.equals(wasteType) &&
                table[index].baseLocation.equals(location) && table[index].isAvailable) {
                return table[index];
            }
            index = (index + 1) % capacity;
        } while (index != startIndex && table[index] != null);
        return null;
    }
}
