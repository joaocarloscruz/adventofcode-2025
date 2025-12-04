import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part1());
    }

    public static int part1() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day4/part1/input.txt"));
        int totalRolls = 0;
        
        
        int height = lines.size();
        int width = lines.get(0).length();
        
        
        int[][] matrix = new int[height + 2][width + 2];
        
        for (int r = 0; r < height; r++) {
            String line = lines.get(r);
            for (int c = 0; c < width; c++) {
                if (line.charAt(c) == '@') {
                    matrix[r + 1][c + 1] = 1;
                } 
            }
        }

        for (int r = 1; r < height + 1; r++) {
            for (int c = 1; c < width + 1; c++) {
                if (matrix[r][c] == 1) {
                    
                    int numberRolls = 0;
                    if(matrix[r + 1][c] == 1){
                        numberRolls++;
                    }
                    if(matrix[r - 1][c] == 1){
                        numberRolls++;
                    }
                    if(matrix[r][c + 1] == 1){
                        numberRolls++;
                    }
                    if(matrix[r][c - 1] == 1){
                        numberRolls++;
                    }
                    if(matrix[r + 1][c + 1] == 1){
                        numberRolls++;
                    }
                    if(matrix[r + 1][c - 1] == 1){
                        numberRolls++;
                    }
                    if(matrix[r - 1][c + 1] == 1){
                        numberRolls++;
                    }
                    if(matrix[r - 1][c - 1] == 1){
                        numberRolls++;
                    } 
                    if(numberRolls < 4){
                        totalRolls++;
                    
                    }
                    numberRolls = 0;
                    
                }
            }
        }
        

        return totalRolls; 
    }
}
