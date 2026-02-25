package cityrescue;

import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;
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

    // TODO: add fields (map, arrays for stations/units/incidents, counters, tick, etc.)
    int tick;
    CityMap cityMap;

    Station[] stations;
    int stationCounter;
    int stationRemoveCounter;

    Unit[] units;
    int unitCounter;

    Incident[] incidents;
    int incidentCounter;

    @Override
    public void initialise(int width, int height) throws InvalidGridException {
        // TODO: implement 
        if (width < 1 || height < 1){
            throw new InvalidGridException("Invalid Grid Size.");
        }
        tick = 0;
        cityMap = new CityMap(width,height);

        stations = new Station[20];
        stationCounter = 0;

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
            stations[stationCounter] = new Station(name,stationCounter,loc);
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
            }
        }
        return stationIds;
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        switch (type) {
            case AMBULANCE:
                units[unitCounter] = new Ambulance(stationId);
                break;
            case FIRE_ENGINE:
                units[unitCounter] = new FireEngine(stationId);
                break;
            case POLICE_CAR:
                units[unitCounter] = new PoliceCar(stationId);
                break;
        }
        unitCounter++;
        return unitCounter;
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        if (!Utils.linearSearch(getUnitIds(), unitId)) { throw new IDNotRecognisedException("Unit does not exist."); }
        if (!Utils.linearSearch(getStationIds(), newStationId)) { throw new IDNotRecognisedException("Station does not exist."); }
        if (units[unitId-1])
        units[unitId-1].homeStationId = newStationId;
    }

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getUnitIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getIncidentIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
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
}
