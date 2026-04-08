package tests.parts;

import data.catalogs.CatalogOfParts;
import data.vehicle.Chassis;
import data.vehicle.PartBrokeException;
import data.vehicle.enums.PartType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChassisTests {
    Chassis chassis;

    @BeforeEach
    public void init(){
        chassis = new Chassis(
                42,
                "article",
                "name",
                "postfix",
                300,
                20,
                5,
                6,
                7,
                List.of("engi_1_0", "tran_2_0"),
                9,
                10
        );
    }

    @DisplayName("Проверка геттеров родительских полей")
    @Test
    public void basePropertiesTest(){
        assertEquals(42, chassis.getId());
        assertEquals(PartType.CHASSIS, chassis.getType());
        assertEquals("article", chassis.getArticle());
        assertEquals("name", chassis.getName());
        assertEquals("postfix", chassis.getPostfix());
        assertEquals(300, chassis.getStockPrice());
        assertEquals(20, chassis.getQuality());
        assertEquals(5, chassis.getMass());
        assertEquals(6, chassis.getDamage());
        assertEquals(0, chassis.getConnectionReliability());
        assertEquals(7, chassis.getReputationLevel());
        assertEquals(List.of("engi_1_0", "tran_2_0"), chassis.getConnectivity());
    }

    @DisplayName("Проверка изменения родительского поля postfix")
    @Test
    public void basePostfixTest(){
        chassis.setPostfix("");
        assertEquals("", chassis.getPostfix());
        chassis.setPostfix("postfix");
        assertEquals("postfix", chassis.getPostfix());
        chassis.setPostfix(null);
        assertEquals("", chassis.getPostfix());
    }

    @DisplayName("Проверка сеттера родительского поля damage")
    @Test
    public void baseDamageTest(){
        chassis.setDamage_75();
        assertEquals(75, chassis.getDamage());

        try{
            chassis.setDamage(-1);
            assertEquals(0, chassis.getDamage());
            chassis.setDamage(0);
            assertEquals(0, chassis.getDamage());
            chassis.setDamage(50);
            assertEquals(50, chassis.getDamage());
            chassis.setDamage(99);
            assertEquals(99, chassis.getDamage());
        }
        catch (PartBrokeException e){
            fail("Ошибки быть не должно");
        }

        try{
            chassis.setDamage(100);
            fail("Ошибка должна быть, но её не произошло");
        }
        catch (PartBrokeException e){
            assertEquals("Сломалос name postfix\n\tdamage 99,00 -> 100,00", e.getMessage());
            assertEquals(100, chassis.getDamage());
        }

        try{
            chassis.setDamage(120);
            fail("Ошибка должна быть, но её не произошло");
        }
        catch (PartBrokeException e){
            assertEquals("Сломалос name postfix\n\tdamage 100,00 -> 120,00", e.getMessage());
            assertEquals(100, chassis.getDamage());
        }
    }

    @DisplayName("Проверка сеттера родительского поля connectionReliability")
    @Test
    public void baseConnectionReliabilityTest(){
        chassis.setConnectionReliability(-1);
        assertEquals(0, chassis.getConnectionReliability());
        chassis.setConnectionReliability(0);
        assertEquals(0, chassis.getConnectionReliability());
        chassis.setConnectionReliability(50);
        assertEquals(50, chassis.getConnectionReliability());
        chassis.setConnectionReliability(100);
        assertEquals(100, chassis.getConnectionReliability());
        chassis.setConnectionReliability(101);
        assertEquals(100, chassis.getConnectionReliability());
    }

    @DisplayName("Проверка геттеров собственных полей")
    @Test
    public void ownPropertiesTest(){
        assertEquals(9, chassis.getAerodynamics());
        assertEquals(10, chassis.getMaxWeight());
    }

    @DisplayName("Проверка подсчёта getRealPrice")
    @Test
    public void realPriceCalculationTest(){
        try {
            chassis.setDamage(0);
        }
        catch (PartBrokeException e){
            fail("Ошибки быть не должно");
        }
        assertEquals(360, chassis.getRealPrice());
        try {
            chassis.setDamage(50);
        }
        catch (PartBrokeException e){
            fail("Ошибки быть не должно");
        }
        assertEquals(210, chassis.getRealPrice());
        try {
            chassis.setDamage(100);
            fail("Ошибка должна быть, но её не произошло");
        }
        catch (PartBrokeException e){
            assertEquals(60, chassis.getRealPrice());
        }
    }

    @DisplayName("Проверка методов get[Market/Warehouse]Characteristics")
    @Test
    public void stringCharacteristicsCreationTest() {
        CatalogOfParts.catalogInit();

        assertEquals("Название: name postfix\n" + "Качество: 20\n" +
                "Масса: 5 кг\n" + "Износ: 6.0 %\n" +
                "Обтекаемость: 9\n" +
                "Макс. суммарная масса оборудования: 10 кг\n\n" + "Совместимость:\n" +
                "* " + CatalogOfParts.getPartWithArticle("engi_1_0").getName() + "\n" +
                "* " + CatalogOfParts.getPartWithArticle("tran_2_0").getName() + "\n" +
                "\nСтоимость: 342", chassis.getMarketCharacteristics());

        chassis.setConnectionReliability(30);
        assertEquals("Название: name postfix\n" + "Качество: 20\n" +
                "Масса: 5 кг\n" + "Износ: 6.0 %\n" +
                "Обтекаемость: 9\n" +
                "Макс. суммарная масса оборудования: 10 кг\n\n" + "Совместимость:\n" +
                "* " + CatalogOfParts.getPartWithArticle("engi_1_0").getName() + "\n" +
                "* " + CatalogOfParts.getPartWithArticle("tran_2_0").getName() + "\n" +
                "\nКачество соединения: 30", chassis.getGarageCharacteristics());
    }

    @DisplayName("Проверка метода получения копии")
    @Test
    public void getCopyMethodTest(){
        Chassis eCopy = (Chassis) chassis.getCopy(200L);
        assertEquals(200L, eCopy.getId());
        assertEquals(PartType.CHASSIS, eCopy.getType());
        assertEquals("article", eCopy.getArticle());
        assertEquals("name", eCopy.getName());
        assertEquals("postfix", eCopy.getPostfix());
        assertEquals(300, eCopy.getStockPrice());
        assertEquals(20, eCopy.getQuality());
        assertEquals(5, eCopy.getMass());
        assertEquals(6, eCopy.getDamage());
        assertEquals(0, eCopy.getConnectionReliability());
        assertEquals(7, eCopy.getReputationLevel());
        assertEquals(List.of("engi_1_0", "tran_2_0"), eCopy.getConnectivity());
        assertEquals(9, eCopy.getAerodynamics());
        assertEquals(10, eCopy.getMaxWeight());
    }

    //getBaseDamage без контекста не рассматривается
}
