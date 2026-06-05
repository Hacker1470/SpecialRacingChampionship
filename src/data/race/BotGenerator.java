package data.race;

import data.catalogs.CatalogOfEmployees;
import data.catalogs.CatalogOfParts;
import data.crew.Employee;
import data.crew.Engineer;
import data.crew.JobType;
import data.crew.Pilot;
import data.parts.*;
import data.race.teams.Team;
import data.special.AssemblingHelpers;
import data.special.RandomGenerator;
import data.racecar.*;
import data.parts.enums.PartType;
import game.GameSession;
import data.racecar.RacecarSample;
import ui.garage.assembly.assemblyexceptions.UnmatchingPartsAssemblyException;

import java.util.ArrayList;
import java.util.List;

public class BotGenerator {
    public static void activate(GameSession gm, Race race) {
        int targetRep;

        Racecar racecar;

        Pilot pilot;
        ArrayList<Employee> emps;

        ArrayList<Chassis> chaList;
        ArrayList<Engine> engList;
        ArrayList<Transmission> traList;
        ArrayList<Wheels> wheList;
        ArrayList<Suspension> susList;
        ArrayList<DownforcePart> dowList;
        ArrayList<Part> parts;

        ArrayList<ArrayList<Integer>> matrix;

        ArrayList<Integer> currentSet;
        RacecarSample rs;

        for (int i = 0; i < race.getRequiredTeamsNumber() - 1; i++) {
            targetRep = (int) Math.round(gm.getRep() * RandomGenerator.getDouble(0.8d, 1.2d));

            parts = CatalogOfParts.getAvailableByReputation(targetRep);
            chaList = new ArrayList<>(parts.stream()
                    .filter(part -> part.getType() == PartType.CHASSIS)
                    .map(part -> (Chassis) part)
                    .toList());
            engList = new ArrayList<>(parts.stream()
                    .filter(part -> part.getType() == PartType.ENGINE)
                    .map(part -> (Engine) part)
                    .toList());
            traList = new ArrayList<>(parts.stream()
                    .filter(part -> part.getType() == PartType.TRANSMISSION)
                    .map(part -> (Transmission) part)
                    .toList());
            wheList = new ArrayList<>(parts.stream()
                    .filter(part -> part.getType() == PartType.WHEELS)
                    .map(part -> (Wheels) part)
                    .toList());
            susList = new ArrayList<>(parts.stream()
                    .filter(part -> part.getType() == PartType.SUSPENSION)
                    .map(part -> (Suspension) part)
                    .toList());
            dowList = new ArrayList<>(parts.stream()
                    .filter(part -> part.getType() == PartType.DOWNFORCE)
                    .map(part -> (DownforcePart) part)
                    .toList());

            matrix = getMatrix(
                    chaList.size(),
                    engList.size(),
                    traList.size(),
                    wheList.size(),
                    susList.size(),
                    dowList.size()
            );

            rs = new RacecarSample();

            emps = CatalogOfEmployees.getAvailableByReputation(JobType.PILOT, targetRep);
            pilot = (Pilot) emps.get(RandomGenerator.getInteger(0, emps.size() - 1)).getCopy();

            emps = CatalogOfEmployees.getAvailableByReputation(JobType.ENGINEER, targetRep);
            rs.setEngineer((Engineer) emps.get(RandomGenerator.getInteger(0, emps.size() - 1)).getCopy());

            racecar = null;
            while (racecar == null) {
                /*
                 * 0 Генерация списка шасси.
                 * 1 Если список не пуст, то тогда взять случайное,
                 *       иначе ошибка.
                 * 2 Генерация списка двигателей
                 * 3 Проверка, есть ли совместимые с шасси двигатели.
                 *       Если список двигателей пуст, то тогда удаляем шасси из каталога шасси
                 *           и начинаем заново с точки 1.
                 *       Если есть, то тогда ищем их в каталоге.
                 *           Если в каталоге нет, то тогда удаляем шасси из каталога шасси
                 *               и начинаем заново с точки 1.
                 *       Если нет, то берём случайный.
                 * 4 Проверка коллизий: если двигатель с чем-то совместим,
                 *       а шасси - нет, то удаляем двигатель из списка двигателей
                 *       и начинаем заново с точки 2.
                 * 3 Проверка, есть ли совместимая с шасси трансмиссия
                 *       Если список трансмиссий пуст, то тогда удаляем шасси из каталога шасси
                 *           и начинаем заново с точки 1.
                 *       Если есть, то тогда ищем их в каталоге.
                 *           Если в каталоге нет, то тогда удаляем шасси из каталога шасси
                 *               и начинаем заново с точки 1.
                 *       Если нет, то берём случайный.
                 * 3 Если для какого-то из типов не пусто, то подбираются совместимые, иначе любое
                 */

                currentSet = matrix.get(RandomGenerator.getInteger(0, matrix.size() - 1));

                rs.setChassis(chaList.get(currentSet.get(0)));
                rs.setEngine(engList.get(currentSet.get(1)));
                rs.setTransmission(traList.get(currentSet.get(2)));
                rs.setWheels(wheList.get(currentSet.get(3)));
                if (currentSet.get(4) >= 0) {
                    rs.setSuspension(susList.get(currentSet.get(4)));
                }
                if (currentSet.get(5) >= 0) {
                    rs.setDownforcePart(dowList.get(currentSet.get(5)));
                }

                try {
                    AssemblingHelpers.checkPartsMatching(rs);
                    AssemblingHelpers.addEngineerPerks(rs);

                    racecar = new Racecar(Long.MIN_VALUE, "Болид", rs);
                    makeDamage(racecar);
                } catch (UnmatchingPartsAssemblyException e) {
                    matrix.remove(currentSet);
                }

                if (matrix.isEmpty()) {
                    throw new RuntimeException("Сломалос");
                }
            }

            race.putTeam(new Team("Бот " + (i + 1), racecar, pilot));
        }
    }

    private static ArrayList<ArrayList<Integer>> getMatrix(
            int chaMax, int engMax, int traMax, int wheMax, int susMax, int dowMax) {
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        for (int c = 0; c < chaMax; c++) {
            for (int e = 0; e < engMax; e++) {
                for (int t = 0; t < traMax; t++) {
                    for (int w = 0; w < wheMax; w++) {
                        for (int s = -1; s < susMax; s++) {
                            for (int d = -1; d < dowMax; d++) {
                                answer.add(new ArrayList<>(List.of(c, e, t, w, s, d)));
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }

    private static void makeDamage(Racecar racecar) {
        double damage;
        for (Part p : racecar.getNotNullParts()) {
            damage = Math.max(p.getDamage(), RandomGenerator.getDouble(30d, 70d));

            try {
                p.setDamage(damage);
            } catch (PartBrokeException e) {
                p.setDamage_75();
            }
        }
    }
}
