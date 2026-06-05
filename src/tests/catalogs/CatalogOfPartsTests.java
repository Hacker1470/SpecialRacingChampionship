package tests.catalogs;

import data.catalogs.CatalogOfParts;
import data.parts.Part;
import data.parts.enums.PartType;
import data.partslists.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogOfPartsTests {
    @BeforeAll
    public static void initCatalog() {
        CatalogOfParts.init();
    }

    @DisplayName("Проверка получения детали по артикулу")
    @Test
    public void getByArticleTest() {
        Part found;

        found = CatalogOfParts.getByArticle("chas_2_0");
        assertEquals(ChassisList.ladaTazik.getArticle(), found.getArticle());

        found = CatalogOfParts.getByArticle("engi_1_0");
        assertEquals(EnginesList.pedal.getArticle(), found.getArticle());

        found = CatalogOfParts.getByArticle("tran_3_0");
        assertEquals(TransmissionList.magnum.getArticle(), found.getArticle());

        found = CatalogOfParts.getByArticle("whel_2_0");
        assertEquals(WheelsList.normWheels.getArticle(), found.getArticle());

        found = CatalogOfParts.getByArticle("susp_1_0");
        assertEquals(SuspensionList.baseSpring.getArticle(), found.getArticle());

        found = CatalogOfParts.getByArticle("dwfr_2_0");
        assertEquals(DownforcePartList.spoiler.getArticle(), found.getArticle());
    }

    @DisplayName("Проверка поиска деталей по типу и по репутации")
    @Test
    public void getPartsByTypeAndRepTest() {
        ArrayList<Part> results;
        results = CatalogOfParts.getAvailableByReputation(PartType.CHASSIS, 0);
        assertTrue(results.stream().map(Part::getArticle).toList()
                .contains(ChassisList.woodenBox.getArticle()));

        results = CatalogOfParts.getAvailableByReputation(PartType.DOWNFORCE, -1);
        assertTrue(results.isEmpty());

        results = CatalogOfParts.getAvailableByReputation(PartType.ENGINE, 10);
        assertTrue(results.stream().map(Part::getArticle).toList()
                .containsAll(List.of(
                        EnginesList.pedal.getArticle(),
                        EnginesList.lada.getArticle()
                )));

        results = CatalogOfParts.getAvailableByReputation(PartType.TRANSMISSION, 30);
        assertTrue(results.stream().map(Part::getArticle).toList().containsAll(
                List.of(
                        TransmissionList.chain.getArticle(),
                        TransmissionList.ladabox.getArticle(),
                        TransmissionList.magnum.getArticle()
                )));
    }

    @DisplayName("Проверка поиска деталей только по репутации")
    @Test
    public void getPartsByOnlyRepTest() {
        ArrayList<Part> expected, actual;

        //rep = 0
        expected = new ArrayList<Part>(List.of(
                ChassisList.woodenBox.getCopy(Long.MIN_VALUE),
                EnginesList.pedal.getCopy(Long.MIN_VALUE),
                TransmissionList.chain.getCopy(Long.MIN_VALUE),
                WheelsList.bicycleWheels.getCopy(Long.MIN_VALUE)
        ));
        expected.sort(Comparator.comparing(Part::getArticle));

        actual = CatalogOfParts.getAvailableByReputation(0);
        actual.sort(Comparator.comparing(Part::getArticle));

        for (int i = 0; i < Math.min(expected.size(), actual.size()); i++) {
            assertEquals(expected.get(i).getArticle(), actual.get(i).getArticle());
        }

        //rep = 9
        expected = new ArrayList<Part>(List.of(
                ChassisList.woodenBox.getCopy(),
                ChassisList.ladaTazik.getCopy(),
                EnginesList.pedal.getCopy(),
                TransmissionList.chain.getCopy(),
                WheelsList.bicycleWheels.getCopy(),
                WheelsList.normWheels.getCopy(),
                WheelsList.niceWheels.getCopy(),
                SuspensionList.baseSpring.getCopy(),
                DownforcePartList.bricks.getCopy()
        ));
        expected.sort(Comparator.comparing(Part::getArticle));

        actual = CatalogOfParts.getAvailableByReputation(9);
        actual.sort(Comparator.comparing(Part::getArticle));

        for (int i = 0; i < Math.min(expected.size(), actual.size()); i++) {
            assertEquals(expected.get(i).getArticle(), actual.get(i).getArticle());
        }
    }
}
