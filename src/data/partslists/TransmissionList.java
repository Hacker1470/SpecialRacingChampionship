package data.partslists;

import vehicle.Engine;
import vehicle.Transmission;

import java.util.HashMap;
import java.util.List;

public class TransmissionList {
    public static Transmission chain = new Transmission(
            "tran_1_0",
            "Цепная передача",
            0,
            10,
            50,
            30,
            1,
            List.of("engi_1_0"),
            15,
            1
            );

    public static Transmission ladabox = new Transmission(
            "tran_2_0",
            "КПП ZovAuto",
            20,
            200,
            10,
            700,
            10,
            List.of("engi_1_0", "engi_2_0"),
            90,
            5);

    public static Transmission magnum = new Transmission(
            "tran_3_0",
            "КПП Magnum",
            50,
            320,
            5,
            1400,
            27,
            List.of("engi_2_0"),
            125,
            8);

    public static HashMap<String, Transmission> listOfTransmission = new HashMap<>();

    public static void initializeTransmission(){
        listOfTransmission.put(chain.getId(), chain);
        listOfTransmission.put(ladabox.getId(), ladabox);
        listOfTransmission.put(magnum.getId(), magnum);
    }

    public static HashMap<Integer, Transmission> getAvailableByReputation(int rep){
        HashMap<Integer, Transmission> availableTransmission = new HashMap<>();
        int counter = 0;
        for (Transmission i : TransmissionList.listOfTransmission.values()){
            if(i.getReputationLevel() <= rep){
                availableTransmission.put(++counter, i);
            }
        }
        return availableTransmission;
    }
}
