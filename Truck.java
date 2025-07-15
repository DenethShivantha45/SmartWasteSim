public class Truck {
    String id;
    String wasteType; // Type of waste it can handle
    String baseLocation;
    boolean isAvailable;

    public Truck(String id, String wasteType, String baseLocation) {
        this.id = id;
        this.wasteType = wasteType;
        this.baseLocation = baseLocation;
        this.isAvailable = true;
    }
}