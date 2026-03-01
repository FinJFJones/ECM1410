package cityrescue;

public class SaveState {
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

    public SaveState(int tick, 
                    CityMap cityMap, 
                    Station[] stations, 
                    int stationCounter, 
                    int stationRemoveCounter, 
                    Unit[] units, 
                    int unitCounter, 
                    int unitRemoveCounter, 
                    Incident[] incidents, 
                    int incidentCounter, 
                    int incidentRemoveCounter) {
        this.tick = tick;
        this.cityMap = cityMap;

        this.stations = stations;
        this.stationCounter = stationCounter;
        this.stationRemoveCounter = stationRemoveCounter;

        this.units = units;
        this.unitCounter = unitCounter;
        this.unitRemoveCounter = unitRemoveCounter;

        this.incidents = incidents;
        this.incidentCounter = incidentCounter;
        this.incidentRemoveCounter = incidentRemoveCounter;
    }
}
