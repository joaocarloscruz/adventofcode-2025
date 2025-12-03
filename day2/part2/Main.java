import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(part2());
    }

    public static long part2() throws IOException {
        String content = Files.readString(Path.of("day2/part2/input.txt"));
        List<String> ranges = Arrays.asList(content.trim().split(","));
        long invalids = 0;

        for (String range : ranges) {
            String[] parts = range.split("-");
            long min = Long.parseLong(parts[0]);
            long max = Long.parseLong(parts[1]);

            for (long val = min; val <= max; val++) {
                String number = Long.toString(val);
                if (isInvalidId(number)) {
                    invalids += val;
                }
            }
        }
        return invalids;
    }

    private static boolean isInvalidId(String number) {
        int n = number.length();
        for (int j = 1; j <= n / 2; j++) {
            if (n % j != 0) {
                continue;
            }
            String pattern = number.substring(0, j);
            boolean allEqual = true;
            for (int i = j; i < n; i += j) {
                String part = number.substring(i, i + j);
                if (!part.equals(pattern)) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) {
                return true;
            }
        }
        return false;
    }
}
