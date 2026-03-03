package cityrescue;

import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;

/**
 * Abstract class for any Unit, contains unit information and the methods related to moving and checking whether they can handle a certain incident.
 */
public abstract class Unit {
    int Id;
    int ticksToResolve;
    UnitType type;
    UnitStatus status;
    int incidentID;
    int homeStationId;
    IncidentType[] handleableIncidences;
    int[] loc;

    public Unit(int Id, int stationId, UnitType type) {
        this.Id = Id;
        this.homeStationId = stationId;
        this.type = type;
        this.incidentID = -1;
        this.status = UnitStatus.IDLE;
        this.handleableIncidences = new IncidentType[0];
    }

    /** 
     * Follows movement rules specified in the task documents, then changes this Unit's location to wherever the algorithm has decided to move. 
     * @param cityMap
     * @param incidents
     */
    public void move(CityMap cityMap,Incident[] incidents) {
        int[][] possible_moves = {{0,1},{1,0},{0,-1},{-1,0}};
        int[] incidentLoc = incidents[this.incidentID-1].loc;
        int dist = Utils.taxiCab(incidentLoc, this.loc);
        boolean[] isMoveLegal = new boolean[4];
        boolean moved = false;

        for (int i=0 ; i<4 ; i++){
            int[] move = possible_moves[i];
            int newX = this.loc[0] + move[0];
            int newY = this.loc[1] + move[1];
            int[] newLoc = new int[] {newX,newY};

            if (cityMap.isInBounds(newX, newY)){
                if (!cityMap.blocked[newX][newY]){
                    if (Utils.taxiCab(incidentLoc, newLoc) < dist){
                        this.loc = newLoc;
                        moved = true;
                        break;
                    }
                isMoveLegal[i] = true;
                }
            }
        }

        if (!moved){
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

    /** 
     * Returns whether this unit can handle a certain type of incident (e.g Ambulance and Medical).
     * @param incidentType
     * @return boolean
     */
    public boolean canHandle(IncidentType incidentType) {
        for (IncidentType elem : this.handleableIncidences) {
            if (elem == incidentType){
                return true;
            }
        }
        return false;
    }
}

/**
 * Child class for an Ambulance from Unit.
 */
class Ambulance extends Unit {
    public Ambulance(int Id, int stationId, UnitType type) {
        super(Id, stationId, type);
        this.ticksToResolve = 2;
        this.handleableIncidences = new IncidentType[] {IncidentType.MEDICAL};
    }
}

/**
 * Child class for a Fire Engine from Unit.
 */
class FireEngine extends Unit {
    public FireEngine(int Id, int stationId, UnitType type) {
        super(Id, stationId, type);
        this.ticksToResolve = 4;
        this.handleableIncidences = new IncidentType[] {IncidentType.FIRE};
    }
}

/**
 * Child class for a Police Car from Unit.
 */
class PoliceCar extends Unit {
    public PoliceCar(int Id, int stationId, UnitType type) {
        super(Id, stationId, type);
        this.ticksToResolve = 3;
        this.handleableIncidences = new IncidentType[] {IncidentType.CRIME};
    }
}