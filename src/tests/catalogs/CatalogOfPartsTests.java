package tests.catalogs;

import data.catalogs.CatalogOfParts;
import data.employeeslists.EngineerList;
import data.partslists.*;
import data.vehicle.Part;
import data.vehicle.enums.PartType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogOfPartsTests {
    @BeforeAll
    public static void initCatalog(){
        CatalogOfParts.catalogInit();
    }

    @DisplayName("Проверка получения детали по артикулу")
    @Test
    public void getByArticleTest(){
        assertEquals(ChassisList.ladaTazik, CatalogOfParts
                .getPartWithArticle("chas_2_0"));
        assertEquals(EnginesList.pedal, CatalogOfParts
                .getPartWithArticle("engi_1_0"));
        assertEquals(TransmissionList.magnum, CatalogOfParts
                .getPartWithArticle("tran_3_0"));
        assertEquals(WheelsList.normWheels, CatalogOfParts
                .getPartWithArticle("whel_2_0"));
        assertEquals(SuspensionList.baseSpring, CatalogOfParts
                .getPartWithArticle("susp_1_0"));
        assertEquals(DownforcePartList.spoiler, CatalogOfParts
                .getPartWithArticle("dwfr_2_0"));
    }

    @DisplayName("Проверка поиска деталей по типу и по репутации")
    @Test
    public void getPartsByTypeAndRepTest(){
        assertEquals(new ArrayList<>(List.of(ChassisList.woodenBox)),
                CatalogOfParts.getAvailableByReputation(PartType.CHASSIS, 0));
        assertEquals(new ArrayList<>(),
                CatalogOfParts.getAvailableByReputation(PartType.DOWNFORCE, -1));
        assertEquals(new ArrayList<>(List.of(EnginesList.pedal, EnginesList.lada)),
                CatalogOfParts.getAvailableByReputation(PartType.ENGINE, 10));
        assertEquals(new ArrayList<>(List.of(TransmissionList.chain,
                        TransmissionList.ladabox, TransmissionList.magnum)),
                CatalogOfParts.getAvailableByReputation(PartType.TRANSMISSION, 30));

    }

    @DisplayName("Проверка поиска деталей только по репутации")
    @Test
    public void getPartsByOnlyRepTest(){
        ArrayList<Part> expected, actual;

        //rep = 0
        expected = new ArrayList<Part>(List.of(
                ChassisList.woodenBox,
                EnginesList.pedal,
                TransmissionList.chain,
                WheelsList.bicycleWheels
        ));
        expected.sort(Comparator.comparing(Part::getArticle));

        actual = CatalogOfParts.getAvailableByReputation(0);
        actual.sort(Comparator.comparing(Part::getArticle));

        assertEquals(expected, actual);

        //rep = 9
        expected = new ArrayList<Part>(List.of(
                ChassisList.woodenBox,
                ChassisList.ladaTazik,
                EnginesList.pedal,
                TransmissionList.chain,
                WheelsList.bicycleWheels,
                WheelsList.normWheels,
                WheelsList.niceWheels,
                SuspensionList.baseSpring,
                DownforcePartList.bricks
        ));
        expected.sort(Comparator.comparing(Part::getArticle));

        actual = CatalogOfParts.getAvailableByReputation(9);
        actual.sort(Comparator.comparing(Part::getArticle));

        assertEquals(expected, actual);
    }
}
