import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static long[][] paths; 

    public static void main(String[] args) throws IOException {
        System.out.println(part2());
    }

    public static long part2() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day7/part2/input.txt"));

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

        paths = new long[height][width];
        for(long[] row : paths){ 
            Arrays.fill(row, -1);
        }

        return solver(0, startCol, height, width, grid);        
    }

    public static long solver(int r, int c, int h, int w, char[][] grid){
        if(r == h){
            return 1;
        }

        if(paths[r][c] != -1) {
            return paths[r][c];
        }

        long count = 0;

        if(grid[r][c] == '^'){
            if(c - 1 >= 0){
                count += solver(r + 1, c - 1, h, w, grid);
            }
            if(c + 1 < w){
                count += solver(r + 1, c + 1, h, w, grid);
            }
        }
        if(grid[r][c] == '.' || grid[r][c] == 'S'){
            count += solver(r + 1, c, h, w, grid);
        }

        paths[r][c] = count;
        return count;
    }
}