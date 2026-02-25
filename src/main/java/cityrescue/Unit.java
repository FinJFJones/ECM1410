package cityrescue;

public abstract class Unit {
    int Id;
    int homeStationId;
    int[] loc;
}

class Ambulance extends Unit {
    public Ambulance(int stationId) {
        this.homeStationId = stationId;
    }
}

class FireEngine extends Unit {

}

class PoliceCar extends Unit {

}