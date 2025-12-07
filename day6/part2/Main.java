import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part2());
    }

    public static long part2() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day6/part2/input.txt"));
        
        String operatorLine = lines.remove(lines.size() - 1);

        int height = lines.size();
        int width = lines.get(0).length(); 

        char[][] grid = new char[height][width];
        for(int r = 0; r < height; r++){
            String line = lines.get(r);
            for(int c = 0; c < width; c++){
                grid[r][c] = line.charAt(c);
            }
        }

        long total = 0;
        List<Long> nums = new ArrayList<>(); 
        char blockOp = ' ';

        for (int c = 0; c <= width; c++) {
            
            String numStr = "";
            if (c < width) { 
                for (int r = 0; r < height; r++) {
                    if (grid[r][c] != ' ') {
                        numStr += grid[r][c];
                    }
                }
            }

            if (numStr.isEmpty()) { // wall
                if (!nums.isEmpty()) {
                    long res = nums.get(0);
                    for (int k = 1; k < nums.size(); k++) {
                        if (blockOp == '*') res *= nums.get(k);
                        else res += nums.get(k);
                    }
                    total += res;
                    nums.clear();
                    blockOp = ' '; 
                }
            } else {
                nums.add(Long.parseLong(numStr));
                if (c < operatorLine.length() && operatorLine.charAt(c) != ' ') {
                    blockOp = operatorLine.charAt(c);
                }
            }
        }
        return total;
    }
}