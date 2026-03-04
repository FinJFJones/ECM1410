import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;

public class OurObstacleTests {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(3, 5);
    }
    @Test
    void WallTest() throws Exception {
        for (int i=0 ; i<5; i++){
        cr.addObstacle(1, i);
        }
        cr.addStation("1", 0, 1);
        cr.addUnit(1,UnitType.AMBULANCE);
        cr.reportIncident(IncidentType.MEDICAL, 4, 2, 4);
        cr.dispatch();

        for (int i=0; i<10; i++){
            System.out.println(cr.viewUnit(1));
            cr.tick();
        }

    }

    @Test
    void SurroundedTest() throws Exception {
        cr.addStation("1", 1, 1);
        cr.addUnit(1,UnitType.AMBULANCE);
        cr.reportIncident(IncidentType.MEDICAL, 4, 2, 4);
        cr.addObstacle(1,0);
        cr.addObstacle(0,1);
        cr.addObstacle(2,1);
        cr.addObstacle(1,2);
        cr.dispatch();
        for (int i=0; i<5; i++){
            System.out.println(cr.viewUnit(1));
            cr.tick();
        }
    }

}