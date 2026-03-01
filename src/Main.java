import data.GameSession;
import data.partslists.EnginesList;
import data.partslists.TransmissionList;
import ui.MainMenu;
import vehicle.Part;
import vehicle.Wheels;

void main() {
    GameSession gm = new GameSession();
    EnginesList.initializeEngines();
    TransmissionList.initializeTransmission();
    gm.launch();
}
