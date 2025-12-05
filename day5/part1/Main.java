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

    public static int part1() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day5/part1/input.txt"));      
        List<Long> range = new ArrayList<>();
        int freshCount = 0;
        int i = 0;
        for(String line : lines){
            if(line.isBlank()){
                break;
            }
            String[] parts = line.split("-");
            range.add(Long.parseLong(parts[0]));
            range.add(Long.parseLong(parts[1]));
            i++;

        }  
        i++; // skip blank line.
        for(int j = i; j < lines.size(); j++){
            long number = Long.parseLong(lines.get(j));
            for(int k = 0; k < range.size() - 1; k+=2){
                if(number >= range.get(k) && number <= range.get(k + 1)){
                    freshCount++;
                    break;
                }
            }           
        }
        
        return freshCount;

        
    }
}
