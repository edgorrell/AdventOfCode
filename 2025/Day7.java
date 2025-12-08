import java.util.*;
import java.io.*;

public class Day7 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("2025\\input.txt"));

        int size = 141;
        ArrayList<Long> list = new ArrayList<>(size);

        String line;
        long total = 0;

        for (int i = 0; i < size; i++) {
            list.add(Long.valueOf(0));
        }

        line = scan.nextLine();
        list.set(line.indexOf('S'),Long.valueOf(1));

        while (scan.hasNextLine()) {
            line = scan.nextLine();

            for (int i = 0; i < list.size(); i++) {

                if (line.charAt(i) == '^') {
                    list.set(i + 1, list.get(i + 1) + list.get(i));
                    list.set(i - 1, list.get(i - 1) + list.get(i));
                    list.set(i, Long.valueOf(0));
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
            total += list.get(i);
        }
        System.out.println(total);
    }
}