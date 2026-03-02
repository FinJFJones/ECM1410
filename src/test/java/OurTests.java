import org.junit.jupiter.api.BeforeEach;

import cityrescue.CityRescue;
import cityrescue.CityRescueImpl;

public class OurTests {
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5, 5);
    }

}