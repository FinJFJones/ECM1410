import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;

public class OurGeneralTests {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(50, 50);

        cr.addStation("Station 1", 0, 0);
        cr.addStation("Station 2", 10, 5);
        cr.addStation("Station 3", 25, 42);
        
        cr.reportIncident(IncidentType.CRIME, 1, 3, 7);
        cr.reportIncident(IncidentType.CRIME, 1, 2, 27);
        cr.reportIncident(IncidentType.CRIME, 1, 34, 37);
        cr.reportIncident(IncidentType.CRIME, 1, 43, 44);
        cr.reportIncident(IncidentType.CRIME, 1, 21, 32);
        cr.reportIncident(IncidentType.CRIME, 1, 9, 17);
        cr.reportIncident(IncidentType.CRIME, 1, 5, 42);
        cr.reportIncident(IncidentType.CRIME, 1, 6, 7);
        cr.reportIncident(IncidentType.CRIME, 1, 3, 5);
        cr.reportIncident(IncidentType.CRIME, 1, 3, 4);

        cr.reportIncident(IncidentType.MEDICAL, 1, 3, 7);
        cr.reportIncident(IncidentType.MEDICAL, 1, 2, 27);
        cr.reportIncident(IncidentType.MEDICAL, 1, 34, 37);
        cr.reportIncident(IncidentType.MEDICAL, 1, 43, 44);
        cr.reportIncident(IncidentType.MEDICAL, 1, 21, 32);
        cr.reportIncident(IncidentType.MEDICAL, 1, 9, 17);
        cr.reportIncident(IncidentType.MEDICAL, 1, 5, 42);
        cr.reportIncident(IncidentType.MEDICAL, 1, 6, 7);
        cr.reportIncident(IncidentType.MEDICAL, 1, 3, 5);
        cr.reportIncident(IncidentType.MEDICAL, 1, 3, 4);

        cr.reportIncident(IncidentType.FIRE, 1, 3, 7);
        cr.reportIncident(IncidentType.FIRE, 1, 2, 27);
        cr.reportIncident(IncidentType.FIRE, 1, 34, 37);
        cr.reportIncident(IncidentType.FIRE, 1, 43, 44);
        cr.reportIncident(IncidentType.FIRE, 1, 21, 32);
        cr.reportIncident(IncidentType.FIRE, 1, 9, 17);
        cr.reportIncident(IncidentType.FIRE, 1, 5, 42);
        cr.reportIncident(IncidentType.FIRE, 1, 6, 7);
        cr.reportIncident(IncidentType.FIRE, 1, 3, 5);
        cr.reportIncident(IncidentType.FIRE, 1, 3, 4);

        cr.addUnit(1, UnitType.POLICE_CAR);
        cr.addUnit(2, UnitType.POLICE_CAR);
        cr.addUnit(3, UnitType.POLICE_CAR);
        cr.addUnit(1, UnitType.AMBULANCE);
        cr.addUnit(1, UnitType.AMBULANCE);
        cr.addUnit(1, UnitType.AMBULANCE);
        cr.addUnit(1, UnitType.FIRE_ENGINE);
        cr.addUnit(1, UnitType.FIRE_ENGINE);
        cr.addUnit(1, UnitType.FIRE_ENGINE);

        cr.addUnit(2, UnitType.POLICE_CAR);
        cr.addUnit(2, UnitType.POLICE_CAR);
        cr.addUnit(2, UnitType.POLICE_CAR);
        cr.addUnit(2, UnitType.AMBULANCE);
        cr.addUnit(2, UnitType.AMBULANCE);
        cr.addUnit(2, UnitType.AMBULANCE);
        cr.addUnit(2, UnitType.FIRE_ENGINE);
        cr.addUnit(2, UnitType.FIRE_ENGINE);
        cr.addUnit(2, UnitType.FIRE_ENGINE);

        cr.addUnit(3, UnitType.POLICE_CAR);
        cr.addUnit(3, UnitType.POLICE_CAR);
        cr.addUnit(3, UnitType.POLICE_CAR);
        cr.addUnit(3, UnitType.AMBULANCE);
        cr.addUnit(3, UnitType.AMBULANCE);
        cr.addUnit(3, UnitType.AMBULANCE);
        cr.addUnit(3, UnitType.FIRE_ENGINE);
        cr.addUnit(3, UnitType.FIRE_ENGINE);
        cr.addUnit(3, UnitType.FIRE_ENGINE);
    }

    @Test
    void UnitOutputTest() throws Exception {
        cr.dispatch();
        for (int i = 0; i < 50; i++) {
            cr.tick();
        }
        for (int i = 0; i < 30; i++) {
            System.out.println(cr.viewIncident(i+1));
        }
    }
}