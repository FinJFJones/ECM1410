import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;
import cityrescue.enums.UnitType;

public class OurStationTests {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5, 5);
    }

    @Test
    void stationTests() throws Exception{
        for (int i=0; i<20; i++){
            cr.addStation("One", 3, 2);
        }

        try {
            cr.addStation("Too many stations error", 2, 3);
        } catch (Exception e) {
            System.out.print("Expected behaviour: Index 20 out of bounds for length 20\n-> ");
            System.out.println(e);
        }

        try {
            cr.addStation("Out of bounds error", 7, 7);
        } catch (Exception e) {
            System.out.print("Expected behaviour: Location not in bounds\n-> ");
            System.out.println(e);
        }

        int[] expected = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        assertArrayEquals(expected,cr.getStationIds());

        cr.removeStation(3);

        int[] expected2 = new int[] {1,2,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        assertArrayEquals(expected2,cr.getStationIds());

        cr.setStationCapacity(10, 10);
        for (int i=0; i<10; i++){
            cr.addUnit(10, UnitType.AMBULANCE);
        }


        try {
            cr.setStationCapacity(3, 10);
        } catch (Exception e) {
            System.out.print("Expected behaviour: No such station Id\n->");
            System.out.println(e);
        }

        try {
            cr.setStationCapacity(10, 3);
        } catch (Exception e) {
            System.out.print("Expected behaviour: New capacity limit exceeds...\n-> ");
            System.out.println(e);
        }

        try {
            cr.setStationCapacity(13,-1);
        } catch (Exception e) {
            System.out.print("Expected behaviour: New capacity limit cannot be <1...\n-> ");
            System.out.println(e);
        }

        try {
            cr.removeStation(10);
        } catch (Exception e) {
            System.out.print("Expected behaviour: Station still has units\n-> ");
            System.out.println(e);
        }

        try {
            cr.removeStation(3);
        } catch (Exception e) {
            System.out.print("Expected behaviour: Station does not exist\n-> ");
            System.out.println(e);
        }
}
}
