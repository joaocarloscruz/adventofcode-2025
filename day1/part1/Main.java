import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        // run part 1
        System.out.println(part1());


    }

    protected static List<String> readInput(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static int part1() throws IOException {
        List<String> lines = readInput("day1/part1/input.txt");
        int password = 0;
        int dial = 50;
        for (String line : lines) {
            char direction = line.charAt(0);
            int amount = Integer.parseInt(line.substring(1));

            if (direction == 'L') {
                dial = (dial - amount) % 100; // java produces negative numbers with modulus
                if (dial < 0) {
                    dial += 100;
                }
            } else {
                dial = (dial + amount) % 100;
            }

            if (dial == 0) {
                password++;
            }
        }   
        return password;
    }
}
