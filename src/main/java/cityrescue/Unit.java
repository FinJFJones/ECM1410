package cityrescue;

public abstract class Unit {
    int Id;
    int homeStationId;
    int[] loc;

    public Unit(int stationId) {
        this.homeStationId = stationId;
    }
}

class Ambulance extends Unit {
    public Ambulance(int stationId) {
        super(stationId);
    }
}

class FireEngine extends Unit {
    public FireEngine(int stationId) {
        super(stationId);
    }
}

class PoliceCar extends Unit {
    public PoliceCar(int stationId) {
        super(stationId);
    }
}