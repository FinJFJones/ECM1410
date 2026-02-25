package cityrescue;

public class Station {
    String name;
    int Id;
    Unit[] units;
    int[] loc;
    public Station(String name, int Id,int[] loc){
        this.name = name;
        this.Id = Id;
        this.loc = loc;
        this.units = new Unit[200]; //WORST CASE 200 UNITS IN 1 STATION
    }
}
