import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;
import cityrescue.enums.UnitType;

public class OutputFormatTests {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(50, 50);

        cr.addStation("Station 1", 0, 0);
        cr.addStation("Station 2", 10, 5);
        cr.addStation("Station 3", 25, 42);

        cr.addUnit(1, UnitType.POLICE_CAR);
        cr.addUnit(1, UnitType.POLICE_CAR);
        cr.addUnit(1, UnitType.POLICE_CAR);
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
    void Unit() throws Exception {

    }
}