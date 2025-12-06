import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part1());
    }

    public static long part1() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day6/part1/input.txt"));   

        // store operators
        List<Character> operators = new ArrayList<>();
        int lastLineIndex = lines.size() - 1;
        String operatorLine = lines.get(lastLineIndex);
        lines.remove(lastLineIndex);
        String[] opParts = operatorLine.trim().split("\\s+");
        for (String part : opParts) {
            char symbol = part.charAt(0);
            operators.add(symbol);
        }

        // parse grid
        int height = lines.size();
        int width = lines.get(0).trim().split("\\s+").length;
        int[][] grid = new int[height][width];
        
        long total = 0;
        int i = 0;
        for (String line : lines) {
            String[] numbers = line.trim().split("\\s+");
            int j = 0;
            for (String num : numbers) {
                grid[i][j] = Integer.parseInt(num);
                j++;
            }
            i++;
        }

        // calculate by column
        for(int c = 0; c < grid[0].length; c++){
            long columnTotal = grid[0][c];
            for(int r = 1; r < grid.length; r++){
                if(operators.get(c) == '+'){
                    columnTotal += grid[r][c];
                }
                if(operators.get(c) == '*'){
                    columnTotal *= grid[r][c];
                }
            }
            total += columnTotal;
            columnTotal = 0;
        }

        return total;
    }
}
