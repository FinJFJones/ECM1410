package cityrescue;

import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;
import cityrescue.enums.IncidentStatus;
import cityrescue.exceptions.IDNotRecognisedException;
import cityrescue.exceptions.InvalidCapacityException;
import cityrescue.exceptions.InvalidGridException;
import cityrescue.exceptions.InvalidLocationException;
import cityrescue.exceptions.InvalidNameException;
import cityrescue.exceptions.InvalidSeverityException;
import cityrescue.exceptions.InvalidUnitException;
/**
 * CityRescueImpl (Starter)
 *
 * Your task is to implement the full specification.
 * You may add additional classes in any package(s) you like.
 */
public class CityRescueImpl implements CityRescue {

    int tick;
    CityMap cityMap;

    Station[] stations;
    int stationCounter;
    int stationRemoveCounter;

    Unit[] units;
    int unitCounter;
    int unitRemoveCounter;

    Incident[] incidents;
    int incidentCounter;
    int incidentRemoveCounter;

    @Override
    public void initialise(int width, int height) throws InvalidGridException {
        if (width < 1 || height < 1){
            throw new InvalidGridException("Invalid Grid Size.");
        }
        tick = 0;
        cityMap = new CityMap(width,height);

        stations = new Station[20];
        stationCounter = 0;
        stationRemoveCounter = 0;

        units = new Unit[50];
        unitCounter = 0;

        incidents = new Incident[200];
        incidentCounter = 0;

    }

    @Override
    public int[] getGridSize() {
        return cityMap.gridSize;
    }

    @Override
    public void addObstacle(int x, int y) throws InvalidLocationException {
        if (cityMap.isInBounds(x, y)){
            cityMap.blocked[x][y] = true;
        }
        else{
            throw new InvalidLocationException("Location not in bounds.");
        }
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        if (cityMap.isInBounds(x, y)){
            cityMap.blocked[x][y] = false;
        }
        else{
            throw new InvalidLocationException("Location not in bounds.");
        }
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        if (name.isBlank()){
            throw new InvalidNameException("Name cannot be blank.");
        }
        if (cityMap.isInBounds(x, y)){
            int[] loc = new int[]{x,y};
            stations[stationCounter] = new Station(name,stationCounter+1,loc);
            return stationCounter++;
        }
        else{
            throw new InvalidLocationException("Location not in bounds.");
        }
    }

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        int[] Ids = getStationIds();
        if (!Utils.linearSearch(Ids,stationId)){
            throw new IDNotRecognisedException("No such station Id.");
        }
        Station station = stations[stationId-1];

        if (station.numUnits != 0){
            throw new IllegalStateException("Station still has units.");
        }

        stations[stationId-1] = null;
        stationRemoveCounter++;
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        int[] Ids = getStationIds();
        if (!Utils.linearSearch(Ids,stationId)){
            throw new IDNotRecognisedException("No such station Id.");
        }
        Station station = stations[stationId-1];

        if (maxUnits < station.numUnits){
            throw new InvalidCapacityException("New capacity limit exceeds current station capacity.");
        }
        if (maxUnits < 1){
            throw new InvalidCapacityException("Max units cannot be <1.");
        }

