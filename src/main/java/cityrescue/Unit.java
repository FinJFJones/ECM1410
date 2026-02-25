package cityrescue;

public abstract class Unit {
    int ID;
    int homeStationID;
    int[] loc;
}

class Ambulance extends Unit {

}

class FireEngine extends Unit {

}

class PoliceCar extends Unit {

}