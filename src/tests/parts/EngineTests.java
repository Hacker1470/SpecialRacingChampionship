package tests.parts;

import data.catalogs.CatalogOfParts;
import data.parts.Engine;
import data.parts.PartBrokeException;
import data.parts.enums.PartType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EngineTests {
    Engine engine;

    @BeforeEach
    public void init() {
        engine = new Engine(
                42,
                "article",
                "name",
                "postfix",
                300,
                20,
                5,
                6,
                7,
                List.of("chas_1_0", "tran_2_0"),
                9,
                10
        );
    }

    @DisplayName("Проверка геттеров родительских полей")
    @Test
    public void basePropertiesTest() {
        assertEquals(42, engine.getId());
        assertEquals(PartType.ENGINE, engine.getType());
        assertEquals("article", engine.getArticle());
        assertEquals("name", engine.getName());
        assertEquals("postfix", engine.getPostfix());
        assertEquals(300, engine.getStockPrice());
        assertEquals(20, engine.getQuality());
        assertEquals(5, engine.getMass());
        assertEquals(6, engine.getDamage());
        assertEquals(0, engine.getConnectionReliability());
        assertEquals(7, engine.getReputationLevel());
        assertEquals(List.of("chas_1_0", "tran_2_0"), engine.getConnectivity());
    }

    @DisplayName("Проверка изменения родительского поля postfix")
    @Test
    public void basePostfixTest() {
        engine.setPostfix("");
        assertEquals("", engine.getPostfix());
        engine.setPostfix("postfix");
        assertEquals("postfix", engine.getPostfix());
        engine.setPostfix(null);
        assertEquals("", engine.getPostfix());
        engine.setPostfix("postfix");
    }

    @DisplayName("Проверка сеттера родительского поля damage")
    @Test
    public void baseDamageTest() {
        engine.setDamage_75();
        assertEquals(75, engine.getDamage());

        try {
            engine.setDamage(-1);
            assertEquals(0, engine.getDamage());
            engine.setDamage(0);
            assertEquals(0, engine.getDamage());
            engine.setDamage(50);
            assertEquals(50, engine.getDamage());
            engine.setDamage(99);
            assertEquals(99, engine.getDamage());
        } catch (PartBrokeException e) {
            fail("Ошибки быть не должно");
        }

        try {
            engine.setDamage(100);
            fail("Ошибка должна быть, но её не произошло");
        } catch (PartBrokeException e) {
            assertEquals("Сломалос name postfix\n\tdamage 99,00 -> 100,00", e.getMessage());
            assertEquals(100, engine.getDamage());
        }

        try {
            engine.setDamage(120);
            fail("Ошибка должна быть, но её не произошло");
        } catch (PartBrokeException e) {
            assertEquals("Сломалос name postfix\n\tdamage 100,00 -> 120,00", e.getMessage());
            assertEquals(100, engine.getDamage());
        }
    }

    @DisplayName("Проверка сеттера родительского поля connectionReliability")
    @Test
    public void baseConnectionReliabilityTest() {
        engine.setConnectionReliability(-1);
        assertEquals(0, engine.getConnectionReliability());
        engine.setConnectionReliability(0);
        assertEquals(0, engine.getConnectionReliability());
        engine.setConnectionReliability(50);
        assertEquals(50, engine.getConnectionReliability());
        engine.setConnectionReliability(100);
        assertEquals(100, engine.getConnectionReliability());
        engine.setConnectionReliability(101);
        assertEquals(100, engine.getConnectionReliability());
    }

    @DisplayName("Проверка геттеров собственных полей")
    @Test
    public void ownPropertiesTest() {
        assertEquals(9, engine.getPower());
        assertEquals(10, engine.getMaxRpm());
    }

    @DisplayName("Проверка подсчёта getRealPrice")
    @Test
    public void realPriceCalculationTest() {
        try {
            engine.setDamage(0);
        } catch (PartBrokeException e) {
            fail("Ошибки быть не должно");
        }
        assertEquals(360, engine.getRealPrice());
        try {
            engine.setDamage(50);
        } catch (PartBrokeException e) {
            fail("Ошибки быть не должно");
        }
        assertEquals(210, engine.getRealPrice());
        try {
            engine.setDamage(100);
            fail("Ошибка должна быть, но её не произошло");
        } catch (PartBrokeException e) {
            assertEquals(60, engine.getRealPrice());
        }
    }

    @DisplayName("Проверка методов get[Market/Warehouse]Characteristics")
    @Test
    public void stringCharacteristicsCreationTest() {
        CatalogOfParts.init();

        assertEquals("Название: name postfix\n" + "Качество: 20\n" +
                "Масса: 5 кг\n" + "Износ: 6.0 %\n" +
                "Мощность: 9 л/с\n" +
                "Максимальные обороты: 10 об/мин\n\n" + "Совместимость:\n" +
                "* " + CatalogOfParts.getByArticle("chas_1_0").getName() + "\n" +
                "* " + CatalogOfParts.getByArticle("tran_2_0").getName() + "\n" +
                "\nСтоимость: 342", engine.getMarketCharacteristics());

        engine.setConnectionReliability(30);
        assertEquals("Название: name postfix\n" + "Качество: 20\n" +
                "Масса: 5 кг\n" + "Износ: 6.0 %\n" +
                "Мощность: 9 л/с\n" +
                "Максимальные обороты: 10 об/мин\n\n" + "Совместимость:\n" +
                "* " + CatalogOfParts.getByArticle("chas_1_0").getName() + "\n" +
                "* " + CatalogOfParts.getByArticle("tran_2_0").getName() + "\n" +
                "\nКачество соединения: 30", engine.getGarageCharacteristics());
    }

    @DisplayName("Проверка метода получения копии")
    @Test
    public void getCopyMethodTest() {
        Engine eCopy = (Engine) engine.getCopy(200L);
        assertEquals(200L, eCopy.getId());
        assertEquals(PartType.ENGINE, eCopy.getType());
        assertEquals("article", eCopy.getArticle());
        assertEquals("name", eCopy.getName());
        assertEquals("postfix", eCopy.getPostfix());
        assertEquals(300, eCopy.getStockPrice());
        assertEquals(20, eCopy.getQuality());
        assertEquals(5, eCopy.getMass());
        assertEquals(6, eCopy.getDamage());
        assertEquals(0, eCopy.getConnectionReliability());
        assertEquals(7, eCopy.getReputationLevel());
        assertEquals(List.of("chas_1_0", "tran_2_0"), eCopy.getConnectivity());
        assertEquals(9, eCopy.getPower());
        assertEquals(10, eCopy.getMaxRpm());
    }

    //getBaseDamage без контекста не рассматривается
}
