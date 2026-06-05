package tests.employees;

import data.crew.Engineer;
import data.crew.JobType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineerTests {
    Engineer engi;

    @BeforeEach
    public void init() {
        engi = new Engineer(
                42,
                "article",
                "name",
                "postfix",
                300,
                5,
                20,
                40,
                60
        );
    }

    @DisplayName("Проверка геттеров родительских полей")
    @Test
    public void basePropertiesTest() {
        assertEquals(42, engi.getId());
        assertEquals(JobType.ENGINEER, engi.getType());
        assertEquals("article", engi.getArticle());
        assertEquals("name", engi.getName());
        assertEquals(300, engi.getStockFee());
        assertEquals(5, engi.getExperience());
        assertEquals(20, engi.getReputationLevel());
    }

    @DisplayName("Проверка изменения родительского поля postfix")
    @Test
    public void basePostfixTest() {
        engi.setPostfix("");
        assertEquals("", engi.getPostfix());
        engi.setPostfix("postfix");
        assertEquals("postfix", engi.getPostfix());
        engi.setPostfix(null);
        assertEquals("", engi.getPostfix());
        engi.setPostfix("postfix");
    }

    @DisplayName("Проверка сеттера родительского поля experience")
    @Test
    public void baseExperienceTest() {
        engi.setExperience(-1);
        assertEquals(0, engi.getExperience());
        engi.setExperience(1000);
        assertEquals(1000, engi.getExperience());
        engi.setExperience(0);
        assertEquals(0, engi.getExperience());
    }

    @DisplayName("Проверка геттеров собственных полей")
    @Test
    public void ownPropertiesTest() {
        assertEquals(40, engi.getWavyHands());
        assertEquals(60, engi.getScrewing());
    }

    @DisplayName("Проверка подсчёта getHiringCost")
    @Test
    public void hiringCostCalculationTest() {
        assertEquals(387, engi.getHiringCost());
        engi.setExperience(0);
        assertEquals(372, engi.getHiringCost());
        engi.setExperience(30);
        assertEquals(462, engi.getHiringCost());
    }

    @DisplayName("Проверка подсчёта getSalary")
    @Test
    public void salaryCalculationTest() {
        assertEquals(375, engi.getSalary(1));
        engi.setExperience(0);
        assertEquals(300, engi.getSalary(1));
        engi.setExperience(30);
        assertEquals(750, engi.getSalary(1));
    }

    @DisplayName("Проверка методов get[Employment/Dorm]Characteristics")
    @Test
    public void stringCharacteristicsCreationTest() {

        assertEquals("Имя: name postfix\n" + "Опыт работы: 5\n" +
                "Криворукость: 40 %\n" + "БОЛТология: 60 %\n" +
                "Мин ЗПшка: 300 грошей\n" +
                "\nСтоимость найма: 387", engi.getEmploymentCharacteristics());

        assertEquals("Имя: name postfix\n" + "Опыт работы: 5\n" +
                "Криворукость: 40 %\n" + "БОЛТология: 60 %\n" +
                "Мин ЗПшка: 300 грошей\n", engi.getDormCharacteristics());
    }

    @DisplayName("Проверка метода получения копии")
    @Test
    public void getCopyMethodTest() {
        Engineer eCopy = (Engineer) engi.getCopy(200L);
        assertEquals(200L, eCopy.getId());
        assertEquals(JobType.ENGINEER, eCopy.getType());
        assertEquals("article", eCopy.getArticle());
        assertEquals("name", eCopy.getName());
        assertEquals("postfix", eCopy.getPostfix());
        assertEquals(300, eCopy.getStockFee());
        assertEquals(5, eCopy.getExperience());
        assertEquals(20, eCopy.getReputationLevel());
        assertEquals(40, eCopy.getWavyHands());
        assertEquals(60, eCopy.getScrewing());
    }
}
