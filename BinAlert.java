public class BinAlert {
    String location;
    int fillLevel; // Percentage (0-100)
    String wasteType; // organic, plastic, general
    long alertTime; // Timestamp in milliseconds

    BinAlert(String location, int fillLevel, String wasteType, long alertTime) {
        this.location = location;
        this.fillLevel = fillLevel;
        this.wasteType = wasteType;
        this.alertTime = alertTime;
    }
}