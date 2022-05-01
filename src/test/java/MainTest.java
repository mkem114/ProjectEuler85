import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void testCountRectangles() {
        assertEquals(1, Main.countRectangles(1,1));
        assertEquals(9, Main.countRectangles(2,2));
        assertEquals(18, Main.countRectangles(3,2));
    }
}
