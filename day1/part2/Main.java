import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part2());
    }

    public static int part2() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day1/part2/input.txt"));
        int password = 0;
        int dial = 50;

        for (String line : lines) {
            char direction = line.charAt(0);
            int amount = Integer.parseInt(line.substring(1));
            int zeros = amount / 100;
            int remainder = amount % 100;

            password += zeros;

            if (direction == 'L') {
                if (dial != 0 && dial - remainder <= 0) { 
                    password++;
                }
                dial = (dial - amount) % 100;
                if (dial < 0) {
                    dial += 100;
                }
            } else { 
                if (dial + remainder >= 100) {
                    password++;
                }
                dial = (dial + amount) % 100;
            }
        }   
        return password;
    }
}
