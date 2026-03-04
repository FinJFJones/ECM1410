import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;

public class VideoFeaturesDisplay {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5, 5);
        cr.addStation("1", 2, 3);
        cr.addUnit(1, UnitType.AMBULANCE);
        cr.reportIncident(IncidentType.MEDICAL, 3, 4, 4);
    }

    @Test
    void runMe() throws Exception {
        System.out.println(cr.getStatus());
        cr.dispatch();
        System.out.println(cr.getStatus());
        cr.tick();
        System.out.println(cr.getStatus());
        cr.tick();
        System.out.println(cr.getStatus());
        cr.tick();
        System.out.println(cr.getStatus());
        cr.tick();
        System.out.println(cr.getStatus());
        cr.tick();

    }

}