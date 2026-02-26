package cityrescue;

public class Station {
    String name;
    int Id;
    int[] loc;
    boolean exists;

    Unit[] units;
    int maxUnits;
    int numUnits;

    public Station(String name, int Id,int[] loc){
        this.name = name;
        this.Id = Id;
        this.loc = loc;
        this.exists = true;
        this.maxUnits = 50; //worst case has 50 units (all of the possible units)
        this.units = new Unit[50];
        this.numUnits = 0;
    }
}