        station.maxUnits = maxUnits;
    }

    @Override
    public int[] getStationIds() {
        int[] stationIds = new int[(stationCounter - stationRemoveCounter)];
        int IdsIndex = 0;

        for (int i = 0; i < stationCounter; i++) {
            if (stations[i] != null) {
                stationIds[IdsIndex] = stations[i].Id;
                IdsIndex++;
            }
        }
        return stationIds;
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        unitCounter++;
        switch (type) {
            case AMBULANCE:
                units[unitCounter-1] = new Ambulance(unitCounter, stationId, type);
                break;
            case FIRE_ENGINE:
                units[unitCounter-1] = new FireEngine(unitCounter, stationId, type);
                break;
            case POLICE_CAR:
                units[unitCounter-1] = new PoliceCar(unitCounter, stationId, type);
                break;
        }
        return unitCounter;
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        int[] Ids = getUnitIds();
        if (!Utils.linearSearch(Ids, unitId)){
            throw new IDNotRecognisedException("No such unit Id.");
        }
        if (units[unitId-1].status == UnitStatus.AT_SCENE || units[unitId-1].status == UnitStatus.EN_ROUTE) {
            throw new IllegalStateException("Unit is either en route or at scene.");
        }

        stations[unitId-1] = null;
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        if (!Utils.linearSearch(getUnitIds(), unitId)) { throw new IDNotRecognisedException("Unit does not exist."); }
        if (!Utils.linearSearch(getStationIds(), newStationId)) { throw new IDNotRecognisedException("Station does not exist."); }
        if (stations[newStationId-1].numUnits == stations[newStationId-1].maxUnits) { throw new IllegalStateException("Station is full."); }
        if (units[unitId-1].status == UnitStatus.IDLE) { throw new IllegalStateException("Unit is not idle."); }

        units[unitId-1].homeStationId = newStationId;
        units[unitId-1].loc = stations[newStationId-1].loc.clone();
    }

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        if (!Utils.linearSearch(getUnitIds(), unitId)) { throw new IDNotRecognisedException("Unit does not exist."); }
        if (outOfService && !(units[unitId-1].status == UnitStatus.IDLE)) { throw new IllegalStateException("Unit is not idle."); }

        if (outOfService) {
            units[unitId-1].status = UnitStatus.OUT_OF_SERVICE;
        }
        else if (units[unitId-1].status == UnitStatus.OUT_OF_SERVICE) {
            units[unitId-1].status = UnitStatus.IDLE;
        }

    }

    @Override
    public int[] getUnitIds() {
        int[] unitIds = new int[(unitCounter - unitRemoveCounter)];
        int IdsIndex = 0;

        for (int i = 0; i < unitCounter; i++) {
            if (units[i] != null) {
                unitIds[IdsIndex] = units[i].Id;
                IdsIndex++;
            }
        }
        return unitIds;
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        if (!cityMap.isInBounds(x, y)){
            throw new InvalidLocationException("Location not in bounds.");
        }
        if (severity > 5 || severity < 1){
            throw new InvalidSeverityException("Severity not in range 1-5.");
        }

        incidents[incidentCounter] = new Incident(type,severity,new int[]{x,y},incidentCounter+1);
        return incidentCounter++;
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        int[] incidentIds = getIncidentIds();
        if (!Utils.linearSearch(incidentIds,incidentId)){
            throw new IDNotRecognisedException("IncidentID not found.");
        }
        Incident incident = incidents[incidentId];
        if (!(incident.incidentStatus == IncidentStatus.REPORTED) && !(incident.incidentStatus == IncidentStatus.DISPATCHED)){//if not reported or dispatched
            throw new IllegalStateException("Incident status must be REPORTED or DISPATCHED.");
        }
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        int[] incidentIds = getIncidentIds();

        if (!Utils.linearSearch(incidentIds,incidentId)){
            throw new IDNotRecognisedException("IncidentID not found.");
        }

        Incident incident = incidents[incidentId];

        if (newSeverity > 5 || newSeverity < 1){
            throw new InvalidSeverityException("Severity not in range 1-5.");
        }

        if ((incident.incidentStatus == IncidentStatus.CANCELLED) || (incident.incidentStatus == IncidentStatus.RESOLVED)){//if cancelled or resolved
            throw new IllegalStateException("Incident status must not be CANCELLED or RESOLVED.");
        }

        incidents[incidentId].severity = newSeverity;
    }

    @Override
    public int[] getIncidentIds() {
        int[] incidentIds = new int[(incidentCounter - incidentRemoveCounter)];
        int IdsIndex = 0;

        for (int i = 0; i < incidentCounter; i++) {
            if (incidents[i] != null) {
                incidentIds[IdsIndex] = incidents[i].Id;
                IdsIndex++;
            }
        }
        return incidentIds;
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // I#1 TYPE=FIRE SEV=4 LOC=(3,1) STATUS=IN_PROGRESS UNIT=2
        if (!Utils.linearSearch(getIncidentIds(),incidentId)){
            throw new IDNotRecognisedException("IncidentID not found.");
        }
        Incident incident = incidents[incidentId];

        int unitId = -1;

        for (Unit unit : units){
            if (unit.incidentID == incidentId){
                unitId = unit.Id;
            }}

        String message = String.format("I#%d TYPE=%s SEV=%d LOC=(%d,%d) STATUS=%s UNIT=%d",
                                        incidentId,
                                        incident.incidentType,
                                        incident.severity,
                                        incident.loc[0],
                                        incident.loc[1],
                                        incident.incidentStatus,
                                        unitId
                                        );

        return message;
    }

    @Override
    public void dispatch() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void tick() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getStatus() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public CityRescueImpl saveState() {

    }
}
