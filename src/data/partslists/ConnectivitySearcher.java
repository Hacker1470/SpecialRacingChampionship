package data.partslists;

import vehicle.Engine;
import vehicle.Part;
import vehicle.Transmission;

import java.util.ArrayList;
import java.util.List;

public class ConnectivitySearcher {

    public static List<Part> findAllConnectivities(Part part){
        List<Part> response = new ArrayList<>();
        for(String i : part.getConnectivity()){
            if(i.startsWith("tran") && TransmissionList.listOfTransmission.get(i) != null){
                response.add(TransmissionList.listOfTransmission.get(i));
            }
        }

        return response;
    }


//    public static List<Part> findConnectivities(Part part, Class<Part> requiredType){
//        List<Part> response = new ArrayList<>();
//
//        //Закончил здесь. Основная мысль: мне в карточке детали нужно по отдельности выводить, с какими деталями
//        //совместима данная деталь. Например, хочу узнать с какими коробками совместим движок, и он мне должен
//        //на основе списка part.connectivity и класса вернуть список с id-шниками деталей нужного типа
          //upd 01 03 2026: можно, а зачем?

//        String targetId;
//        if(Engine.class instanceof requiredType.g){
//
//        }
//
//        switch (requiredType.getClass().){
//            case:
//
//        }
//        for(String i : part.getConnectivity()){
//            if(i.startsWith("tran") && TransmissionList.listOfTransmission.get(i) != null){
//                response.add(TransmissionList.listOfTransmission.get(i));
//            }
//        }
//
//        return response;
//    }
}
