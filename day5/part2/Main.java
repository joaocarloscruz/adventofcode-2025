import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part2());
    }

    public static long part2() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("day5/part2/input.txt"));      
        List<long[]> range = new ArrayList<>(); // {start, end}
        int i = 0;
        for(String line : lines){
            if(line.isBlank()){
                break;
            }
            String[] parts = line.split("-");
            range.add(new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1])});
            i++;
        } 

        // sort
        range.sort((a, b) -> Long.compare(a[0], b[0]));
        List<long[]> merged = new ArrayList<>();
        long[] current = range.get(0);

        // fix overlaps
        for(int j = 1; j < range.size(); j++){
            long[] next = range.get(j);
            if(next[0] > current[1]){
                merged.add(current);
                current = next;
            }else{
                current[1] = Math.max(current[1], next[1]);
            }

        }
        merged.add(current);
        
        // calculate ids
        long totalIds = 0;
        for(long[] r : merged){
            totalIds += (r[1] - r[0] + 1);
        }
        
        return totalIds;

        
    }
}
