import data.GameSession;
import data.partslists.CatalogOfParts;
import data.partslists.EnginesList;
import data.partslists.TransmissionList;
import ui.MainMenu;
import vehicle.Part;
import vehicle.Wheels;

void main() {
    GameSession gm = new GameSession(0, 1000);
    CatalogOfParts.catalogInit();
    //TransmissionList.initializeTransmission();
    gm.launch();
}
