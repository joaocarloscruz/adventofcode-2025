import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part1());
    }

    public static long part1() throws IOException {
        List<String> banks = Files.readAllLines(Path.of("day3/part2/input.txt"));      
        long totalJoltage = 0;
        int largestIndex = 0;
        int num;
        long dozen = 0;
        char c;
        for(String bank : banks){
            largestIndex = 0;
            int index = 0;
            dozen = 0;
            
            for(int i = 11; i >= 0; i--){
                int max = 0;
                for(int j = largestIndex; j < bank.length() - i ; j++){
                    c = bank.charAt(j);
                    
                    num = Integer.parseInt(String.valueOf(c));
                    if(num > max){
                        max = num;
                        index = j;
                        
                    }
                    
                }
                dozen += (max * Math.pow(10, i));
                largestIndex = index + 1;
            } 
            totalJoltage += dozen;           
        }

        
        return totalJoltage;
    }
}
