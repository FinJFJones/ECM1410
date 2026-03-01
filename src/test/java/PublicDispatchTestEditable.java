import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;

public class PublicDispatchTestEditable {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(6, 6);
    }

    @Test
    void dispatch_assignsClosestEligibleUnit_thenLowestUnitId() throws Exception {
        int s = cr.addStation("A", 0, 0);
        int u1 = cr.addUnit(s, UnitType.POLICE_CAR);
        int u2 = cr.addUnit(s, UnitType.POLICE_CAR);

        int i1 = cr.reportIncident(IncidentType.CRIME, 2, 2, 2);

        cr.dispatch();

        String inc = cr.viewIncident(i1);
        assertTrue(inc.contains("UNIT=" + u1));
        assertFalse(inc.contains("UNIT=" + u2));
    }
}
