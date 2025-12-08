import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part1());
    }

    private static class Connection {
        public int boxA;
        public int boxB;
        public double distance;

        public Connection(int boxA, int boxB, double distance) {
            this.boxA = boxA;
            this.boxB = boxB;
            this.distance = distance;
        }
    }

    private static class Circuit {
        List<Integer> boxes;
        boolean isMerged;

        Circuit(int box) {
            this.boxes = new ArrayList<>();
            this.boxes.add(box);
            this.isMerged = false;
        }
    }

    public static int part1() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day8/part1/input.txt"));
        int height = lines.size();
        List<Connection> connections = new ArrayList<>();

        // Parse input and build connections
        for (int i = 0; i < height; i++) {
            String line = lines.get(i);
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            int z = Integer.parseInt(coords[2]);
            for (int j = i + 1; j < height; j++) {
                String line2 = lines.get(j);
                String[] coords2 = line2.split(",");
                int x2 = Integer.parseInt(coords2[0]);
                int y2 = Integer.parseInt(coords2[1]);
                int z2 = Integer.parseInt(coords2[2]);
                double distance = Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2) + Math.pow(z - z2, 2));
                connections.add(new Connection(i, j, distance));
            }
        }

        connections.sort((a, b) -> Double.compare(a.distance, b.distance));

        List<Circuit> circuits = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            circuits.add(new Circuit(i));
        }

        int n = 1000; 
        for (int i = 0; i < n; i++) {
            Connection con = connections.get(i);
            int boxA = con.boxA;
            int boxB = con.boxB;

            int circuitA = -1, circuitB = -1;
            for (int j = 0; j < circuits.size(); j++) {
                if (!circuits.get(j).isMerged && circuits.get(j).boxes.contains(boxA)) {
                    circuitA = j;
                }
                if (!circuits.get(j).isMerged && circuits.get(j).boxes.contains(boxB)) {
                    circuitB = j;
                }
            }

            if (circuitA != -1 && circuitB != -1 && circuitA != circuitB) {
                if (circuits.get(circuitA).boxes.size() < circuits.get(circuitB).boxes.size()) {
                    circuits.get(circuitB).boxes.addAll(circuits.get(circuitA).boxes);
                    circuits.get(circuitA).isMerged = true;
                } else {
                    circuits.get(circuitA).boxes.addAll(circuits.get(circuitB).boxes);
                    circuits.get(circuitB).isMerged = true;
                }
            }
            if (circuitA != -1 && circuitB == -1) {
                circuits.get(circuitA).boxes.add(boxB);
            }
            if (circuitA == -1 && circuitB != -1) {
                circuits.get(circuitB).boxes.add(boxA);
            } 
            if (circuitA == -1 && circuitB == -1) {
                circuits.add(new Circuit(boxA));
                circuits.get(circuits.size() - 1).boxes.add(boxB);
            }
        }

        List<Integer> sizes = new ArrayList<>();
        for (Circuit c : circuits) {
            if (!c.isMerged) {
                sizes.add(c.boxes.size());
            }
        }

        sizes.sort((a, b) -> b - a);

        return sizes.get(0) * sizes.get(1) * sizes.get(2);
    }
}
