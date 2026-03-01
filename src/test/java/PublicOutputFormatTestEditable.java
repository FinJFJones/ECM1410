import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;

public class PublicOutputFormatTestEditable {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5, 5);
    }

    @Test
    void getStatus_containsRequiredHeadings() throws Exception {
        String s = cr.getStatus();
        assertTrue(s.contains("TICK="));
        assertTrue(s.contains("INCIDENTS"));
        assertTrue(s.contains("UNITS"));
    }

    @Test
    void viewUnit_and_viewIncident_haveStablePrefixes() throws Exception {
        int st = cr.addStation("A", 0, 0);
        int u = cr.addUnit(st, UnitType.FIRE_ENGINE);
        int i = cr.reportIncident(IncidentType.FIRE, 3, 4, 4);

        //System.out.println(cr.viewUnit(u));
        // Output prefix must match the coursework specification examples
        // (e.g., "U#2 ..." and "I#1 ...").
        assertTrue(cr.viewUnit(u).startsWith("U#"));
        assertTrue(cr.viewIncident(i).startsWith("I#"));
    }
}
