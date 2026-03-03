package cityrescue;
import java.util.Arrays;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;
import cityrescue.exceptions.IDNotRecognisedException;
import cityrescue.exceptions.InvalidCapacityException;
import cityrescue.exceptions.InvalidGridException;
import cityrescue.exceptions.InvalidLocationException;
import cityrescue.exceptions.InvalidNameException;
import cityrescue.exceptions.InvalidSeverityException;
import cityrescue.exceptions.InvalidUnitException;
/**
 * CityRescueImpl
 * Methods to be used in the interface for CityRescue
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

    int obstacleCounter;

    /**
     * Initialises the grid and other attributes of the implementation.
     * These include: tick, cityMap, obstacles, and: stations, units and incidents + their counters.
     * @param width The width of the grid.
     * @param height The height of the grid.
     * @throws InvalidGridException If grid size is invalid; invalid grids have width<1 or height<1.
     */
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
        unitRemoveCounter = 0;

        incidents = new Incident[200];
        incidentCounter = 0;
        incidentRemoveCounter = 0;

        obstacleCounter = 0;

    }

    /** 
     * Returns the size of the grid from the cityMap object.
     * @return The size of the grid in form (width,height)
     */
    @Override
    public int[] getGridSize() {
        return cityMap.gridSize;
    }

    /** 
     * Adds an obstacle to the blocked matrix in the cityMap object.
     * @param x The x co-ordinate to add the obstacle at.
     * @param y The y co-ordinate to add the obstacle at.
     * @throws InvalidLocationException
     */
    @Override
    public void addObstacle(int x, int y) throws InvalidLocationException {
        if (cityMap.isInBounds(x, y)){
            cityMap.blocked[x][y] = true;
            obstacleCounter++;
        }
        else{
            throw new InvalidLocationException("Location not in bounds.");
        }
    }

    /** 
     * Removes an obstacle from the blocked matrix in the cityMap object.
     * @param x The x co-ordinate to remove the obstacle at.
     * @param y The y co-ordinate to remove the obstacle at.
     * @throws InvalidLocationException
     */
    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        if (cityMap.isInBounds(x, y)){
            cityMap.blocked[x][y] = false;
            obstacleCounter--;
        }
        else{
            throw new InvalidLocationException("Location not in bounds.");
        }
    }

    /** 
     * Adds a station with the given name at the provided co-ordinates.
     * @param name The name of the station.
     * @param x The x co-ordinate to add the station at.
     * @param y The y co-ordinate to add the station at.
     * @return The Id of the new station.
     * @throws InvalidNameException If name is left blank.
     * @throws InvalidLocationException If location is out of bounds.
     */
    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        if (name.isBlank()){
            throw new InvalidNameException("Name cannot be blank.");
        }
        if (cityMap.isInBounds(x, y)){
            int[] loc = new int[]{x,y};
            stations[stationCounter] = new Station(name,stationCounter+1,loc);
            stationCounter++;
            return stationCounter;
        }
        else{
            throw new InvalidLocationException("Location not in bounds.");
        }
    }

    /** 
     * Removes a station with the given Id.
     * @param stationId The station's Id.
     * @throws IDNotRecognisedException If a station with the given Id does not exist.
     * @throws IllegalStateException If a station still has units.
     */
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

    /** 
     * Sets a given stations maximum units capacity.
     * @param stationId The station's Id.
     * @param maxUnits The new maximum capacity of the station.
     * @throws IDNotRecognisedException If a station with the given Id does not exist.
     * @throws InvalidCapacityException If the station currently has more units than the new max capacity, or new max capacity <1.
     */
    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        int[] Ids = getStationIds();
        if (!Utils.linearSearch(Ids,stationId)){
            throw new IDNotRecognisedException("No such station Id.");
        }
        Station station = stations[stationId-1];
        if (maxUnits < 1){
            throw new InvalidCapacityException("Max units cannot be <1.");
        }

        if (maxUnits < station.numUnits){
            throw new InvalidCapacityException("New capacity limit exceeds current station capacity.");
        }

        station.maxUnits = maxUnits;
    }

    /** 
     * Gets the Ids of all of the currently active stations.
     * @return The Ids of all of the currently active stations.
     */
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

    /** 
     * @param stationId The home stations's Id.
     * @param type The type of unit to be added.
     * @return The unit's Id.
     * @throws IDNotRecognisedException If a station with the given Id does not exist.
     * @throws InvalidUnitException If the given unitType does not exist.
     * @throws IllegalStateException If the station is full.
     */
    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        if (!Utils.linearSearch(getStationIds(), stationId)){
            throw new IDNotRecognisedException("Station ID does not exist.");
        }
        if (stations[stationId-1].maxUnits == stations[stationId-1].numUnits) {
            throw new IllegalStateException("Station full.");
        }
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
            default:
                throw new InvalidUnitException("Unit type invalid.");
        }
        units[unitCounter-1].loc = stations[stationId-1].loc;
        stations[stationId-1].numUnits++;
        return unitCounter;
    }

    /** 
     * Permenantly decommissions a unit.
     * @param unitId The Id of the unit.
     * @throws IDNotRecognisedException If the unit with given Id  does not exist.
     * @throws IllegalStateException If the unit is en-route or at-scene.
     */
    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        int[] Ids = getUnitIds();
        if (!Utils.linearSearch(Ids, unitId)){
            throw new IDNotRecognisedException("No such unit Id.");
        }
        if (units[unitId-1].status == UnitStatus.AT_SCENE || units[unitId-1].status == UnitStatus.EN_ROUTE) {
            throw new IllegalStateException("Unit is either en route or at scene.");
        }

        int stationId = units[unitId-1].homeStationId;
        units[unitId-1] = null;
        stations[stationId-1].numUnits--;
    }

    /** 
     * Transfers a unit to a new home station.
     * @param unitId The Id of the unit to be transferred.
     * @param newStationId The Id of the new home station.
     * @throws IDNotRecognisedException If the unit with given Id  or new home station does not exist.
     * @throws IllegalStateException If the station is full or the unit is not idle.
     */
    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        if (!Utils.linearSearch(getUnitIds(), unitId)) { throw new IDNotRecognisedException("Unit does not exist."); }
        if (!Utils.linearSearch(getStationIds(), newStationId)) { throw new IDNotRecognisedException("Station does not exist."); }
        if (stations[newStationId-1].numUnits == stations[newStationId-1].maxUnits) { throw new IllegalStateException("Station is full."); }
        if (units[unitId-1].status != UnitStatus.IDLE) { throw new IllegalStateException("Unit is not idle."); }

        int stationId = units[unitId-1].homeStationId;
        stations[stationId-1].numUnits--;

        units[unitId-1].homeStationId = newStationId;
        units[unitId-1].loc = stations[newStationId-1].loc.clone();
        stations[newStationId-1].numUnits++;
    }

    /** 
     * Sets a unit to either be out-of-service or in-service (true/false).
     * @param unitId The Id of the unit.
     * @param outOfService Whether the unit should be set to be out-of-service or in-service.
     * @throws IDNotRecognisedException If the unit with given Id does not exist.
     * @throws IllegalStateException If the unit is not idle.
     */
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

    /** 
     * Gets all of the existing units Ids.
     * @return The existing units Ids.
     */
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

    /** 
     * Gets the information about a unit.
     * @param unitId The Id of the unit.
     * @return The information about the unit.
     * @throws IDNotRecognisedException If the unit with the given Id does not exist.
     */
    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // U#2 TYPE=FIRE_ENGINE HOME=2 LOC=(3,1) STATUS=AT_SCENE INCIDENT=1 WORK=2
        if (!Utils.linearSearch(getUnitIds(), unitId)){
            throw new IDNotRecognisedException("UnitID not found.");
        }
        Unit unit = units[unitId-1];

        String incidentStr = Integer.toString(unit.incidentID);
        if (unit.incidentID == -1){
            incidentStr = "-";
        }

        String message = String.format("U#%d TYPE=%s HOME=%d LOC=(%d,%d) STATUS=%s INCIDENT=%s",
                                        unitId,
                                        unit.type,
                                        unit.homeStationId,
                                        unit.loc[0],
                                        unit.loc[1],
                                        unit.status,
                                        incidentStr
                                        );
        if (unit.status == UnitStatus.AT_SCENE){
            String workMsg = " WORK=" + unit.ticksToResolve;
            message += workMsg;
        }
        return message;
    }

    /** 
     * Reports a new incident.
     * @param type The type of incident.
     * @param severity The severity of the incident.
     * @param x The x co-ordinate of the incident.
     * @param y The y co-ordinate of the incident.
     * @return The Id of the incident.
     * @throws InvalidSeverityException If severity is not in range 1-5.
     * @throws InvalidLocationException If location is out of bounds.
     */
    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        if (!cityMap.isInBounds(x, y)){
            throw new InvalidLocationException("Location not in bounds.");
        }
        if (severity > 5 || severity < 1){
            throw new InvalidSeverityException("Severity not in range 1-5.");
        }

        incidents[incidentCounter] = new Incident(type,severity,new int[]{x,y},incidentCounter+1);
        incidentCounter++;
        return incidentCounter;
    }

    /** 
     * Cancels an incident.
     * @param incidentId The Id of the incident.
     * @throws IDNotRecognisedException If the incident with given Id does not exist.
     * @throws IllegalStateException If the incident status is not Reported or Dispatched.
     */
    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        int[] incidentIds = getIncidentIds();
        if (!Utils.linearSearch(incidentIds,incidentId)){
            throw new IDNotRecognisedException("IncidentID not found.");
        }
        Incident incident = incidents[incidentId-1];
        if (!(incident.incidentStatus == IncidentStatus.REPORTED) && !(incident.incidentStatus == IncidentStatus.DISPATCHED)){//if not reported or dispatched
            throw new IllegalStateException("Incident status must be REPORTED or DISPATCHED.");
        }

        incident.incidentStatus = IncidentStatus.CANCELLED;

        int unitId = -1;

        for (Unit unit : units){
            if (unit != null) {
                if (unit.incidentID == incidentId){
                    unitId = unit.Id;
                }
            }
        }

        units[unitId-1].status = UnitStatus.IDLE;
    }

    /** 
     * Changes the severity of an incident.
     * @param incidentId The Id of the incident.
     * @param newSeverity The new severity to make the incident.
     * @throws IDNotRecognisedException If the Incident with given Id does not exist.
     * @throws InvalidSeverityException If severity is not in range 1-5.
     * @throws IllegalStateException If the incident status is Cancelled or Resolved.
     */
    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        int[] incidentIds = getIncidentIds();

        if (!Utils.linearSearch(incidentIds,incidentId)){
            throw new IDNotRecognisedException("IncidentID not found.");
        }

        Incident incident = incidents[incidentId-1];

        if (newSeverity > 5 || newSeverity < 1){
            throw new InvalidSeverityException("Severity not in range 1-5.");
        }

        if ((incident.incidentStatus == IncidentStatus.CANCELLED) || (incident.incidentStatus == IncidentStatus.RESOLVED)){//if cancelled or resolved
            throw new IllegalStateException("Incident status must not be CANCELLED or RESOLVED.");
        }

        incidents[incidentId-1].severity = newSeverity;
    }

    /** 
     * Gets the Ids of all incidents.
     * @return The Ids of all incidents.
     */
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

    /** 
     * Gets the information about an incident.
     * @param incidentId The Id of the incident.
     * @return The information about the incident.
     * @throws IDNotRecognisedException If the incident with the given Id does not exist.
     */
    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // I#1 TYPE=FIRE SEV=4 LOC=(3,1) STATUS=IN_PROGRESS UNIT=2
        if (!Utils.linearSearch(getIncidentIds(),incidentId)){
            throw new IDNotRecognisedException("IncidentID not found.");
        }
        Incident incident = incidents[incidentId-1];

        int unitId = -1;

        for (Unit unit : units){
            if (unit != null) {
                if (unit.incidentID == incidentId){
                    unitId = unit.Id;
                }
            }
        }

        String unitIdStr = Integer.toString(unitId);
        if (unitId == -1){
            unitIdStr = "-";
        }

        String message = String.format("I#%d TYPE=%s SEV=%d LOC=(%d,%d) STATUS=%s UNIT=%s",
                                        incidentId,
                                        incident.incidentType,
                                        incident.severity,
                                        incident.loc[0],
                                        incident.loc[1],
                                        incident.incidentStatus,
                                        unitIdStr
                                        );

        return message;
    }

    /**
     * Dispatches all available units to incidents that which they can handle, based on their distance to the incident, and then their Id, and then home station Id.
     */
    @Override
    public void dispatch() {
        for (Incident incident : incidents) {
            if (incident != null) {
                if (incident.incidentStatus == IncidentStatus.REPORTED) {
                    int taxiCabDist = (getGridSize()[0]-1 + getGridSize()[1]-1) + 1; // One more than max distance
                    Unit unitToDispatch = null;
                    int newTaxiCab;
                    for (Unit unit : units) {
                        if (unit != null) {
                            if (unit.canHandle(incident.incidentType) && unit.status != UnitStatus.OUT_OF_SERVICE && unit.status != UnitStatus.EN_ROUTE && unit.status != UnitStatus.AT_SCENE) {
                                newTaxiCab = Utils.taxiCab(unit.loc, incident.loc);
                                if (newTaxiCab < taxiCabDist) {
                                    taxiCabDist = newTaxiCab;
                                    unitToDispatch = unit;
                                }
                            }
                        }
                    }
                    if (unitToDispatch != null) {
                        unitToDispatch.status = UnitStatus.EN_ROUTE;
                        unitToDispatch.incidentID = incident.Id;
                        incident.incidentStatus = IncidentStatus.DISPATCHED;
                    }
                }
            }
        }
    }
    /**
     * Increases tick by one, moves units towards incidents, and progresses through the incident lifecycle.
     */
    @Override
    public void tick() {
        tick++;
        for (Unit unit : units) {
            if (unit != null) {
                if (unit.status == UnitStatus.EN_ROUTE) {
                    unit.move(cityMap,incidents);
                    if (Arrays.equals(unit.loc, incidents[unit.incidentID-1].loc)) {
                        unit.status = UnitStatus.AT_SCENE;
                        incidents[unit.incidentID-1].incidentStatus = IncidentStatus.IN_PROGRESS;
                        incidents[unit.incidentID-1].ticksLeft = unit.ticksToResolve;
                    }
                }
            }
        }
        for (Incident incident : incidents) {
            if (incident != null) {
                if (incident.incidentStatus == IncidentStatus.IN_PROGRESS){
                    incident.ticksLeft--;
                }
            }
        }
        for (Incident incident : incidents) {
            if (incident != null) {
                if (incident.incidentStatus == IncidentStatus.IN_PROGRESS){
                    if (incident.ticksLeft == 0) {
                        incident.incidentStatus = IncidentStatus.RESOLVED;

                        int unitId = -1;
                        for (Unit unit : units){
                            if (unit != null) {
                                if (unit.incidentID == incident.Id){
                                    unitId = unit.Id;
                                }
                            }
                        }
                        units[unitId-1].status = UnitStatus.IDLE;
                        units[unitId-1].incidentID = -1;
                    }
                }
            }
        }
    }

    /** 
     * Gets the information of all units and incidents, as well as tick and counters information.
     * @return The information of all units and incidents, as well as tick and counters information.
     */
    @Override
    public String getStatus() {
        // TICK=7
        // STATIONS=2 UNITS=3 INCIDENTS=2 OBSTACLES=5
        // INCIDENTS
        // I#1 TYPE=FIRE SEV=4 LOC=(3,1) STATUS=IN_PROGRESS UNIT=2
        // I#2 TYPE=CRIME SEV=2 LOC=(0,4) STATUS=REPORTED UNIT=-
        // UNITS
        // U#1 TYPE=AMBULANCE HOME=1 LOC=(1,1) STATUS=IDLE INCIDENT=-
        // U#2 TYPE=FIRE_ENGINE HOME=2 LOC=(3,1) STATUS=AT_SCENE INCIDENT=1 WORK=2
        // U#3 TYPE=POLICE_CAR HOME=1 LOC=(1,2) STATUS=EN_ROUTE INCIDENT=2

        String tickMsg = String.format("TICK=%d\n",tick);
        String countMsg = String.format(
        "STATIONS=%d UNITS=%d INCIDENTS=%d OBSTACLES=%d\n",
        stationCounter,
        unitCounter,
        incidentCounter,
        obstacleCounter
        );

        String incidentMsg = "INCIDENTS\n";
        for (int Id=0 ; Id<incidentCounter ; Id++){
            if (incidents[Id] != null){
                try {
                    incidentMsg = incidentMsg + viewIncident(Id) + "\n";
                } catch (IDNotRecognisedException ex) {
                }
            }
        }

        String unitMsg = "UNITS\n";
        for (int Id=0 ; Id<unitCounter ; Id++){
            if (units[Id] != null){
                try {
                    unitMsg = unitMsg + viewUnit(Id) + "\n";
                } catch (IDNotRecognisedException ex) {
                }
            }
        }

        return (tickMsg+countMsg+incidentMsg+unitMsg);
    }
}
