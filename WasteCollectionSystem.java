import java.util.List;

public class WasteCollectionSystem {
    private MaxHeap alertHeap;
    private TruckHashTable truckTable;
    private Graph cityGraph;

    public WasteCollectionSystem(int maxAlerts, int maxTrucks) {
        alertHeap = new MaxHeap(maxAlerts);
        truckTable = new TruckHashTable(maxTrucks);
        cityGraph = new Graph();
    }

    public void addBinAlert(String location, int fillLevel, String wasteType, long alertTime) {
        alertHeap.insert(new BinAlert(location, fillLevel, wasteType, alertTime));
    }

    public void addTruck(String id, String wasteType, String baseLocation) {
        truckTable.addTruck(new Truck(id, wasteType, baseLocation));
    }

    public void addCityRoad(String from, String to, int distance) {
        cityGraph.addEdge(from, to, distance);
    }

    public String processNextAlert() {
        BinAlert alert = alertHeap.extractMax();
        if (alert == null) return "No alerts to process.";

        Truck truck = truckTable.findTruck(alert.wasteType, alert.location);
        if (truck == null) {
            return "No available truck for alert at " + alert.location + " with waste type " + alert.wasteType;
        }

        List<String> path = cityGraph.findShortestPath(truck.baseLocation, alert.location);
        truck.isAvailable = false; // Mark truck as unavailable

        return "Alert at " + alert.location + " (Fill: " + alert.fillLevel + "%, Type: " + alert.wasteType +
               ") assigned to Truck " + truck.id + ". Path: " + String.join(" -> ", path);
    }

    public static void main(String[] args) {
        WasteCollectionSystem system = new WasteCollectionSystem(100, 50);

        // Example city map
        system.addCityRoad("ZoneA", "ZoneB", 5);
        system.addCityRoad("ZoneB", "ZoneC", 3);
        system.addCityRoad("ZoneA", "ZoneC", 7);

        // Add trucks
        system.addTruck("T1", "organic", "ZoneA");
        system.addTruck("T2", "plastic", "ZoneB");

        // Add bin alerts
        system.addBinAlert("ZoneC", 90, "organic", System.currentTimeMillis());
        system.addBinAlert("ZoneB", 85, "plastic", System.currentTimeMillis() + 1000);

        // Process alerts
        System.out.println(system.processNextAlert());
        System.out.println(system.processNextAlert());
    }
}