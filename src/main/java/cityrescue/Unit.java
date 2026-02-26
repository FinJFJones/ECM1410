package cityrescue;

import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;

public abstract class Unit {
    int Id;
    int ticksToResolve;
    UnitType type;
    UnitStatus status;
    boolean exists;
    int incidentID;
    int homeStationId;
    int[] loc;

    // implement state (e.g idle)

    public Unit(int Id, int stationId, UnitType type) {
        this.Id = Id;
        this.homeStationId = stationId;
        this.type = type;
        this.incidentID = -1;
        this.status = UnitStatus.IDLE;
        this.exists = true;
    }

    public void move() {
        
    }
}

class Ambulance extends Unit {
    public Ambulance(int Id, int stationId, UnitType type) {
        super(Id, stationId, type);
        this.ticksToResolve = 2;
    }
}

class FireEngine extends Unit {
    public FireEngine(int Id, int stationId, UnitType type) {
        super(Id, stationId, type);
        this.ticksToResolve = 4;
    }
}

class PoliceCar extends Unit {
    public PoliceCar(int Id, int stationId, UnitType type) {
        super(Id, stationId, type);
        this.ticksToResolve = 3;
    }
}