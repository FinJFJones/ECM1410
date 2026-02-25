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

    Unit[] units;
    int unitCounter;

    Incident[] incidents;
    int incidentCounter;

    @Override
    public void initialise(int width, int height) throws InvalidGridException {
        // TODO: implement 
        if (width < 1 || height < 1){
            throw new InvalidGridException("Invalid Grid Size");
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
            throw new InvalidLocationException("Location not in bounds");
        }
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        if (cityMap.isInBounds(x, y)){
            cityMap.blocked[x][y] = false;
        }
        else{
            throw new InvalidLocationException("Location not in bounds");
        }
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getStationIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
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
