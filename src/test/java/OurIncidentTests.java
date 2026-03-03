import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;

public class OurIncidentTests {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5, 5);
    }

    @Test
    void incidentTests() throws Exception {
        cr.reportIncident(IncidentType.CRIME, 3, 3, 1);
        try {
            cr.reportIncident(null, 2, 1, 1);
        } catch (Exception e) {
            System.out.print("Expected behaviour: Incident type does not exist\n-> ");
            System.out.println(e);
        }
        try {
            cr.reportIncident(IncidentType.CRIME, -1, 1, 1);
        } catch (Exception e) {
            System.out.print("Expected behaviour: Wrong severity\n-> ");
            System.out.println(e);
        }
        try {
            cr.reportIncident(IncidentType.CRIME, 6, 1, 1);
        } catch (Exception e) {
            System.out.print("Expected behaviour: Wrong severity\n-> ");
            System.out.println(e);
        }
        try {
            cr.reportIncident(IncidentType.CRIME, 3, 7, 1);
        } catch (Exception e) {
            System.out.print("Expected behaviour: Out of bounds\n-> ");
            System.out.println(e);
        }
        cr.addStation("1", 1, 1);
        cr.addUnit(1, UnitType.POLICE_CAR);

        cr.dispatch();
        cr.tick();
        cr.tick();
        try {
            cr.cancelIncident(1);
        } catch (Exception e) {
            System.out.print("Expected behaviour: incident must be reported/dispatched\n-> ");
            System.out.println(e);
        }
        cr.tick();
        cr.tick();

        try {
            cr.escalateIncident(1, 2);
        } catch (Exception e) {
            System.out.print("Expected behaviour: incident must not be cancelled/resolved\n-> ");
            System.out.println(e);
        }
    }
}