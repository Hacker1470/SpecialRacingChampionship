package game;

import data.crew.Pilot;
import data.special.RandomGenerator;

import java.util.*;

public class Hospital {
    public class IllPilot {
        private final int startTime;
        private final int delta;
        private final Pilot pilot;

        public IllPilot(Pilot pilot, int startTime, int delta) {
            this.pilot = pilot;
            this.startTime = startTime;
            this.delta = delta;
        }

        public Pilot getPilot() {
            return pilot;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getDelta() {
            return delta;
        }
    }

    private ArrayList<IllPilot> illPilots;
    private final GameSession gm;

    public Hospital(GameSession gm) {
        this.gm = gm;
        illPilots = new ArrayList<>();
    }

    public void addNewIllPeople(ArrayList<Pilot> pilots) {
        int currentTime = getCurrentTime();
        for (Pilot p : pilots) {
            illPilots.add(new IllPilot(p, currentTime, RandomGenerator.getInteger(1, 3)));
        }
    }

    public String generateString() {
        StringBuilder sb = new StringBuilder(illPilots.size() * 40);
        int currentTime = getCurrentTime();
        int counter = 1;
        for (IllPilot ip : illPilots) {
            sb.append(counter).append(") ")
                    .append(ip.getPilot().getName()).append(" ").append(ip.getPilot().getPostfix())
                    .append(" (Осталось валяться ").append(ip.getDelta() - currentTime + ip.getStartTime())
                    .append(" гонок)\n");
            counter++;
        }

        sb.append("\n");

        return sb.toString();
    }

    public ArrayList<IllPilot> getAllEmployees() {
        return new ArrayList<>(illPilots);
    }

    public void update() {
        int currentTime = getCurrentTime();
        for (int i = 0; i < illPilots.size(); i++) {
            if (currentTime - illPilots.get(i).getStartTime() >= illPilots.get(i).getDelta()) {
                gm.dorm().put(illPilots.get(i).getPilot());
                illPilots.remove(illPilots.get(i));
            }
        }

        if (illPilots.isEmpty()) {
            gm.setHospital(null);
        }
    }

    private int getCurrentTime() {
        return gm.archive().size();
    }
}
