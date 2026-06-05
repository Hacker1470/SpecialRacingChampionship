package tests.race;

import data.crew.Pilot;
import data.employeeslists.PilotList;
import data.parts.*;
import data.partslists.ChassisList;
import data.partslists.EnginesList;
import data.partslists.TransmissionList;
import data.partslists.WheelsList;
import data.race.calculators.TimeCalculator;
import data.race.map.RaceTrack;
import data.race.map.enums.SurfaceType;
import data.race.map.enums.WeatherType;
import data.race.map.terrains.StraightRoad;
import data.racecar.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorsTests {

    public Racecar racecar;
    public Pilot pilot;

    @BeforeEach
    public void init() {
        pilot = (Pilot) PilotList.cheboks.getCopy(Long.MIN_VALUE);

        racecar = new Racecar(
                Long.MIN_VALUE,
                "Болид",
                (Chassis) ChassisList.woodenBox.getCopy(Long.MIN_VALUE),
                (Engine) EnginesList.pedal.getCopy(Long.MIN_VALUE),
                (Transmission) TransmissionList.chain.getCopy(Long.MIN_VALUE),
                (Wheels) WheelsList.bicycleWheels.getCopy(Long.MIN_VALUE),
                null,
                null
        );

        for(Part p : racecar.getNotNullParts()){
            p.setConnectionReliability(50);
        }
    }

    @DisplayName("Проверка общего влияния пилота на гонку")
    @Test
    public void WeatherEffectTest() {
        TimeCalculator tc;
        RaceTrack rt = new RaceTrack("Карта", List.of(new StraightRoad(20, SurfaceType.ASPHALT)));
        rt.setWeather(WeatherType.SUNNY);

        tc = new TimeCalculator(racecar, (Pilot) PilotList.cheboks.getCopy(Long.MIN_VALUE));
        double badPilotTime = tc.calculate(rt, rt.getTerrainByNumber(0));

        tc = new TimeCalculator(racecar, (Pilot) PilotList.schumacher.getCopy(Long.MIN_VALUE));
        double goodPilotTime = tc.calculate(rt, rt.getTerrainByNumber(0));

        assertTrue(goodPilotTime < badPilotTime);
    }

    @DisplayName("Проверка влияния опыта пилота на гонку")
    @Test
    public void ExperienceEffectTest() {
        TimeCalculator tc;
        RaceTrack rt = new RaceTrack("Карта", List.of(new StraightRoad(20, SurfaceType.ASPHALT)));
        rt.setWeather(WeatherType.SUNNY);

        tc = new TimeCalculator(racecar, pilot);
        double badPilotTime = tc.calculate(rt, rt.getTerrainByNumber(0));

        pilot.setExperience(pilot.getExperience() + 1);

        tc = new TimeCalculator(racecar, (Pilot) PilotList.schumacher.getCopy(Long.MIN_VALUE));
        double goodPilotTime = tc.calculate(rt, rt.getTerrainByNumber(0));

        assertTrue(goodPilotTime < badPilotTime);
    }

    @DisplayName("Проверка влияния погоды на гонку")
    @Test
    public void PilotEffectTest() {
        TimeCalculator tc = new TimeCalculator(racecar, pilot);
        RaceTrack rt = new RaceTrack("Карта", List.of(new StraightRoad(20, SurfaceType.ASPHALT)));

        rt.setWeather(WeatherType.SUNNY);
        double goodWeatherTime = tc.calculate(rt, rt.getTerrainByNumber(0));

        rt.setWeather(WeatherType.RAINING);
        double badWeatherTime = tc.calculate(rt, rt.getTerrainByNumber(0));

        assertTrue(goodWeatherTime < badWeatherTime);
    }

    @DisplayName("Проверка влияния перков, выдаваемых инженером, на гонку")
    @Test
    public void EngineerEffectTest() {
        TimeCalculator tc;
        RaceTrack rt = new RaceTrack("Карта", List.of(new StraightRoad(20, SurfaceType.ASPHALT)));

        for(Part p : racecar.getNotNullParts()){
            p.setConnectionReliability(20);
        }
        tc = new TimeCalculator(racecar, pilot);
        double badEngineerTime = tc.calculate(rt, rt.getTerrainByNumber(0));

        for(Part p : racecar.getNotNullParts()){
            p.setConnectionReliability(80);
        }
        tc = new TimeCalculator(racecar, pilot);
        double goodEngineerTime = tc.calculate(rt, rt.getTerrainByNumber(0));

        assertTrue(goodEngineerTime < badEngineerTime);
    }
}
