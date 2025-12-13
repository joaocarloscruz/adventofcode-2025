import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part1());
    }

    

    public static long part1() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day9/part1/input.txt"));
        int height = lines.size(); 
        int[] coordinates = new int[2 * height]; // we allocate 2 positions for each pair of coordinates
        int count = 0;
        for(String line : lines) {
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            coordinates[count] = x;
            coordinates[count + 1] = y;
            count += 2;
        }
        long maxArea = 0;

        for(int i = 0; i < 2 * height; i+=2){
            
            int x = coordinates[i];
            int y = coordinates[i + 1];
            for(int j = i + 2; j < 2 * height; j+=2){
                long area = 0;
                int x2 = coordinates[j];
                int y2 = coordinates[j + 1];
                int h = Math.abs(x - x2) + 1;
                int w = Math.abs(y - y2) + 1;
                area = (long) h * w;
                if(area > maxArea) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }
}
