package cityrescue;

import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;

public abstract class Unit {
    int Id;
    UnitType type;
    UnitStatus status;
    int homeStationId;
    int[] loc;

    // implement state (e.g idle)

    public Unit(int stationId, UnitType type) {
        this.homeStationId = stationId;
        this.type = type;
        this.status = UnitStatus.IDLE;
    }
}

class Ambulance extends Unit {
    public Ambulance(int stationId, UnitType type) {
        super(stationId, type);
    }
}

class FireEngine extends Unit {
    public FireEngine(int stationId, UnitType type) {
        super(stationId, type);
    }
}

class PoliceCar extends Unit {
    public PoliceCar(int stationId, UnitType type) {
        super(stationId, type);
    }
}