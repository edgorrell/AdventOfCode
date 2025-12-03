import java.util.*;
import java.io.*;

public class Day3 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("2025\\input.txt"));

        String line;
        long total = 0;

        while (scan.hasNextLine()) {
            line = scan.nextLine();

            String jolt = line.substring(0, 12);
            String rest = line.substring(12);
            
            for (char c : rest.toCharArray()) {
                String best = jolt;
                for (int i = 0; i < 12; i++) {
                    String test = jolt.substring(0, i) + jolt.substring(i + 1) + c;
                    if (Long.valueOf(test) > Long.valueOf(best)) {
                        best = test;
                        System.out.println(line + ": " + best);
                    }
                }
                jolt = best;
            }
            System.out.println("---");

            total += Long.parseLong(jolt);
        }

        System.out.println(total);
        scan.close();
    }

}