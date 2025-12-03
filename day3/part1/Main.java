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
        List<String> banks = Files.readAllLines(Path.of("day3/part1/input.txt"));      
        int totalJoltage = 0;
        int largestIndex = 0;
        int num;
        char c;
        for(String bank : banks){
            int maxFirst = 0;
            int maxSecond = 0;
            int pair = 0;
            // first pass
            for(int i = 0; i < bank.length() - 1; i++){
                c = bank.charAt(i);
                num = Integer.parseInt(String.valueOf(c));
                if(num > maxFirst){
                    maxFirst = num;
                    largestIndex = i;
                }
            }
            //second pass
            for(int j = largestIndex + 1; j < bank.length(); j++){
                c = bank.charAt(j);
                num = Integer.parseInt(String.valueOf(c));
                if(num > maxSecond){
                    maxSecond = num;
                }
            }
            pair = (maxFirst * 10) + maxSecond;
            totalJoltage += pair;
        }

        
        return totalJoltage;
    }
}
