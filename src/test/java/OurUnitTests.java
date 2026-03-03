import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;
import cityrescue.enums.UnitType;

public class OurUnitTests {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5, 5);
        cr.addStation("1", 1, 1);
        cr.addStation("2", 1, 3);
    }
    @Test
    void UnitTests() throws Exception {
        cr.addUnit(1, UnitType.AMBULANCE);
        cr.addUnit(2,UnitType.FIRE_ENGINE);

        System.out.println(cr.viewUnit(1));

        cr.setUnitOutOfService(1, true);
        cr.setUnitOutOfService(1, false);

        System.out.println(cr.viewUnit(1));
        cr.transferUnit(1,2);
    }
}