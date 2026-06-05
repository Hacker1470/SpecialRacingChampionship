package ui.garage;

import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.garage.assembly.CarAssemblyTab;

public class GarageTab extends Tab {

    public GarageTab(GameSession gm) {
        super(gm);
    }

    @Override
    public Tab show() {
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString("ВАШ ГАРАЖ");

        if (gm.garage().getCarsNumber() > 0) {
            printCarsCatalog();
        } else {
            printEmptyGarage();
        }

        gm.io().printlnString("[+] Собрать новый автомобиль");
        gm.io().printlnString("[0] Выход в меню");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printCarsCatalog() {
        gm.io().printlnString("В гараже стоят следующие авто");

        gm.io().printlnString(gm.garage().generateStringCatalog());

        gm.io().printlnString("");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[N] Открыть карточку машины N");
    }

    private void printEmptyGarage() {
        gm.io().printlnString("Гараж пустует");
        gm.io().printlnString("");
        gm.io().printlnString("=============================================");
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;


        while (response == null) {
            request = gm.io().getString();

            response = selectResponse(request);
            if (response == null) {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req) {
        if (req.equals("0")) {
            return new MainTab(gm);
        }
        if (req.equals("+")) {
            return new CarAssemblyTab(gm);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        } catch (NumberFormatException e) {
            return null;
        }

        if (gm.garage().getCarsNumber() > 0 && index >= 1 && index <= gm.garage().getCarsNumber()) {
            return new GarageCarInfoTab(gm, gm.garage().getCarById(gm.garage().getKeys().get(index - 1)));
        } else {
            return null;
        }
    }
}
