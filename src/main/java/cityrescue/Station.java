package cityrescue;

public class Station {
    String name;
    int Id;
    int[] loc;

    Unit[] units;
    int maxUnits;
    int numUnits;

    public Station(String name, int Id,int[] loc){
        this.name = name;
        this.Id = Id;
        this.loc = loc;
        this.maxUnits = 200; //worst case has 200 units (all of the possible units)
        this.units = new Unit[200];
        this.numUnits = 0;
    }
}
