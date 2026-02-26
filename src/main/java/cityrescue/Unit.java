package cityrescue;

import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;

public abstract class Unit {
    int Id;
    int ticksToResolve;
    UnitType type;
    UnitStatus status;
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
    }

    public void move(CityMap cityMap,Incident[] incidents) {
        int[][] possible_moves = {{0,1},{1,0},{0,-1},{-1,0}};
        int[] incidentLoc = incidents[this.incidentID-1].loc;
        int dist = Utils.taxiCab(incidentLoc, this.loc);
        boolean[] isMoveLegal = new boolean[4];

        for (int i=0 ; i<4 ; i++){
            int[] move = possible_moves[i];
            int newX = this.loc[0] + move[0];
            int newY = this.loc[1] + move[1];
            int[] newLoc = new int[] {newX,newY};

            if (cityMap.isInBounds(newX, newY)){
                if (!cityMap.blocked[newX][newY]){
                    if (Utils.taxiCab(incidentLoc, newLoc) < dist){
                        this.loc = newLoc;
                    }
                isMoveLegal[i] = true;
                }
            }
        }
        for (int i=0 ; i<4 ; i++){
            if (isMoveLegal[i]){
                int[] move = possible_moves[i];
                int newX = this.loc[0] + move[0];
                int newY = this.loc[1] + move[1];
                int[] newLoc = new int[] {newX,newY};
                this.loc = newLoc;
                break;
            }
        }

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