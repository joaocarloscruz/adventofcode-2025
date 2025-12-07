import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part1());
    }

    public static long part1() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day7/part1/input.txt"));

        int height = lines.size();
        int width = lines.get(0).length(); 
        char[][] grid = new char[height][width];

        int startCol = -1; 

        for (int i = 0; i < height; i++) {
            String line = lines.get(i);
            grid[i] = line.toCharArray(); 

            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 'S') {
                    startCol = j;
                }
            }
        }
        // first pass
        Set<Integer> currentSet = new HashSet<>();
        currentSet.add(startCol);

        // second pass
        long total = 0;
        for(int r = 0; r < height; r++){
            Set<Integer> nextSet = new HashSet<>();
            for(int c : currentSet){
                // split
                if(grid[r][c] == '^'){
                    int split = 0;
                    if(c - 1 >= 0){
                        nextSet.add(c - 1);
                        split++;
                    }
                    if(c + 1 < width){
                        nextSet.add(c + 1);
                        split++;
                    }
                    if(split > 0){
                        total++;
                    }
                } 
                // straight down
                if(grid[r][c] == '.' || grid[r][c] == 'S'){
                    nextSet.add(c);
                }
                
            }
            currentSet = nextSet;
        }

        return total;
    }
}
