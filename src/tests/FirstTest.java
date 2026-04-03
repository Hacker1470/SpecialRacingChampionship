package tests;

import data.vehicle.Engine;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {
    @Test
    public void test(){
        Engine e = new Engine(
                42,
                "a",
                "b",
                3,
                4,
                5,
                6,
                7,
                List.of("c", "d", "e"),
                9,
                10
        );
        assertEquals(42, e.getId());
        assertEquals("a", e.getArticle());
        assertEquals("b", e.getName());
        assertEquals(3, e.getStockPrice());
        assertEquals(4, e.getQuality());
        assertEquals(5, e.getMass());
        assertEquals(6, e.getDamage());
        assertEquals(7, e.getReputationLevel());
        assertEquals(List.of("c", "d", "e"), e.getConnectivity());
        assertEquals(9, e.getPower());
        assertEquals(10, e.getMaxRpm());
    }
}
