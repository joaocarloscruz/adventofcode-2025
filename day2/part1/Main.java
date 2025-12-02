import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        // run part 1
        System.out.println(part1());
    }


    public static long part1() throws IOException {
        String content = Files.readString(Path.of("day2/part1/input.txt"));
        List <String> ranges = Arrays.asList(content.trim().split(","));
        long invalids = 0;
        for(String range : ranges) {
            String[] parts = range.split("-");
            long min = Long.parseLong(parts[0]);
            long max = Long.parseLong(parts[1]);
            long val = min;
            String number = "";
            while(val <= max){
                number += val; 
                if(number.length() % 2 == 0 && number.substring(0,number.length() / 2).equals(number.substring(number.length() / 2))){
                    invalids += val;
                }
                number = "";
                val++;
            }
        }
        return invalids;
    
    
        
    }
}
