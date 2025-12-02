import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        // run part 1
        System.out.println(part1());


    }

    public static int part1() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day1/part2/input.txt"));        
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
